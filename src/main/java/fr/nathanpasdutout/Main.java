package fr.nathanpasdutout;

import fr.nathanpasdutout.commands.account.RankCommand;
import fr.nathanpasdutout.commands.account.RegisterCommand;
import fr.nathanpasdutout.commands.lol.StatusCommand;
import fr.nathanpasdutout.commands.user.InviteCommand;
import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.commands.user.PingCommand;
import fr.nathanpasdutout.database.Database;
import fr.nathanpasdutout.events.Events;

import fr.nathanpasdutout.utils.Bot;
import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
    private static JDA bot;
    private static Database database;
    private static Dotenv env;

    private static final BaseCommand[] commands = {
            new PingCommand(),
            new RegisterCommand(),
            new RankCommand(),
            new StatusCommand(),
            new InviteCommand()
    };

    public static void main(String[] args) {
        env = Dotenv.load();

        bot = JDABuilder.createDefault(env.get("DISCORD_TOKEN"))
                .setStatus(Bot.getStatus())
                .setActivity(Bot.getActivity())
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