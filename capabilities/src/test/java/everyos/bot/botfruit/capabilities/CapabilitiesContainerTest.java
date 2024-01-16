package everyos.bot.botfruit.capabilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CapabilitiesContainerTest {
	
	@Test
	@DisplayName("Container built without providers does not have providers")
	public void containerBuiltWithoutProvidersDoesNotHaveProviders() {
		CapabilitiesContainer container = CapabilitiesContainer.builder().build();
		Assertions.assertEquals(0, container.getPossibleCapabilityProviders(String.class).size());
	}

	@Test
	@DisplayName("Container built with provider has provider")
	@SuppressWarnings("unchecked")
	public void containerBuiltWithProviderHasProvider() {
		CapabilityProvider<String> provider = Mockito.mock(CapabilityProvider.class);
		CapabilitiesContainer container = CapabilitiesContainer.builder()
			.registerCapabilityProvider(String.class, provider)
			.build();
		Assertions.assertEquals(1, container.getPossibleCapabilityProviders(String.class).size());
	}

	@Test
	@DisplayName("Container built with alternate provider has alternate provider")
	@SuppressWarnings("unchecked")
	public void containerBuiltWithAlternateProviderHasAlternateProvider() {
		CapabilityProvider<String> provider = Mockito.mock(CapabilityProvider.class);
		CapabilityProvider<String> provider2 = Mockito.mock(CapabilityProvider.class);
		CapabilitiesContainer container = CapabilitiesContainer.builder()
			.registerCapabilityProvider(String.class, provider)
			.registerCapabilityProvider(String.class, provider2)
			.build();
		Assertions.assertEquals(2, container.getPossibleCapabilityProviders(String.class).size());
		Assertions.assertEquals(provider, container.getPossibleCapabilityProviders(String.class).get(0));
		Assertions.assertEquals(provider2, container.getPossibleCapabilityProviders(String.class).get(1));
	}

	@Test
	@DisplayName("Container can prefer alternative provider")
	@SuppressWarnings("unchecked")
	public void containerCanPreferAlternativeProvider() {
		CapabilityProvider<String> provider = Mockito.mock(CapabilityProvider.class);
		CapabilityProvider<String> provider2 = Mockito.mock(CapabilityProvider.class);
		CapabilitiesContainer container = CapabilitiesContainer.builder()
			.registerCapabilityProvider(String.class, provider)
			.preferCapabilityProvider(String.class, provider2)
			.build();
		Assertions.assertEquals(2, container.getPossibleCapabilityProviders(String.class).size());
		Assertions.assertEquals(provider2, container.getPossibleCapabilityProviders(String.class).get(0));
		Assertions.assertEquals(provider, container.getPossibleCapabilityProviders(String.class).get(1));
	}

}
