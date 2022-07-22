package skyblock.skyblock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (!sender.hasPermission("skyblock.setlobby")) {
                sender.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }

            Player player = (Player) sender;

            FileConfiguration config = Skyblock.getPlugin().getConfig();
            config.set("lobby.x", player.getLocation().getX());
            config.set("lobby.y", player.getLocation().getY());
            config.set("lobby.z", player.getLocation().getZ());
            config.set("lobby.yaw", player.getLocation().getYaw());
            //config.set("lobby.pitch", player.getLocation().getPitch());

            Skyblock.getPlugin().saveConfig();

            player.sendMessage("§aLobby set!");
        }

        return false;
    }
}
