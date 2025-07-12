package fr.nathanpasdutout.commands.lol;

import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.LolData;
import fr.nathanpasdutout.utils.Time;
import fr.nathanpasdutout.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Status extends BaseCommand {

    public Status() {
        super("status", "Shows servers status.");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            JSONObject data = LolData.getStatusData();

            JSONArray maintenances = data.getJSONArray("maintenances");
            JSONArray incidents = data.getJSONArray("incidents");

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Servers status");

            if(maintenances.isEmpty() && incidents.isEmpty()) {
                embed.setColor(Color.GREEN);
                embed.setDescription("✅ Everything is ok.");
            }

            String language = "en_US";

            if(!maintenances.isEmpty()) {
                embed.setColor(Color.RED);

                String content = "";

                for(int i = 0; i < maintenances.length(); i++) {
                    JSONObject maintenance = maintenances.getJSONObject(i);

                    JSONArray titles = maintenance.getJSONArray("titles");
                    for(int j = 0; j < titles.length(); j++) {
                        JSONObject title = titles.getJSONObject(j);
                        if(title.getString("locale").equals(language)) {
                            content += "- **Title:** " + title.getString("content") + "\n";
                            break;
                        }
                    }

                    content += "- **Status:** " + Utils.capitalizeFirstLetter(maintenance.getString("maintenance_status")) + "\n";
                    content += "- **Concerned platforms:** " + maintenance.getJSONArray("platforms").join(",").replace('"', ' ') + "\n";

                    content += "\n- **Updates:**\n";

                    JSONArray updates = maintenance.getJSONArray("updates");
                    for(int j = 0; j < updates.length(); j++) {
                        JSONObject update = updates.getJSONObject(j);
                        content += "Created at: " + (new Time(update.getString("created_at"))).getTextFormat() + "\n";
                        content += "Updated at: " + (new Time(update.getString("updated_at"))).getTextFormat() + "\n";

                        JSONArray descriptions = update.getJSONArray("translations");
                        for(int k = 0; k < descriptions.length(); k++) {
                            JSONObject description = descriptions.getJSONObject(k);

                            if(description.getString("locale").equals(language)) {
                                content += "Message: " + description.getString("content") + "\n";
                                break;
                            }
                        }
                    }
                }

                embed.addField("Maintenances", content, true);
            }

            event.replyEmbeds(embed.build()).queue();
        } catch(RequestFailedException e) {

        }
    }
}
