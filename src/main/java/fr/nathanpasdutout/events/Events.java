package fr.nathanpasdutout.events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.session.SessionDisconnectEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import fr.nathanpasdutout.Main;
import fr.nathanpasdutout.commands.BaseCommand;

import java.util.Arrays;

public class Events extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        System.out.println("Riot Bot is now online...");

        Guild guild = event.getGuild();
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
