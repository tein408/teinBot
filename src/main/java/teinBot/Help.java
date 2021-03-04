package teinBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.OffsetDateTime;

public class Help extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        String msg = event.getMessage().getContentDisplay();

        if(msg.equalsIgnoreCase("$help")) {
            embedBuilder.setColor(new Color(66, 98, 140));
            //embedBuilder.setAuthor("setAuthor");
            embedBuilder.setTitle("teinBot");
            embedBuilder.setDescription("How to use teinBot?");
            //embedBuilder.addBlankField(true);
            embedBuilder.addField("$help", "도움말 출력",false);
            embedBuilder.addField("$ping", "Pong! 출력",false);
            embedBuilder.addField("$avatar [@Mention]", "해당 유저 프로필 이미지 출력",false);
            embedBuilder.addField("$echo [int]", "int 만큼 [hello : int] 출력",false);
            embedBuilder.addField("$delete [int]", "int 만큼 메시지 삭제",false);
            embedBuilder.setFooter("made by 이안#8990");
            embedBuilder.setTimestamp(OffsetDateTime.now());
            event.getChannel().sendMessage(embedBuilder.build()).queue();
        }

    }//onMessageReceived

}//class
