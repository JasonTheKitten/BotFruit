package everyos.bot.botfruit.core.command.imp;

import java.lang.reflect.Method;

import everyos.bot.botfruit.core.command.CommandCall;
import everyos.bot.botfruit.core.command.CommandCallOption;
import everyos.bot.botfruit.core.command.CommandContext;
import everyos.bot.botfruit.core.command.CommandInvocation;
import reactor.core.publisher.Mono;

public class AnnotatedCommandCallOption implements CommandCallOption {

	private final Class<?> commandClass;
	private final Method method;

	public AnnotatedCommandCallOption(Class<?> commandClass, Method method) {
		this.commandClass = commandClass;
		this.method = method;
	}

	@Override
	public CommandCall getCommandCall(CommandInvocation invocation) {
		return new CommandCall() {
			@Override
			@SuppressWarnings("unchecked")
			public Mono<Void> execute(CommandContext context) throws Exception {
				Object instance = commandClass.getConstructor().newInstance();
				return (Mono<Void>) method.invoke(instance);
			}
		};
	}

	public static AnnotatedCommandCallOption create(Class<?> commandClass, Method method) {
		return new AnnotatedCommandCallOption(commandClass, method);
	}

}
