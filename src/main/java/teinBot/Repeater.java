package teinBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Repeater extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        int count;
        if(msg.startsWith("$echo ")){
            String[] args = event.getMessage().getContentRaw().substring(1).split(" ");
            if(args[1].chars().allMatch(Character::isDigit)) {
                count = Integer.parseInt(args[1]);
                for (int i = 0; i < count; i++) {
                    event.getChannel().sendMessage("hello : " + i).queue();
                }
            } else if(!args[1].chars().allMatch(Character::isDigit)){
                event.getChannel().sendMessage("input int pls").queue();
            }
        } else if(msg.equals("$echo")) {
            event.getChannel().sendMessage("input int pls").queue();
        }
    }//onMessageReceived

}//class
