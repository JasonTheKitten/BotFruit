package everyos.bot.botfruit.core.command;

import java.util.List;

public interface Command {

	String getName();
	
	List<CommandCallOption> getCommandCallOptions();

}
