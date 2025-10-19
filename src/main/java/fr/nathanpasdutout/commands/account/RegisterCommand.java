package fr.nathanpasdutout.commands.account;

import fr.nathanpasdutout.Main;
import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.database.UserData;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.LolData;
import fr.nathanpasdutout.riotapi.elements.account.Account;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class RegisterCommand extends BaseCommand {

    public RegisterCommand() {
        super("register", "Allows to register your account in our database (DiscordID and PUUID only).",
                new OptionData(OptionType.STRING, "game_name", "In-game pseudo", true),
                new OptionData(OptionType.STRING, "tag_line", "Tag line of your account (ex: EUW)", true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        UserData data = new UserData(event.getUser());

        if(data.hasData()) {
            event.reply("You are already registered!")
                    .setEphemeral(true)
                    .queue();

            return;
        }

        try {
            Account account = LolData.getAccountData(event.getOptions().get(0).getAsString(), event.getOptions().get(1).getAsString());
            data.createData(account.getPUUID());

            event.reply("Your account has been created!")
                    .setEphemeral(true)
                    .queue();
        } catch(RequestFailedException exception) {
            event.reply("Request has failed: Error " + exception.getErrorCode())
                    .queue();
        }
    }
}
