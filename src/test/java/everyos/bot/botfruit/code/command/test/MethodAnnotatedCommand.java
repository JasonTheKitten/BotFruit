package everyos.bot.botfruit.code.command.test;

import everyos.bot.botfruit.core.annotation.AnnotatedCommand;
import reactor.core.publisher.Mono;

public class MethodAnnotatedCommand {
	
	@AnnotatedCommand(name="mtest")
	public Mono<Void> test() {
		return Mono.empty();
	}

}
