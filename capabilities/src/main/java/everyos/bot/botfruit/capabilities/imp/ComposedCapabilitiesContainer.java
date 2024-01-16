package everyos.bot.botfruit.capabilities.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import everyos.bot.botfruit.capabilities.CapabilitiesContainer;
import everyos.bot.botfruit.capabilities.CapabilityProvider;

public class ComposedCapabilitiesContainer implements CapabilitiesContainer {

	private final Map<Class<?>, List<CapabilityProvider<?>>> providersCache = new HashMap<>();
	private final CapabilitiesContainer[] containers;

	public ComposedCapabilitiesContainer(CapabilitiesContainer... containers) {
		this.containers = containers;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public <T> List<CapabilityProvider<T>> getPossibleCapabilityProviders(Class<T> capabilityClass) {
		return (List) providersCache.computeIfAbsent(capabilityClass, _1 -> {
			List<CapabilityProvider<?>> providers = new ArrayList<>();
			for (CapabilitiesContainer container: containers) {
				List<CapabilityProvider<T>> containerProviders = container.getPossibleCapabilityProviders(capabilityClass);
				for (CapabilityProvider<T> provider: containerProviders) {
					if (providers.contains(provider)) continue;
					providers.add(provider);
				}
			}
			
			return providers;
		});
	}
	
}
