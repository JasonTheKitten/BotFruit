package everyos.bot.botfruit.discord4j;

import everyos.bot.botfruit.core.Application;
import everyos.bot.botfruit.discord4j.imp.BotFruitD4JClientImp;
import reactor.core.publisher.Mono;

public interface BotFruitD4JClient {
	
	Mono<Void> start();

	static BotFruitD4JClient create(Application application, String token) {
		return new BotFruitD4JClientImp(application, token);
	}

}
