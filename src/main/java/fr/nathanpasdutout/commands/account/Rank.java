package fr.nathanpasdutout.commands.account;

import fr.nathanpasdutout.Main;
import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.riotapi.LolData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class Rank extends BaseCommand {

    public Rank() {
        super("rank", "Show your ranked information.");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        JSONArray array = LolData.getLeagueData(event.getUser());

        if(array == null) {
            event.reply("You are not registered or the request as failed.").setEphemeral(true).queue();
            return;
        }

        JSONObject json = array.getJSONObject(0);

        System.out.println("ranked-emblems/Rank=" + json.getString(LolData.TIER).substring(0, 1).toUpperCase() + json.getString(LolData.TIER).substring(1).toLowerCase() + ".png");

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Ranked Information");
        embed.setDescription("You can show " + event.getUser().getName() + "'s ranked information.");
        embed.addField("LP", String.valueOf(json.getInt(LolData.LEAGUE_POINTS)), true);
        embed.addField("Rank", json.getString(LolData.TIER) + " " + json.getString(LolData.RANK), true);
        embed.addField("Wins", String.valueOf(json.getInt(LolData.WINS)), false);
        embed.addField("Losses", String.valueOf(json.getInt(LolData.LOSSES)), true);

        event.replyEmbeds(embed.build()).queue();
    }
}
