package teinBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class MainApp {

    public static void main(String[] args) throws LoginException{
        JDA jda = JDABuilder
                .createDefault("NzkwMTUyOTA4NTAyMjA0NDI2.X98dPQ.XvvB0yFQZQHaG1sM6X8v5A3QtyI")
                .build();
        jda.addEventListener(new MessageListener());


    }//main
}//class
