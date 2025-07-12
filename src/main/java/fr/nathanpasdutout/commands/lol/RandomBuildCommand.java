package fr.nathanpasdutout.commands.lol;

import fr.nathanpasdutout.commands.BaseCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RandomBuildCommand extends BaseCommand {

    public RandomBuildCommand() {
        super("random-build", "Génère un champion, un rôle, des summoners et un build aléatoire à jouer.");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

    }
}
