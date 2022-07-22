package skyblock.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Head {

    public static ItemStack returnHead(String playerName) {
        ItemStack head = getHead(playerName);
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Minion"));
        head.setItemMeta(headMeta);
        return head;
    }

    private static ItemStack getHead(String playerName) {
        ItemStack item = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(playerName);
        skull.setOwner(playerName);
        item.setItemMeta(skull);
        return item;
    }

}
