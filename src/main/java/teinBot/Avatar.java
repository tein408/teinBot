package teinBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Avatar extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.startsWith("$avatar ")){
            String tag = msg.substring(8);
            JDA jda = event.getJDA();
            try {
                User user = jda.getUserByTag(tag);
                if(user.getAvatarUrl() != null){
                    event.getChannel().sendMessage(user.getAvatarUrl()).queue();
                }
                else {
                    event.getChannel().sendMessage("avatar is null").queue();
                }
            } catch (Exception e) {
                event.getChannel().sendMessage("error : Invalid tag format").queue();
            }
        } else if(msg.equals("$avatar")) {
            event.getChannel().sendMessage("input user's discord tag").queue();
        }
    }//onMessageReceived

}//class
