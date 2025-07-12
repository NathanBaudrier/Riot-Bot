package fr.nathanpasdutout.commands.user;

import fr.nathanpasdutout.commands.BaseCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class InviteCommand extends BaseCommand {

    public InviteCommand() {
        super("invite", "Show invite link of the bot.");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Invite", "https://discord.com/oauth2/authorize?client_id=1338465299267325982&permissions=8&integration_type=0&scope=bot+applications.commands");

        event.replyEmbeds(embed.build()).queue();
    }
}
