package fr.nathanpasdutout.utils;

import fr.nathanpasdutout.Main;
import fr.nathanpasdutout.commands.BaseCommand;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Arrays;

public class Bot {

    public static OnlineStatus getStatus() {
        return OnlineStatus.ONLINE;
    }

    public static Activity getActivity() {
        return Activity.playing("League of Legends");
    }

    public static void loadCommandsOnGuild(Guild guild) {
        SlashCommandData[] commandsData = Arrays.stream(Main.getCommands()).map(BaseCommand::getCommandData).toArray(SlashCommandData[]::new);
        if (guild.retrieveCommands().complete().isEmpty()) {
            guild.upsertCommand(commandsData[0]).queue();
        }
        guild.retrieveCommands().queue(commands -> {
            commands.forEach(command -> {
                boolean exist = false;
                for (SlashCommandData data : commandsData) {
                    if (command.getName().equals(data.getName())) {
                        exist = true;
                        guild.upsertCommand(data).queue();
                    } else if (commands.stream().map(Command::getName).noneMatch(name -> name.equals(data.getName()))) {
                        guild.upsertCommand(data).queue();
                    }
                }
                if (!exist) guild.deleteCommandById(command.getId()).queue();
            });
        });
    }
}
