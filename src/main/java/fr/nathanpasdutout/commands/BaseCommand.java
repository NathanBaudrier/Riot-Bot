package fr.nathanpasdutout.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public abstract class BaseCommand extends ListenerAdapter {
    protected String name;
    protected String description;
    protected OptionData[] options;

    public BaseCommand(String name, String description, OptionData... options) {
        this.name = name;
        this.description = description;
        this.options = options;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals(name)) return;
        if (event.getMember().getUser().isBot()) return;

        this.execute(event);
    }

    public SlashCommandData getCommandData() {
        return (this.options == null) ? Commands.slash(this.name, this.description) : Commands.slash(this.name, this.description).addOptions(this.options);
    }

    public abstract void execute(SlashCommandInteractionEvent event);
}
