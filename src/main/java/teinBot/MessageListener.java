package teinBot;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.internal.entities.TextChannelImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        TextChannel tc = event.getTextChannel();

        String msg = message.getContentDisplay();
        System.out.println("author : "+author);
        //System.out.println("channel : "+channel);
        System.out.println("message : "+msg);
        System.out.println("message.getId() : "+message.getId());

        if(author.isBot()) return;

        if(msg.equals("!ping")){
            channel.sendMessage("Pong!").queue();
        }
        else {
            if(msg.charAt(0) == '!'){
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

                if(args[0].equalsIgnoreCase("test")){
                    System.out.println(messageHistory.getRetrievedHistory());
                    //tc.deleteMessages()
                }

                if(args[0].equalsIgnoreCase("delete")){
                    /*message.delete().queue();*/
                    if(args.length != 2) return;

                    try{
                        count = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        channel.sendMessage("input int please").queue();
                        return;
                    }

                    int share = count/100;
                    int rest = count%100;
                    System.out.println("rest : "+rest);

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
