package teinBot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LunchListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.isFromType(ChannelType.PRIVATE)){
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }

        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();

        String msg = message.getContentDisplay();
        System.out.println("author : "+author);
        System.out.println("channel : "+channel);
        System.out.println("message : "+msg);

        if(msg.equals("!ping")){
            channel.sendMessage("Pong!").queue();
        }
        else if(msg.equals("!delete")){
            channel.sendMessage("not yet...").queue();
        }



    }//onMessageReceived method



}//class
