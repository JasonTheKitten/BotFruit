package everyos.bot.botfruit.discord4j.imp;

import java.util.List;

import discord4j.discordjson.json.ApplicationCommandRequest;
import everyos.bot.botfruit.core.command.Command;
import everyos.bot.botfruit.core.command.CommandContainer;

public final class SlashCommandGenerator {

	private SlashCommandGenerator() {}

    public static List<ApplicationCommandRequest> generateSlashCommands(CommandContainer defaultCommands) {
		return defaultCommands.getCommands()
			.stream()
			.map(SlashCommandGenerator::generateSlashCommand)
			.toList();
    }

	private static ApplicationCommandRequest generateSlashCommand(Command command) {
		return ApplicationCommandRequest.builder()
			.name(command.getName())
			.description("An autogenerated slash command")
			.build();
	}

}