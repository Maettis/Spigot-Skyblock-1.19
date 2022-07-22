package skyblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import skyblock.skyblock.Minion;
import skyblock.skyblock.Skyblock;

public class MinionConstructor implements Listener {

    private String name;
    private Location loc;
    private ArmorStand as;
    private Player p;
    private int lvl = 1;
    private ItemStack is = new ItemStack(Material.COBBLESTONE);
    private int quantaty = 0;

    public MinionConstructor (String name, Location loc, Player p) {
        Plugin plugin = Skyblock.getPlugin(Skyblock.class);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.name = name;
        loc = new org.bukkit.Location(loc.getWorld(), loc.getX() + 0.5, loc.getY(), loc.getZ() + 0.5);
        this.loc = loc;
        this.p = p;

        as = loc.getWorld().spawn(loc, ArmorStand.class);

        as.setBasePlate(false);
        as.setArms(true);
        as.setVisible(true);
        as.setInvulnerable(true);
        as.setCanPickupItems(false);
        as.setGravity(false);
        as.setSmall(true);
        as.setCustomNameVisible(true);

        as.setCustomName(name);
        as.setItemInHand(new ItemStack(Material.WOODEN_PICKAXE));
        as.setHelmet(Head.returnHead(p.getName()));
        as.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        as.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        as.setBoots(new ItemStack(Material.LEATHER_BOOTS));
    }

    public void countUp(int multiplier) {
        quantaty += 1 * multiplier;
    }

    public void openInv (Player p) {
        Inventory inv = Bukkit.createInventory(p, 9*5, name);

        for (int i = 0; i < 9*5; i++) {
            ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName(" ");
            glass.setItemMeta(glassMeta);

            inv.setItem(i, glass);
        }

        ItemStack remove = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta removeMeta = remove.getItemMeta();
        removeMeta.setDisplayName("§cRemove Minion");
        remove.setItemMeta(removeMeta);

        inv.setItem(9*5-1, remove);
        p.openInventory(inv);
    }

    public String getName() {
        return name;
    }
    public Location getLocation() {
        return loc;
    }

    @EventHandler
    public void handleNavigatorGUIClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player) || e.getCurrentItem() == null) return;
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle() == name) {
            switch(e.getCurrentItem().getType()) {
                case RED_STAINED_GLASS_PANE:
                    e.setCancelled(true);

                    // Removes the Minion from World and Minions list
                    player.closeInventory();
                    Minion.minions.remove(loc);
                    as.remove();

                    // Gives the Player back the Minion Item for replacing it
                    ItemStack head = Head.returnHead(p.getName());
                    ItemMeta headMeta = head.getItemMeta();
                    headMeta.setDisplayName("§6Minion");
                    head.setItemMeta(headMeta);
                    p.getInventory().addItem(head);
                    break;
                case GRAY_STAINED_GLASS_PANE:
                    e.setCancelled(true);
                default:
                    break;
            }
        }
    }

}
