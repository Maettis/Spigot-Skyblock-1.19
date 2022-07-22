package skyblock.listener;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import skyblock.skyblock.Skyblock;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin (PlayerJoinEvent e) {
        String name = e.getPlayer().getName();
        e.setJoinMessage("§6>> §7" + name);

        Player player = e.getPlayer();
        player.setFoodLevel(20);

        FileConfiguration config = Skyblock.getPlugin().getConfig();
        if (config.getString("spawn.world") == null) {
            player.sendMessage("§cSpawn is not set!");
            return;
        }
        Location spawn = new Location(player.getWorld(), config.getDouble("spawn.x"), config.getDouble("spawn.y"), config.getDouble("spawn.z"), (float) config.getDouble("spawn.yaw"), 50);
        player.teleport(spawn);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String name = e.getEventName();
        e.setQuitMessage("§7<< " + name);
    }

}
