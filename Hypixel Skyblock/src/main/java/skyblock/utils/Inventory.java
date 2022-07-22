package skyblock.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventory implements Listener {

    ItemStack star = new ItemStack(Material.NETHER_STAR);

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerInventory inv = player.getInventory();
        ItemMeta starMeta = star.getItemMeta();
        starMeta.setUnbreakable(true);
        star.setItemMeta(starMeta);

        inv.setItem(8, star);
    }

    @EventHandler
    public void onInvClick (InventoryClickEvent e) {
        if (e.getSlot() == 8) {
            e.setCancelled(true);
            e.getWhoClicked().getItemOnCursor().setType(Material.AIR);
        }
    }

    @EventHandler
    public void onDrop (PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getItemMeta().isUnbreakable() == true)
            e.setCancelled(true);
    }

}
