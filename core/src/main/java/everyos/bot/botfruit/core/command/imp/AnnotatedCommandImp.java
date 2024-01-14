package everyos.bot.botfruit.core.command.imp;

import java.util.List;

import everyos.bot.botfruit.core.command.Command;
import everyos.bot.botfruit.core.command.CommandCallOption;

public class AnnotatedCommandImp implements Command {

	private final String name;
	private final List<CommandCallOption> commandCallOptions;

	public AnnotatedCommandImp(String name, List<CommandCallOption> commandCallOptions) {
		this.name = name;
		this.commandCallOptions = commandCallOptions;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<CommandCallOption> getCommandCallOptions() {
		return commandCallOptions;
	}

	public static List<AnnotatedCommandImp> create(Class<?> commandClass) {
		return AnnotatedCommandLoader.load(commandClass);
	}

}
