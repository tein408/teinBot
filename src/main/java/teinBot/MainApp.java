package teinBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class MainApp {

    public static void main(String[] args) throws LoginException{
        JDA jda = JDABuilder
                .createDefault(Token.getToken())
                .build();
        jda.addEventListener(new MessageListener());

    }//main


}//class
