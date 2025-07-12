package fr.nathanpasdutout.commands.account;

import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.database.UserData;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.LolData;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.json.JSONObject;

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
            event.reply("You are already registered!").setEphemeral(true).queue();
            return;
        }

        try {
            JSONObject json = LolData.getAccountData(event.getOptions().get(0).getAsString(), event.getOptions().get(1).getAsString());

            data.createData(json.getString(LolData.PUUID));
            event.reply("✅ Your account has been successfully created!")
                    .setEphemeral(true)
                    .queue();
        } catch(RequestFailedException e) {
            event.reply("❌ Account not found.")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
