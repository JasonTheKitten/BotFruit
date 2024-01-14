package everyos.bot.botfruit.core.command;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import everyos.bot.botfruit.core.command.imp.CommandContainerImp;

/**
 * A command container allows for grouping together a collection of
 * many commands. This is useful when one then wants to allow those
 * commands to be available to use together.
 */
public interface CommandContainer {
	
	/**
	 * Add a command to the container. The class should be declared by
	 * implementing the Command interface. Annotations will not be available.
	 * @param command The command to add
	 */
	void addCommand(Command command);

	/**
	 * Add a command to the container. The command should be declared
	 * using annotations.
	 * @param clazz The class of the command to add
	 */
	void addAnnotatedCommand(Class<?> clazz);

	/**
	 * Get all the commands in the container.
	 * @return The commands in the container
	 */
	List<Command> getCommands();

	/**
	 * Get a command from the container by name.
	 * @param string The name of the command to get
	 * @param localizer A function that localizes the name of the command
	 *  by mapping the label to the localized name
	 * @return The command, if it exists
	 */
	Optional<Command> getCommandByName(String string, Function<String, String> localizer);

	/**
	 * Create a new command container.
	 * @return The new command container
	 */
	public static CommandContainer create() {
		return new CommandContainerImp();
	}

}
