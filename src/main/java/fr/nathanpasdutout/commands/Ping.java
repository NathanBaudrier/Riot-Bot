package fr.nathanpasdutout.commands;

import fr.nathanpasdutout.Main;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping extends BaseCommand {

    public Ping() {
        super("ping", "Renvoie la latence du bot.", null);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Le ping du bot est de " + Main.getBotInstance().getGatewayPing() + "ms.").queue();
    }
}
