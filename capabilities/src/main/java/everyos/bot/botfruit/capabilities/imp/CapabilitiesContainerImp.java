package everyos.bot.botfruit.capabilities.imp;

import java.util.List;
import java.util.Map;

import everyos.bot.botfruit.capabilities.CapabilitiesContainer;
import everyos.bot.botfruit.capabilities.CapabilityProvider;

public class CapabilitiesContainerImp implements CapabilitiesContainer {

	private final Map<Class<?>, List<CapabilityProvider<?>>> providers;

	public CapabilitiesContainerImp(Map<Class<?>, List<CapabilityProvider<?>>> providers) {
		this.providers = providers;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<CapabilityProvider<T>> getPossibleCapabilityProviders(Class<T> capabilityClass) {
		return (List<CapabilityProvider<T>>) (List) providers.getOrDefault(capabilityClass, List.of());
	}

}
