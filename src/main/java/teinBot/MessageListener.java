package teinBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.List;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        TextChannel tc = event.getTextChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        String msg = message.getContentDisplay();
        //System.out.println("author : "+author);
        //System.out.println("channel : "+channel);
        //System.out.println("message : "+msg);
        //System.out.println("message.getId() : "+message.getId());

        if(author.isBot()) return;
        if(msg.equalsIgnoreCase("$help")) {
            embedBuilder.setColor(new Color(66, 98, 140));
            //embedBuilder.setAuthor("setAuthor");
            embedBuilder.setTitle("teinBot");
            embedBuilder.setDescription("How to use teinBot?");
            //embedBuilder.addBlankField(true);
            embedBuilder.addField("$help", "도움말 출력",false);
            embedBuilder.addField("$ping", "Pong! 출력",false);
            embedBuilder.addField("$echo [int]", "int 만큼 [hello : int] 출력",false);
            embedBuilder.addField("$delete [int]", "int 만큼 메시지 삭제",false);
            embedBuilder.setFooter("made by 이안#8990");
            embedBuilder.setTimestamp(OffsetDateTime.now());
            channel.sendMessage(embedBuilder.build()).queue();
        }
        else {
            if(msg.length() != 0 && msg.charAt(0) == '$'){
                String[] args = message.getContentRaw().substring(1).split(" ");
                int count;

                if(args.length <= 0) return;
                if(args[0].equalsIgnoreCase("ping")){
                    channel.sendMessage("Pong!").queue();
                }


                if(args[0].equalsIgnoreCase("echo")){

                    try{
                        count = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        channel.sendMessage("input int pls").queue();
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

                    try{
                        for(int i = 0; i<share; i++){
                            messages = messageHistory.retrievePast(100).complete();
                            tc.deleteMessages(messages).complete();
                        }

                        messages = messageHistory.retrievePast(rest+1).complete();
                        tc.deleteMessages(messages).complete();

                        channel.sendMessage(count + " was deleted").queue();

                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        channel.sendMessage(author.getAsMention()+ "delete error").queue();
                    }

                }//delete

                if(args[0].equalsIgnoreCase("avatar")) {
                    String tag = message.getContentRaw().substring(8);
                    JDA jda = event.getJDA();
                    User user = jda.getUserByTag(tag);
                    tc.sendMessage(user.getAvatarUrl()).queue();
                }


            }
        }
    }//onMessageReceived method


}//class
