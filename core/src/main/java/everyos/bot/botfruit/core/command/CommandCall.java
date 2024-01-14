package everyos.bot.botfruit.core.command;

import reactor.core.publisher.Mono;

public interface CommandCall {

	Mono<Void> execute(CommandContext context) throws Exception;

}
