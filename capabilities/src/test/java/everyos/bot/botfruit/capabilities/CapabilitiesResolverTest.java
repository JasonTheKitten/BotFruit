package everyos.bot.botfruit.capabilities;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CapabilitiesResolverTest {
	
	@Test
	@DisplayName("Can create capability with one option and no dependencies")
	@SuppressWarnings("unchecked")
	public void canCreateCapabilityWithOneOptionAndNoDependencies() {
		CapabilityProvider<String> provider = Mockito.mock(CapabilityProvider.class);	
		Mockito.when(provider.getDependencies()).thenReturn(List.of());
		Mockito.when(provider.createCapability(Mockito.any())).thenReturn("test");
		CapabilitiesContainer container = Mockito.mock(CapabilitiesContainer.class);
		Mockito.when(container.getPossibleCapabilityProviders(String.class)).thenReturn(List.of(provider));
		CapabilitiesResolver resolver = CapabilitiesResolver.create(container);
		String capability = resolver.createCapability(String.class).get().getCapability();
		Assertions.assertEquals("test", capability);
	}

}
