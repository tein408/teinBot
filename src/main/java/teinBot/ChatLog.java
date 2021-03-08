package teinBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ChatLog extends ListenerAdapter {

    LogVO logVO = new LogVO();
    LogDAO logDAO = new LogDAO();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String author = event.getAuthor().getAsTag();
        String name = event.getAuthor().getName();
        String channel = event.getChannel().getName();
        String channelid = event.getChannel().getId();
        String message = event.getMessage().getContentRaw();
        String messageId = event.getMessageId();

        if(message.matches(".*")){
            if(!event.getAuthor().isBot()) {
                logVO = new LogVO(author,name,channel,channelid, messageId, message);
                logDAO.insertDB(logVO);
            }
        }
    }//onMessageReceived

}//class
