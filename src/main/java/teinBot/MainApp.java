package teinBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class MainApp {

    public static void main(String[] args) {

        JDABuilder jdaBuilder = JDABuilder.createDefault(Token.getToken());
        JDA jda = null;

        jda.addEventListener(new MessageListener());

        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }//main


}//class
