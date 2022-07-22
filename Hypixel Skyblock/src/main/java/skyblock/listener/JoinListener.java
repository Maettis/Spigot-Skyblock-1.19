package skyblock.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin (PlayerJoinEvent e) {
        String name = e.getPlayer().getName();
        e.setJoinMessage("§6>> §7" + name);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String name = e.getEventName();
        e.setQuitMessage("§7<< " + name);
    }

}
