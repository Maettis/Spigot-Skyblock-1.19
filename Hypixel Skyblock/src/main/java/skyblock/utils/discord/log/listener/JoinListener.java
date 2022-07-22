package skyblock.utils.discord.log.listener;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import skyblock.utils.discord.log.core.Bot;

public class JoinListener extends ListenerAdapter {

    private static Dotenv dotenv = Bot.dotenv;

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        EmbedBuilder joinEmbed = new EmbedBuilder();
        TextChannel channel = event.getGuild().getTextChannelById(dotenv.get("JOIN_CHANNEL"));
        joinEmbed.setTitle("Welcome on the Skyblock Discord Server!");
        joinEmbed.setDescription("powered by https://github.com/Maettis/Spigot-Skyblock-1.19");
        joinEmbed.setColor(0xffffff);
        joinEmbed.setAuthor("James", "", null);
        joinEmbed.addField("Welcome to the Skyblock Discord Server!", "Please link your account with `/link authcode`", false);
        joinEmbed.setFooter("Skyblock Discord Server", null);


    }
}
