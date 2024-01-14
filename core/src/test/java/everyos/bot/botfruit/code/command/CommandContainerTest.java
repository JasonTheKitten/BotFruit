package everyos.bot.botfruit.code.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import everyos.bot.botfruit.code.command.test.MethodAnnotatedCommand;
import everyos.bot.botfruit.core.command.Command;
import everyos.bot.botfruit.core.command.CommandContainer;

public class CommandContainerTest {
	
	@Test
	@DisplayName("Empty container is empty")
	public void emptyContainerIsEmpty() {
		CommandContainer container = CommandContainer.create();
		Assertions.assertEquals(0, container.getCommands().size());
	}

	@Test
	@DisplayName("Can add command to container")
	public void canAddCommandToContainer() {
		CommandContainer container = CommandContainer.create();
		container.addCommand(Mockito.mock(Command.class));
		Assertions.assertEquals(1, container.getCommands().size());
	}

	@Test
	@DisplayName("Can add annotated command to container")
	public void canAddAnnotatedCommandToContainer() {
		CommandContainer container = CommandContainer.create();
		container.addAnnotatedCommand(MethodAnnotatedCommand.class);
		Assertions.assertEquals(1, container.getCommands().size());
	}

	@Test
	@DisplayName("Can get command from container by name")
	public void canGetCommandFromContainerByName() {
		CommandContainer container = CommandContainer.create();
		Command command = Mockito.mock(Command.class);
		Mockito.when(command.getName()).thenReturn("test");
		container.addCommand(command);
		Assertions.assertEquals(command, container.getCommandByName("test", name -> name).get());
	}

}
