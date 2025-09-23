package fr.nathanpasdutout.commands.lol;

import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.LolData;
import fr.nathanpasdutout.utils.Utils;
import jdk.jshell.execution.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Color;

public class StatusCommand extends BaseCommand {

    public StatusCommand() {
        super("status", "Shows servers status (maintenances / incidents).");
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
                embed.setDescription("\uD83D\uDFE2 Everything is ok.");
                return;
            }

            embed.setColor(Color.RED);

            if(!maintenances.isEmpty()) {
                this.addMaintenanceFormatToEmbed(embed, maintenances);
            }

            if(!incidents.isEmpty()) {
                this.addIncidentFormatToEmbed(embed, incidents);
            }

            event.replyEmbeds(embed.build()).queue();
        } catch(RequestFailedException e) {

        }
    }

    private void addMaintenanceFormatToEmbed(EmbedBuilder embed, JSONArray maintenances) {
        String content = "";
        String language = "en_US";

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

            content += "- **Region:** " + maintenance.getString("name") + "\n";
            content += "- **Status:** " + Utils.capitalizeFirstLetter(maintenance.getString("maintenance_status")) + "\n";
            content += "- **Concerned platforms:** " + maintenance.getJSONArray("platforms").join(",").replace('"', ' ') + "\n";

            content += "\n- **Updates:**\n";

            JSONArray updates = maintenance.getJSONArray("updates");
            for(int j = 0; j < updates.length(); j++) {
                JSONObject update = updates.getJSONObject(j);
                content += "Created at: " + Utils.getDateFormat(update.getString("created_at")) + "\n";
                content += "Updated at: " + Utils.getDateFormat(update.getString("updated_at")) + "\n";

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

    private void addIncidentFormatToEmbed(EmbedBuilder embed, JSONArray incidents) {
        StringBuilder content = new StringBuilder();
        String language = "en_US";

        for(int i = 0; i < incidents.length(); i++) {
            JSONObject incident = incidents.getJSONObject(i);

            JSONArray titles = incident.getJSONArray("titles");
            for(int j = 0; j < titles.length(); j++) {
                JSONObject title = titles.getJSONObject(j);
                if(title.getString("locale").equals(language)) {
                    content.append("- **Title:** ").append(title.getString("content")).append("\n");
                    break;
                }
            }

            content.append("- **Created at:** ").append(Utils.getDateFormat(incident.getString("created_at"))).append("\n");
            content.append("- **Updated at:** ").append(Utils.getDateFormat(incident.getString("updated_at"))).append("\n");
            content.append("- **Archived at:** ").append(Utils.getDateFormat(incident.getString("archived_at"))).append("\n");
            content.append("- **Active:** ").append(Utils.capitalizeFirstLetter(incident.getString("active"))).append("\n");
            content.append("- **Severity:** ").append(Utils.capitalizeFirstLetter(incident.getString("incident_severity"))).append("\n");
            content.append("- **Concerned platforms:** ").append(incident.getJSONArray("platforms").join(",").replace('"', ' ')).append("\n");

            content.append("\n- **Updates:**\n");

            JSONArray updates = incident.getJSONArray("updates");
            for(int j = 0; j < updates.length(); j++) {
                JSONObject update = updates.getJSONObject(j);
                content.append("Created at: ").append(Utils.getDateFormat(update.getString("created_at"))).append("\n");
                content.append("Updated at: ").append(Utils.getDateFormat(update.getString("updated_at"))).append("\n");

                JSONArray descriptions = update.getJSONArray("translations");
                for(int k = 0; k < descriptions.length(); k++) {
                    JSONObject description = descriptions.getJSONObject(k);

                    if(description.getString("locale").equals(language)) {
                        content.append("Message: ").append(description.getString("content")).append("\n");
                        break;
                    }
                }
            }
        }

        embed.addField("Incidents", content.toString(), true);
    }
}
