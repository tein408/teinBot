package teinBot;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Avatar extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.startsWith("$avatar ")){
            User user;
            List<Member> mentionedMember = event.getMessage().getMentionedMembers();
            try {
                if(mentionedMember != null){
                    user = mentionedMember.get(0).getUser();
                    if(user.getAvatarUrl() != null) {
                        event.getChannel().sendMessage(user.getAvatarUrl() + "?size=1024").queue();
                    } else if (user.getAvatarUrl() == null){
                        event.getChannel().sendMessage("no image").queue();
                    }
                }
            } catch (Exception e) {
                event.getChannel().sendMessage("error").queue();
            }
        } else if(msg.equals("$avatar")) {
            event.getChannel().sendMessage("need mention user").queue();
        }
    }//onMessageReceived

}//class
