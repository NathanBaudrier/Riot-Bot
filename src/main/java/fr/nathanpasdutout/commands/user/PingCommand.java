package fr.nathanpasdutout.commands.user;

import fr.nathanpasdutout.Main;
import fr.nathanpasdutout.commands.BaseCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand extends BaseCommand {

    public PingCommand() {
        super("ping", "Renvoie la latence du bot.", null);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Le ping du bot est de " + Main.getBotInstance().getGatewayPing() + "ms.").queue();
    }
}
