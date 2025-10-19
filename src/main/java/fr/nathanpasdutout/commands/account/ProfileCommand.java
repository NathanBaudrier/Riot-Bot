package fr.nathanpasdutout.commands.account;

import fr.nathanpasdutout.commands.BaseCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class ProfileCommand extends BaseCommand {

    public ProfileCommand() {
        super("profile", "Show your information.", new OptionData(OptionType.USER, "user", "User that you want to show the profile."));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

    }
}
