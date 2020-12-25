package teinBot;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        TextChannel tc = event.getTextChannel();
        String msg = message.getContentDisplay();

        if(author.isBot()) return;
        if(msg.equals("!ping")) {
            channel.sendMessage("Pong!").queue();
        }
        else {
            if(msg.length() != 0 && msg.charAt(0) == '!'){
                String[] args = message.getContentRaw().substring(1).split(" ");
                int count;

                if(args.length <= 0) return;
                if(args[0].equalsIgnoreCase("echo")){

                    try{
                        count = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        channel.sendMessage("int input pls").queue();
                        return;
                    }
                    for(int i=0; i<count; i++){
                        channel.sendMessage("hello : "+i).queue();
                    }
                }

                MessageHistory messageHistory = new MessageHistory(tc);
                List<Message> messages;

                if(args[0].equalsIgnoreCase("history")){
                    System.out.println(messageHistory.size());
                }

                if(args[0].equalsIgnoreCase("delete")){
                    if(args.length != 2) return;

                    try{
                        count = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        channel.sendMessage("input int please").queue();
                        return;
                    }

                    int share = count/100;
                    int rest = count%100;

                    for(int i = 0; i<share; i++){
                        messages = messageHistory.retrievePast(100).complete();
                        tc.deleteMessages(messages).complete();
                    }

                    messages = messageHistory.retrievePast(rest+1).complete();
                    tc.deleteMessages(messages).complete();

                    channel.sendMessage(count + " was deleted").queue();
                }
            }
        }
    }//onMessageReceived method


}//class
