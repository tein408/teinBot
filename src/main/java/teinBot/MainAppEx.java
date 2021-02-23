package teinBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class MainAppEx {

    public static void main(String[] args) throws LoginException{
        JDA jda = JDABuilder
                .createDefault("tokenHere")
                .build();
        jda.addEventListener(new MessageListener());


    }//main
}//class
