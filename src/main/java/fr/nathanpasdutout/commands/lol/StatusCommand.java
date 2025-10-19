package fr.nathanpasdutout.commands.lol;

import fr.nathanpasdutout.commands.BaseCommand;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.LolData;
import fr.nathanpasdutout.riotapi.elements.status.Data;
import fr.nathanpasdutout.riotapi.elements.status.Status;

import fr.nathanpasdutout.riotapi.elements.status.Update;
import fr.nathanpasdutout.utils.Utils;
import jdk.jshell.execution.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class StatusCommand extends BaseCommand {

    public StatusCommand() {
        super("status", "Shows servers status (maintenances / incidents).");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            Status status = LolData.getStatusData();

            EmbedBuilder embed = new EmbedBuilder();

            if(status.getMaintenances().length == 0 && status.getIncidents().length == 0) {

                embed.setColor(Color.GREEN);
                embed.setDescription("\uD83D\uDFE2 Everything is ok.");
                event.replyEmbeds(embed.build()).queue();

                return;
            }

            embed.setColor(Color.RED);
            embed.setTitle("LOL STATUS");
            embed.setDescription("Region: " + status.getRegion() + " (" + status.getId() + ")");

            String language = "en_US";

            String content;

            if(status.getMaintenances().length != 0) {
                content = "";

                for(Data maintenance : status.getMaintenances()) {
                    content += "- Title: " + maintenance.getContent(language).getText() + "\n";
                    content += "- Created At: " + Utils.getDateFormat(maintenance.getCreatedAt()) + "\n";
                    content += "- Updated At: " + Utils.getDateFormat(maintenance.getUpdatedAt()) + "\n";
                    content += "- Archive At: " + Utils.getDateFormat(maintenance.getArchivedAt()) + "\n";
                    content += "- Concerned Platforms: " + String.join(", ", maintenance.getPlatforms()) + "\n";
                    content += "- Status: " + maintenance.getStatus() + "\n";
                    content += "- Severity: " + maintenance.getSeverity() + "\n";

                    content += "\n - Updates - \n";

                    for(Update update : maintenance.getUpdates()) {
                        content += "\n";
                        content += "| Created At: " + Utils.getDateFormat(update.getCreatedAt()) + "\n";
                        content += "| Updated At: " + Utils.getDateFormat(update.getUpdatedAt()) + "\n";
                        content += "| Author: " + update.getAuthor();
                    }
                }

                embed.addField("Maintenances", content, false);
            }

            if(status.getIncidents().length != 0) {
                content = "";
                for(Data incident : status.getIncidents()) {
                    content += "- Title: " + incident.getContent(language).getText() + "\n";
                    content += "- Created At: " + Utils.getDateFormat(incident.getCreatedAt()) + "\n";
                    content += "- Updated At: " + Utils.getDateFormat(incident.getUpdatedAt()) + "\n";
                    content += "- Archive At: " + Utils.getDateFormat(incident.getArchivedAt()) + "\n";
                    content += "- Concerned Platforms: " + String.join(", ", incident.getPlatforms()) + "\n";
                    content += "- Status: " + incident.getStatus() + "\n";
                    content += "- Severity: " + incident.getSeverity() + "\n";

                    content += "\n - Updates - \n";

                    for(Update update : incident.getUpdates()) {
                        content += "\n";
                        content += "| Created At: " + Utils.getDateFormat(update.getCreatedAt()) + "\n";
                        content += "| Updated At: " + Utils.getDateFormat(update.getUpdatedAt()) + "\n";
                        content += "| Author: " + update.getAuthor();
                    }
                }

                embed.addField("Incidents", content, false);
            }

            event.replyEmbeds(embed.build()).queue();
        } catch(RequestFailedException exception) {
            System.err.println(exception.getMessage());
        }
    }

}
