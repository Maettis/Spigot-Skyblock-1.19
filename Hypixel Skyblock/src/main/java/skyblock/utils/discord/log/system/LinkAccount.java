package skyblock.utils.discord.log.system;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyblock.skyblock.Link;

public class LinkAccount extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("link")) {
            if (event.getOption("authcode") != null) {

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Link Account");
                eb.setDescription("Please wait while we link your account");
               // eb.addField("User Name", )


            }
        }
    }
}
