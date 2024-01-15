package everyos.bot.botfruit.discord4j.imp;

import java.util.List;
import java.util.Optional;

import org.reactivestreams.Publisher;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import everyos.bot.botfruit.core.Application;
import everyos.bot.botfruit.core.command.Command;
import everyos.bot.botfruit.core.command.CommandCallOption;
import everyos.bot.botfruit.core.command.CommandContainer;
import everyos.bot.botfruit.core.command.CommandContext;
import everyos.bot.botfruit.core.command.CommandInvocation;
import everyos.bot.botfruit.discord4j.BotFruitD4JClient;
import reactor.core.publisher.Mono;

public class BotFruitD4JClientImp implements BotFruitD4JClient {

	private final Application application;
	private final String token;

	public BotFruitD4JClientImp(Application application, String token) {
		this.application = application;
		this.token = token;
	}

	@Override
	public Mono<Void> start() {
		return DiscordClientBuilder.create(token)
			.build()
			.withGateway(this::onConnect);
	}

	private Mono<Void> onConnect(GatewayDiscordClient client) {
		return Mono.when(
			installDefaultSlashCommands(client, application.getDefaultCommands()),
			setupCommandListeners(client, application.getDefaultCommands())
		);
	}

	private Mono<Void> installDefaultSlashCommands(GatewayDiscordClient client, CommandContainer defaultCommands) {
		RestClient restClient = client.getRestClient();
		List<ApplicationCommandRequest> slashCommands = SlashCommandGenerator.generateSlashCommands(defaultCommands);
		return restClient.getApplicationId()
			.flatMapMany(applicationId ->
				restClient.getApplicationService().bulkOverwriteGlobalApplicationCommand(applicationId, slashCommands))
			.then();

	}

	private Publisher<?> setupCommandListeners(GatewayDiscordClient client, CommandContainer defaultCommands) {
		return setupSlashCommandListeners(client, defaultCommands);
	}

	private Publisher<?> setupSlashCommandListeners(GatewayDiscordClient client, CommandContainer defaultCommands) {
		return client.getEventDispatcher().on(ChatInputInteractionEvent.class)
			.flatMap(event -> {
				String commandName = event.getCommandName();
				Optional<Command> command = defaultCommands.getCommandByName(commandName, name -> name);
				if (command.isEmpty()) {
					// TODO: Log
					return Mono.empty();
				}

				List<CommandCallOption> callOptions = command.get().getCommandCallOptions();
				if (callOptions.size() != 1) {
					return Mono.error(new RuntimeException("Only one command call option is supported right now"));
				}

				CommandCallOption callOption = callOptions.get(0);
				return callOption
					.getCommandCall(new CommandInvocation() {})
					.execute(new CommandContext() {})
					.then(event.reply("(Slash commands are working, yay!!!)"));
			});
	}
	
}
