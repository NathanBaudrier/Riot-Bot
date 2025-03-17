package fr.nathanpasdutout;

import fr.nathanpasdutout.commands.Register;
import fr.nathanpasdutout.database.Database;
import fr.nathanpasdutout.events.Events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.commands.Ping;

public class Main {

    private static JDA bot;
    private static Database database;

    private static final BaseCommand[] commands = {
            new Ping(),
            new Register()
    };

    public static void main(String[] args) {

        bot = JDABuilder.createDefault(TempClass.TOKEN)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Riot"))
                .addEventListeners(new Events())
                .addEventListeners(getCommands())
                .build();

        database = new Database("src/main/java/org/example/database/database.db");
        database.connection();
    }

    public static JDA getBotInstance() {
        return bot;
    }

    public static Database getDatabase() {
        return database;
    }

    public static BaseCommand[] getCommands() {
        return commands;
    }
}