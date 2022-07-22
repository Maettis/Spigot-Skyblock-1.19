package skyblock.skyblock;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import skyblock.listener.ChatListener;
import skyblock.listener.JoinListener;
import skyblock.utils.Head;

import java.util.HashMap;

public final class Skyblock extends JavaPlugin implements Listener {

    private static Skyblock plugin;

    public static HashMap<Integer, Player> hashMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager plm = Bukkit.getPluginManager();
        this.saveDefaultConfig();

        plm.registerEvents(new JoinListener(), this);
        plm.registerEvents(new ChatListener(), this);
        plm.registerEvents(new Minion(), this);
        plm.registerEvents(this, this);

        getCommand("minion").setExecutor(new Minion());
        getCommand("setLobby").setExecutor(new SetLobby());
        getCommand("link").setExecutor(new Link());

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
    }

    @SuppressWarnings("unchecked")
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) { event.setFoodLevel(20); }

    public static Skyblock getPlugin() { return plugin; }
}
