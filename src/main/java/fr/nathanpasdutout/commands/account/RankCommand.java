package fr.nathanpasdutout.commands.account;

import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.exceptions.AccountNotFoundException;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.LolData;
import fr.nathanpasdutout.riotapi.elements.league.Rank;
import fr.nathanpasdutout.utils.MyImage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.FileUpload;

import java.awt.*;
import java.io.InputStream;
import java.util.Random;

public class RankCommand extends BaseCommand {

    public RankCommand() {
        super("rank", "Show your ranked information.",
                new OptionData(OptionType.STRING, "type", "Choose Solo-q of Flex section.")
                        .addChoice("Solo-q", Rank.SOLO_QUEUE_TYPE)
                        .addChoice("Flex", Rank.FLEX_QUEUE_TYPE),
                new OptionData(OptionType.USER, "user", "Get rank's data from a user.")
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        User user = event.getOption("user") == null ? event.getUser() : event.getOption("user").getAsUser();

        try {
            Rank[] ranks = LolData.getLeagueData(user);

            EmbedBuilder embed = new EmbedBuilder();

            if(ranks == null) {
                embed.setDescription("No data found.");
                embed.setColor(Color.RED);
                event.replyEmbeds(embed.build()).queue();

                return;
            }

            OptionMapping queueType = event.getOption("type");
            Rank rank = queueType != null ? this.getSpecificsRank(queueType.getAsString(), ranks) : ranks[0];

            embed.setTitle("Ranked Information");
            embed.setDescription(user.getName() + " - " + Rank.getQueueName(rank.getQueueType()));
            Random random = new Random();
            embed.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
            embed.addField("🛡️ | Rank", rank.getTier() + " " + rank.getRank(), true);
            embed.addField("🗡️ | LP", String.valueOf(rank.getLeaguePoints()), false);
            embed.addField("🏆 | Wins", String.valueOf(rank.getWins()), false);
            embed.addField("😢 | Losses", String.valueOf(rank.getLosses()), false);

            MyImage image = new MyImage("ranked-icons/Rank=" +
                    rank.getTier().substring(0, 1).toUpperCase() +
                    rank.getTier().substring(1).toLowerCase() + ".png");
            InputStream is = image.getImageAsInputStream();

            if(is != null) {
                embed.setThumbnail("attachment://rank.png");

                event.replyEmbeds(embed.build())
                        .addFiles(FileUpload.fromData(is, "rank.png"))
                        .queue();
            } else {
                event.replyEmbeds(embed.build())
                        .queue();
            }
        } catch(RequestFailedException exception) {
            event.reply("Error " + exception.getErrorCode())
                    .setEphemeral(true)
                    .queue();
        } catch(AccountNotFoundException exception) {
            event.reply("❌ No account was found.")
                    .setEphemeral(true)
                    .queue();
        }
    }

    private Rank getSpecificsRank(String queueType, Rank[] ranks) {
        for(Rank rank : ranks) {
            if(rank.getQueueType().equals(queueType)) return rank;
        }

        return null;
    }
}
