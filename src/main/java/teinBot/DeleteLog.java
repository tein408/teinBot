package teinBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;

public class DeleteLog extends ListenerAdapter {

    LogVO logVO = new LogVO();
    LogDAO logDAO = new LogDAO();

    @Override
    public void onMessageDelete(@NotNull MessageDeleteEvent event) {
        logVO = logDAO.selectBeforeMessage(event.getMessageId());
        if(logVO.getAuthor()!=null){
            logDAO.messageDeleted(event.getMessageId());
            EmbedBuilder deleted = new EmbedBuilder();
            deleted.setColor(Color.GRAY);
            deleted.setAuthor(logVO.getAuthor());
            deleted.setTitle("Message deleted at #"+event.getChannel().getName());
            deleted.addField("Message", logVO.getMessage(), false);
            deleted.setTimestamp(OffsetDateTime.now());

            event.getGuild().getTextChannelsByName("update-message-log",false)
                    .get(0).sendMessage(deleted.build()).queue();
        }

    }//onMessageDelete

}//class
