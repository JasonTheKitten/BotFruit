package everyos.bot.botfruit.core.command.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import everyos.bot.botfruit.core.command.Command;
import everyos.bot.botfruit.core.command.CommandContainer;

public class CommandContainerImp implements CommandContainer {

	private final List<Command> commands = new ArrayList<>();

	@Override
	public void addCommand(Command command) {
		commands.add(command);
	}

	@Override
	public void addAnnotatedCommand(Class<?> clazz) {
		List<Command> commands = AnnotatedCommandLoader.load(clazz);
		if (commands.isEmpty()) {
			throw new RuntimeException("No commands found in class");
		}
		
		this.commands.addAll(commands);
	}

	@Override
	public List<Command> getCommands() {
		return commands;
	}

	@Override
	public Optional<Command> getCommandByName(String string, Function<String, String> localizer) {
		return commands.stream()
			.filter(command -> command.getName().equals(localizer.apply(string)))
			.findFirst();
	}
	
}
