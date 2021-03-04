package teinBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingPong extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equals("$ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }
    }

}//class
