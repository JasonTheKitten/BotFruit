package everyos.bot.botfruit.code.command.test;

import everyos.bot.botfruit.core.annotation.AnnotatedCommand;
import everyos.bot.botfruit.core.annotation.CommandEntry;
import reactor.core.publisher.Mono;

@AnnotatedCommand(name="htest")
public class HeaderAnnotatedCommand {
	
	@CommandEntry
	public Mono<Void> invoke() {
		return Mono.empty();
	}

	@CommandEntry
	public Mono<Void> invoke2() {
		return Mono.empty();
	}

}
