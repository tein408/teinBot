package teinBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class MainApp {

    public static void main(String[] args) {

        JDABuilder jdaBuilder = JDABuilder.createDefault(Token.getToken());

        PingPong pingPong = new PingPong();
        jdaBuilder.addEventListeners(pingPong);

        Help help = new Help();
        jdaBuilder.addEventListeners(help);

        Repeater repeater = new Repeater();
        jdaBuilder.addEventListeners(repeater);

        Avatar avatar = new Avatar();
        jdaBuilder.addEventListeners(avatar);

        DeleteMessages messageListener = new DeleteMessages();
        jdaBuilder.addEventListeners(messageListener);

        WelcomeMessage welcomeMessage = new WelcomeMessage();

        jdaBuilder.addEventListeners(welcomeMessage);
        jdaBuilder.setActivity(Activity.listening("$help"));

        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        JDA jda = null;

        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        /*try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }//main


}//class
