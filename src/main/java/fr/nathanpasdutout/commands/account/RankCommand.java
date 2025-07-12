package fr.nathanpasdutout.commands.account;

import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.exceptions.AccountNotFoundException;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.LolData;
import fr.nathanpasdutout.utils.MyImage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.FileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class RankCommand extends BaseCommand {

    public RankCommand() {
        super("rank", "Show your ranked information.",
                new OptionData(OptionType.STRING, "type", "Choose Solo-q of Flex section.")
                        .addChoice("Solo-q", LolData.SOLO)
                        .addChoice("Flex", LolData.FLEX),
                new OptionData(OptionType.USER, "user", "Get rank's data from a user.")
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        User user = event.getOption("user") == null ? event.getUser() : event.getOption("user").getAsUser();

        try {
            JSONArray data =  LolData.getLeagueData(user);

            if(data == null) {
                event.reply("❌ The request as failed.")
                        .setEphemeral(true)
                        .queue();
                return;
            }

            if(event.getOption("type") == null) {
                //TODO
                return;
            }

            String rankType = event.getOption("type").getAsString();
            JSONObject rankData = null;

            for(int i = 0; i < data.length(); i++) {
                 if(data.getJSONObject(i).getString("queueType").equals(rankType)) {
                     rankData = data.getJSONObject(i);
                     break;
                 }
            }

            if(rankData == null) {
                event.reply("No data found for this entry.")
                        .setEphemeral(true)
                        .queue();
                return;
            }

            System.out.println("ranked-icons/Rank=" +
                    rankData.getString(LolData.TIER).substring(0, 1).toUpperCase() +
                    rankData.getString(LolData.TIER).substring(1).toLowerCase() + ".png");

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Ranked Information");
            embed.setDescription(user.getName() + " | " + (rankType.equals(LolData.SOLO) ? "Solo-Q" : "Flex"));
            embed.addField("LP", String.valueOf(rankData.getInt(LolData.LEAGUE_POINTS)), true);
            embed.addField("Rank", rankData.getString(LolData.TIER) + " " + rankData.getString(LolData.RANK), true);
            embed.addBlankField(true);
            embed.addField("Wins", String.valueOf(rankData.getInt(LolData.WINS)), true);
            embed.addField("Losses", String.valueOf(rankData.getInt(LolData.LOSSES)), true);

            MyImage image = new MyImage("ranked-icons/Rank=" +
                    rankData.getString(LolData.TIER).substring(0, 1).toUpperCase() +
                    rankData.getString(LolData.TIER).substring(1).toLowerCase() + ".png");
            InputStream is = image.getImageAsInputStream();

            if (is != null) {
                embed.setThumbnail("attachment://rank.png");

                event.replyEmbeds(embed.build())
                        .addFiles(FileUpload.fromData(is, "rank.png"))
                        .queue();
            } else {
                event.replyEmbeds(embed.build()).queue();
            }

        } catch (AccountNotFoundException e) {
            event.reply("❌ No account was found.")
                    .setEphemeral(true)
                    .queue();
        } catch(RequestFailedException e) {
            event.reply("❌ The request as failed (error " + e.getErrorCode() + ").")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
