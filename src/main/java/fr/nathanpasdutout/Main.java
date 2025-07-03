package fr.nathanpasdutout;

import fr.nathanpasdutout.commands.account.Rank;
import fr.nathanpasdutout.commands.account.Register;
import fr.nathanpasdutout.database.Database;
import fr.nathanpasdutout.events.Events;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.commands.user.Ping;

public class Main {
    private static JDA bot;
    private static Database database;
    private static Dotenv env;

    private static final BaseCommand[] commands = {
            new Ping(),
            new Register(),
            new Rank()
    };

    public static void main(String[] args) {
        env = Dotenv.load();

        bot = JDABuilder.createDefault(env.get("DISCORD_TOKEN"))
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Riot"))
                .addEventListeners(new Events())
                .addEventListeners(getCommands())
                .build();

        database = new Database("src/main/java/fr/nathanpasdutout/database.db");
        database.connection();
    }

    public static JDA getBotInstance() {
        return bot;
    }

    public static Database getDatabase() {
        return database;
    }

    public static Dotenv getEnv() {
        return env;
    }

    public static BaseCommand[] getCommands() {
        return commands;
    }
}