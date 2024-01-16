package everyos.bot.botfruit.capabilities.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import everyos.bot.botfruit.capabilities.CapabilitiesContainer;
import everyos.bot.botfruit.capabilities.CapabilitiesContainerBuilder;
import everyos.bot.botfruit.capabilities.CapabilityProvider;

public class CapabilitiesContainerBuilderImp implements CapabilitiesContainerBuilder {

	private final Map<Class<?>, List<CapabilityProvider<?>>> providers = new HashMap<>();

	@Override
	public <T> CapabilitiesContainerBuilder registerCapabilityProvider(Class<T> capabilityClass, CapabilityProvider<T> capabilityProvider) {
		providers.computeIfAbsent(capabilityClass, _1 -> new ArrayList<>()).add(capabilityProvider);
		
		return this;
	}

	@Override
	public <T> CapabilitiesContainerBuilder preferCapabilityProvider(Class<T> capabilityClass, CapabilityProvider<T> capabilityProvider) {
		providers.computeIfAbsent(capabilityClass, _1 -> new ArrayList<>()).add(0, capabilityProvider);
		
		return this;
	}

	@Override
	public CapabilitiesContainer build() {
		return new CapabilitiesContainerImp(Map.copyOf(providers));
	}
	
}
