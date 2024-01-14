package everyos.bot.botfruit.core;

import everyos.bot.botfruit.core.command.CommandContainer;
import everyos.bot.botfruit.core.imp.ApplicationImp;

/**
 * An application builder allows for setting the properties that
 * will be used to create an application.
 */
public final class ApplicationBuilder {

	private CommandContainer defaultCommands;
	
	private ApplicationBuilder() {}

	/**
	 * Set the default commands for the application.
	 * These will allow commands to be used via the most universally applicable
	 * input method. For example, on a Discord bot, these would likely be slash commands
	 * or text commands, but they would not be user menu commands. The specific backend may
	 * provide more specific ways to give users access to commands.
	 * @param commands
	 */
	public void setDefaultCommands(CommandContainer commands) {
		this.defaultCommands = commands;
	}

	/**
	 * Build the application.
	 * @return The built application
	 */
	public Application build() {
		return new ApplicationImp(defaultCommands);
	}


	/**
	 * Obtain a new application builder.
	 * @return The new application builder
	 */
	public static ApplicationBuilder create() {
		return new ApplicationBuilder();
	}

}
