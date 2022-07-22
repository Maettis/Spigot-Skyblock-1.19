package skyblock.skyblock;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyblock.utils.discord.log.system.LinkAccount;

import java.security.SecureRandom;

public class Link extends ListenerAdapter implements CommandExecutor {


    private JDA jda;


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.hasPermission("skyblock.link")) {
                player.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }
            // check if player is already in hashmap
            if (Skyblock.hashMap.containsValue(player)) {
                player.sendMessage("§cYou are already linked to an account.");
                return true;
            }



            if (player.hasPermission("skyblock.link")) {
                player.sendMessage("&2&&lHey, this is your code to link your account: &a" + generateAuthCode());
            }


        }

        return false;
    }

    protected static SecureRandom random = new SecureRandom();

    public synchronized String generateAuthCode() {
        long longToken = Math.abs(random.nextLong());
        String random = Long.toString(longToken, 4);
        return ( random );
    }



    public Player getPlayer(Player p) {
        return p;
    }

}
