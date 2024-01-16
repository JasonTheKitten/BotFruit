package everyos.bot.botfruit.capabilities;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ComposedCapabilitiesContainerTest {

	@Test
	@DisplayName("Composed container without parents does not have providers")
	public void composedContainerWithoutParentsDoesNotHaveProviders() {
		CapabilitiesContainer container = CapabilitiesContainer.compose();
		Assertions.assertEquals(0, container.getPossibleCapabilityProviders(String.class).size());
	}

	@Test
	@DisplayName("Composed container with parent has providers from parent")
	@SuppressWarnings("unchecked")
	public void composedContainerWithParentHasProvidersFromParent() {
		CapabilityProvider<String> provider1 = Mockito.mock(CapabilityProvider.class);
		CapabilityProvider<String> provider2 = Mockito.mock(CapabilityProvider.class);
		CapabilitiesContainer parent = Mockito.mock(CapabilitiesContainer.class);
		Mockito.when(parent.getPossibleCapabilityProviders(String.class)).thenReturn(
			List.of(provider1, provider2)
		);
		CapabilitiesContainer container = CapabilitiesContainer.compose(parent);
		Assertions.assertEquals(2, container.getPossibleCapabilityProviders(String.class).size());
		Assertions.assertEquals(provider1, container.getPossibleCapabilityProviders(String.class).get(0));
		Assertions.assertEquals(provider2, container.getPossibleCapabilityProviders(String.class).get(1));
	}

	@Test
	@DisplayName("Composed container with multiple parents has providers from all parents")
	@SuppressWarnings("unchecked")
	public void composedContainerWithMultipleParentsHasProvidersFromAllParents() {
		CapabilityProvider<String> provider1 = Mockito.mock(CapabilityProvider.class);
		CapabilityProvider<String> provider2 = Mockito.mock(CapabilityProvider.class);
		CapabilityProvider<String> provider3 = Mockito.mock(CapabilityProvider.class);
		CapabilityProvider<String> provider4 = Mockito.mock(CapabilityProvider.class);
		CapabilitiesContainer parent1 = Mockito.mock(CapabilitiesContainer.class);
		CapabilitiesContainer parent2 = Mockito.mock(CapabilitiesContainer.class);
		Mockito.when(parent1.getPossibleCapabilityProviders(String.class)).thenReturn(
			List.of(provider1, provider2)
		);
		Mockito.when(parent2.getPossibleCapabilityProviders(String.class)).thenReturn(
			List.of(provider3, provider4)
		);
		CapabilitiesContainer container = CapabilitiesContainer.compose(parent1, parent2);
		Assertions.assertEquals(4, container.getPossibleCapabilityProviders(String.class).size());
		Assertions.assertEquals(provider1, container.getPossibleCapabilityProviders(String.class).get(0));
		Assertions.assertEquals(provider2, container.getPossibleCapabilityProviders(String.class).get(1));
		Assertions.assertEquals(provider3, container.getPossibleCapabilityProviders(String.class).get(2));
		Assertions.assertEquals(provider4, container.getPossibleCapabilityProviders(String.class).get(3));
	}
	
}
