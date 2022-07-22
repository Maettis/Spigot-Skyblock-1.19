package skyblock.skyblock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import skyblock.utils.Head;
import skyblock.utils.MinionConstructor;

import java.util.HashMap;

public class Minion implements Listener, CommandExecutor {

    public static HashMap<Location, MinionConstructor> minions = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            ItemStack head = Head.returnHead(p.getName());
            ItemMeta headMeta = head.getItemMeta();
            headMeta.setDisplayName("§6Minion");
            head.setItemMeta(headMeta);

            p.getInventory().addItem(head);
        } else
            sender.sendMessage("Only Players can execute this command!");

        return false;
    }

    @EventHandler
    public void onSetMinion (BlockPlaceEvent e) {
        if (e.getBlock().getType().toString() != "PLAYER_HEAD") return;
        Player player = e.getPlayer();
        MinionConstructor minion = new MinionConstructor("§6" + player.getName() + "'s Minion", e.getBlock().getLocation(), player);
        minions.put(minion.getLocation(), minion);
        e.setCancelled(true);
    }

    @EventHandler
    public void onClick (PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if(e.getRightClicked() instanceof ArmorStand) {
            if (!minions.containsKey(e.getRightClicked().getLocation())) return;
            e.setCancelled(true);
            MinionConstructor minion = minions.get(e.getRightClicked().getLocation());
            minion.openInv(player);
        }
    }
}
