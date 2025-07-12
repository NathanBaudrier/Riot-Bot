package fr.nathanpasdutout.events;

import fr.nathanpasdutout.utils.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
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
        Bot.loadCommandsOnGuild(event.getGuild());
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Bot.loadCommandsOnGuild(event.getGuild());

        event.getJDA().getPresence().setStatus(Bot.getStatus());
        event.getJDA().getPresence().setActivity(Bot.getActivity());
    }
}
