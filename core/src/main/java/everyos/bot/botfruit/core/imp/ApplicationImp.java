package everyos.bot.botfruit.core.imp;

import everyos.bot.botfruit.core.Application;
import everyos.bot.botfruit.core.command.CommandContainer;

public class ApplicationImp implements Application {

	private final CommandContainer defaultCommands;

	public ApplicationImp(CommandContainer defaultCommands) {
		this.defaultCommands = defaultCommands;
	}

	@Override
	public CommandContainer getDefaultCommands() {
		return defaultCommands;
	}
	
}
