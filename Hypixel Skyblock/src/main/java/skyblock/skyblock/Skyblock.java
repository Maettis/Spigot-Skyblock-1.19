package skyblock.skyblock;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import skyblock.enchantments.Telekineses;
import skyblock.listener.ChatListener;
import skyblock.listener.JoinListener;
import skyblock.utils.Head;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class Skyblock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager plm = Bukkit.getPluginManager();
        plm.registerEvents(new JoinListener(), this);
        plm.registerEvents(new ChatListener(), this);
        plm.registerEvents(new Minion(), this);
        plm.registerEvents(this, this);

        getCommand("minion").setExecutor(new Minion());

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Minion.minions.isEmpty()) return;
                Minion.minions.forEach((location, minionConstructor) -> {
                    minionConstructor.countUp(1);
                });
            }
        }.runTaskTimer(this, 100L, 100L); //Delays in ticks

        ItemStack cobblestone_minion = Head.returnHead("Cobble");
        ItemMeta cobblestone_minionMeta = cobblestone_minion.getItemMeta();
        cobblestone_minionMeta.setDisplayName("§6Minion");
        cobblestone_minion.setItemMeta(cobblestone_minionMeta);

        NamespacedKey key = new NamespacedKey(this, "cobblestone_minion");
        ShapedRecipe c_minion_recipe = new ShapedRecipe(key, cobblestone_minion);
        c_minion_recipe.shape("CCC", "CPC", "CCC");
        c_minion_recipe.setIngredient('C', Material.COBBLESTONE, 10);
        c_minion_recipe.setIngredient('P', Material.WOODEN_PICKAXE);

        Bukkit.addRecipe(c_minion_recipe);

        loadEnchantments();
    }

    public Telekineses tele = new Telekineses(new NamespacedKey("Tele", String.valueOf(101)));

    @SuppressWarnings("unchecked")
    public void onDisable() {
        // Plugin shutdown logic
        try {
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            byIdField.setAccessible(true);
            HashMap<Integer, Enchantment> byId =(HashMap<Integer, Enchantment>) byIdField.get(null);

            if (byId.containsKey(tele.getID())) {
                byId.remove(tele.getID());
            }
        } catch (Exception e) { }
    }

    public void loadEnchantments() {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Enchantment.registerEnchantment(tele);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
