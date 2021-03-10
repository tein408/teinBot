package teinBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;

public class EditLog extends ListenerAdapter {

    LogVO logVO = new LogVO();
    LogDAO logDAO = new LogDAO();

    @Override
    public void onMessageUpdate(@NotNull MessageUpdateEvent event) {
        User user = event.getMember().getUser();

        if(event.getAuthor().isBot()){
            return;
        }

        logVO = logDAO.selectBeforeMessage(event.getMessageId());
        if(logVO.getMessage()!=null){
            EmbedBuilder update = new EmbedBuilder();
            update.setColor(Color.GRAY);
            update.setAuthor(event.getAuthor().getAsTag(), null, user.getAvatarUrl());
            update.setTitle("Message edited at #"+event.getChannel().getName());

            update.addField("Before", logVO.getMessage(),false);
            update.addField("After", event.getMessage().getContentRaw(),false);
            update.setTimestamp(OffsetDateTime.now());

            logVO.setChanged(event.getMessage().getContentRaw());
            logVO.setMessageId(event.getMessageId());
            logDAO.updateDB(logVO);

            event.getGuild().getTextChannelsByName("update-message-log",false)
                    .get(0).sendMessage(update.build()).queue();
        }

    }//onMessageUpdate


}//class
