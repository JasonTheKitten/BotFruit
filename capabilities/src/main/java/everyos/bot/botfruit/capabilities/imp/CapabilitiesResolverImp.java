package everyos.bot.botfruit.capabilities.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import everyos.bot.botfruit.capabilities.CapabilitiesContainer;
import everyos.bot.botfruit.capabilities.CapabilitiesResolver;
import everyos.bot.botfruit.capabilities.CapabilityCreationContext;
import everyos.bot.botfruit.capabilities.CapabilityHandle;
import everyos.bot.botfruit.capabilities.CapabilityProvider;
import everyos.bot.botfruit.capabilities.exceptions.UnmetDependencyException;

public class CapabilitiesResolverImp implements CapabilitiesResolver {

	private final Map<Class<?>, Optional<CapabilityHandle<?>>> cache = new HashMap<>();

	private final CapabilitiesContainer container;

	public CapabilitiesResolverImp(CapabilitiesContainer container) {
		this.container = container;
	}

	@Override
	public <T> Optional<CapabilityHandle<T>> createCapability(Class<T> capabilityClass) {
		List<CapabilityProvider<T>> providers = container.getPossibleCapabilityProviders(capabilityClass);
		for (CapabilityProvider<T> provider: providers) {
			Optional<CapabilityHandle<T>> handle = createCapabilityWithProvider(provider);
			if (handle.isPresent()) return cache(capabilityClass, handle);
		}

		return cache(capabilityClass, Optional.empty());
	}

	@Override
	public void close() {
		for (Optional<CapabilityHandle<?>> handle: cache.values()) {
			handle.ifPresent(CapabilityHandle::close);
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T> Optional<CapabilityHandle<T>> cache(Class<T> capabilityClass, Optional<CapabilityHandle<T>> handle) {
		cache.put(capabilityClass, (Optional) handle);

		return handle;
	}

	private <T> Optional<CapabilityHandle<T>> createCapabilityWithProvider(CapabilityProvider<T> provider) {
		CapabilityCreationContext context = null;
		try {
			return Optional.of(CapabilityHandleImp.create(provider.createCapability(context)));
		} catch (UnmetDependencyException e) {
			return Optional.empty();
		}
	}
	
}
