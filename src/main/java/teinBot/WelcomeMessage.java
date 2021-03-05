package teinBot;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WelcomeMessage extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        try {
            event.getGuild()
                    .getTextChannelsByName("in-out-log", false)
                    .get(0).sendMessage("welcome " + event.getMember().getAsMention()).queue();
        } catch (Exception e) {
            event.getGuild().getDefaultChannel().sendMessage("error to print welcome message");
        }
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        System.out.println("onGuildMemberRemove");
        System.out.println(event.getUser().getName());
        //System.out.println(event.getUser().getAsMention());
        try {
            event.getGuild()
                    .getTextChannelsByName("in-out-log", false)
                    .get(0).sendMessage("bye " + event.getUser().getAsMention()).queue();
        } catch (Exception e) {
            event.getGuild().getDefaultChannel().sendMessage("error to print bye message");
        }

    }


}//class
