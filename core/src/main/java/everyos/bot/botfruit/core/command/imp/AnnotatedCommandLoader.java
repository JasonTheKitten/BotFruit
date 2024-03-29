package everyos.bot.botfruit.core.command.imp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import everyos.bot.botfruit.core.annotation.AnnotatedCommand;
import everyos.bot.botfruit.core.annotation.CommandEntry;
import everyos.bot.botfruit.core.command.Command;
import everyos.bot.botfruit.core.command.CommandCallOption;

public final class AnnotatedCommandLoader {

	private AnnotatedCommandLoader() {}

	// TODO: Make sure there aren't conflicting CommandEntry and AnnotationCommand annotations

	public static List<Command> load(Class<?> commandClass) {
		List<Command> methodCommands = loadMethodCommands(commandClass);
		Optional<Command> classCommand = loadClassCommand(commandClass);

		List<Command> allCommands = new ArrayList<>(methodCommands.size() + 1);
		allCommands.addAll(methodCommands);
		classCommand.ifPresent(allCommands::add);

		return allCommands;
	}

	private static List<Command> loadMethodCommands(Class<?> commandClass) {
		List<Command> commands = new ArrayList<>(1);
		for (Method method: commandClass.getMethods()) {
			Optional<Command> annotation = loadMethodCommand(commandClass, method);
			if (annotation.isPresent()) {
				commands.add(annotation.get());
			}
		}

		return commands;
	}

	private static Optional<Command> loadMethodCommand(Class<?> commandClass, Method method) {
		AnnotatedCommand annotation = method.getAnnotation(AnnotatedCommand.class);
		if (annotation == null) {
			return Optional.empty();
		}

		CommandCallOption commandCallOption = createCommandCallOption(commandClass, method);
		return Optional.of(new AnnotatedCommandImp(annotation.name(), List.of(commandCallOption)));
	}


	private static Optional<Command> loadClassCommand(Class<?> commandClass) {
		AnnotatedCommand annotation = commandClass.getAnnotation(AnnotatedCommand.class);
		if (annotation == null) {
			return Optional.empty();
		}

		List<CommandCallOption> commandCallOptions = findCommandCallOptions(commandClass);
		return Optional.of(new AnnotatedCommandImp(annotation.name(), commandCallOptions));
	}

	private static List<CommandCallOption> findCommandCallOptions(Class<?> commandClass) {
		List<CommandCallOption> commandCallOptions = new ArrayList<>();
		for (Method method: commandClass.getMethods()) {
			if (method.getAnnotation(CommandEntry.class) == null) {
				continue;
			}

			CommandCallOption commandCallOption = createCommandCallOption(commandClass, method);
			commandCallOptions.add(commandCallOption);
		}

		return commandCallOptions;
	}

	private static CommandCallOption createCommandCallOption(Class<?> commandClass, Method method) {
		return AnnotatedCommandCallOption.create(commandClass, method);
	}
	
}
