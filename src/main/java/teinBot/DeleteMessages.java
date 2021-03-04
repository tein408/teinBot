package teinBot;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class DeleteMessages extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        MessageChannel channel = event.getChannel();
        TextChannel tc = event.getTextChannel();
        String msg = event.getMessage().getContentRaw();
        MessageHistory messageHistory = new MessageHistory(tc);
        List<Message> messages;

        int count;

        if(msg.startsWith("$delete ")){
            String[] args = event.getMessage().getContentRaw().substring(1).split(" ");
            if(args[1].chars().allMatch(Character::isDigit)) {
                if(args.length != 2) return;
                try{
                    count = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    channel.sendMessage("input int please").queue();
                    return;
                }

                int share = count/100;
                int rest = count%100;
                try{
                    for(int i = 0; i<share; i++){
                        messages = messageHistory.retrievePast(100).complete();
                        tc.deleteMessages(messages).complete();
                    }

                    messages = messageHistory.retrievePast(rest+1).complete();
                    tc.deleteMessages(messages).complete();

                    channel.sendMessage(count + " was deleted").queue();

                } catch (IllegalArgumentException e) {
                    channel.sendMessage("delete error").queue();
                }
            } else if(!args[1].chars().allMatch(Character::isDigit)){
                event.getChannel().sendMessage("input int pls").queue();
            }
        } else if(msg.equals("$delete")) {
            event.getChannel().sendMessage("input int pls").queue();
        }
    }//onMessageReceived method


}//class
