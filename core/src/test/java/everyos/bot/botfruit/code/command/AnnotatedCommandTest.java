package everyos.bot.botfruit.code.command;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import everyos.bot.botfruit.code.command.test.HeaderAnnotatedCommand;
import everyos.bot.botfruit.code.command.test.MethodAnnotatedCommand;
import everyos.bot.botfruit.core.command.Command;
import everyos.bot.botfruit.core.command.imp.AnnotatedCommandImp;

public class AnnotatedCommandTest {
	
	@Test
	@DisplayName("Class with no command annotations does not return instances")
	public void classWithNoCommandAnnotationsDoesNotReturnInstances() {
		List<AnnotatedCommandImp> commands = AnnotatedCommandImp.create(Object.class);
		Assertions.assertEquals(0, commands.size());
	}

	@Test
	@DisplayName("Class with one method command annotation returns one instance")
	public void classWithOneMethodCommandAnnotationReturnsOneInstance() {
		List<AnnotatedCommandImp> commands = AnnotatedCommandImp.create(MethodAnnotatedCommand.class);
		Assertions.assertEquals(1, commands.size());
		Command command = commands.get(0);
		Assertions.assertEquals("mtest", command.getName());
	}

	@Test
	@DisplayName("Class with header command annotation returns one instance")
	public void classWithHeaderCommandAnnotationReturnsOneInstance() {
		List<AnnotatedCommandImp> commands = AnnotatedCommandImp.create(HeaderAnnotatedCommand.class);
		Assertions.assertEquals(1, commands.size());
		Command command = commands.get(0);
		Assertions.assertEquals("htest", command.getName());
	}

	@Test
	@DisplayName("Class with header command annotation and two command entries has two command call options")
	public void classWithHeaderCommandAnnotationAndTwoCommandEntriesHasTwoCommandCallOptions() {
		List<AnnotatedCommandImp> commands = AnnotatedCommandImp.create(HeaderAnnotatedCommand.class);
		Assertions.assertEquals(1, commands.size());
		Command command = commands.get(0);
		Assertions.assertEquals(2, command.getCommandCallOptions().size());
	}

	@Test
	@DisplayName("Class with method command annotation has one command call option")
	public void classWithMethodCommandAnnotationHasOneCommandCallOption() {
		List<AnnotatedCommandImp> commands = AnnotatedCommandImp.create(MethodAnnotatedCommand.class);
		Assertions.assertEquals(1, commands.size());
		Command command = commands.get(0);
		Assertions.assertEquals(1, command.getCommandCallOptions().size());
	}

}
