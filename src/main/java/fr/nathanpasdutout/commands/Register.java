package fr.nathanpasdutout.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Register extends BaseCommand {
    public Register() {
        super("register", "Enregistre ta clé d'API pour avoir accès aux fonctionnalités du bot",
                new OptionData(OptionType.STRING, "tag", "Le tag de votre compte (exemple: EUW)", true),
                new OptionData(OptionType.STRING, "pseudo", "Votre pseudonyme Riot", true)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

    }
}
