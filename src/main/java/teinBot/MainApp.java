package teinBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class MainApp {

    public static void main(String[] args) {

        JDABuilder jdaBuilder = JDABuilder.createDefault(Token.getToken());
        JDA jda = null;

        PingPong pingPong = new PingPong();
        jdaBuilder.addEventListeners(pingPong);

        Help help = new Help();
        jdaBuilder.addEventListeners(help);

        Repeater repeater = new Repeater();
        jdaBuilder.addEventListeners(repeater);

        MessageListener messageListener = new MessageListener();
        jdaBuilder.addEventListeners(messageListener);

        jdaBuilder.setActivity(Activity.listening("히힛히힛"));

        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }//main


}//class
