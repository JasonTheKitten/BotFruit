package everyos.bot.botfruit.code.command;

import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import everyos.bot.botfruit.code.command.test.HeaderAnnotatedCommand;
import everyos.bot.botfruit.core.command.CommandCall;
import everyos.bot.botfruit.core.command.CommandContext;
import everyos.bot.botfruit.core.command.CommandInvocation;
import everyos.bot.botfruit.core.command.imp.AnnotatedCommandCallOption;

public class AnnotatedCommandCalledOptionTest {
	
	@Test
	@DisplayName("Can invoke annotated command call option")
	public void canInvokeAnnotatedCommandCallOption() throws Exception {
		AnnotatedCommandCallOption commandCallOption = selectCommandCallOption(HeaderAnnotatedCommand.class, "invoke");
		CommandCall commandCall = commandCallOption.getCommandCall(Mockito.mock(CommandInvocation.class));
		commandCall.execute(Mockito.mock(CommandContext.class)).block();
	}

	private AnnotatedCommandCallOption selectCommandCallOption(
		Class<HeaderAnnotatedCommand> commandClass, String methodName
	) throws NoSuchMethodException, SecurityException {
		Method method = commandClass.getMethod(methodName);
		return AnnotatedCommandCallOption.create(commandClass, method);
	}

}
