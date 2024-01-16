package everyos.bot.botfruit.capabilities;

import java.util.List;

/**
 * A capability provider is capable of creating a capability and
 * managing it's lifecycle and security policies.
 */
public interface CapabilityProvider<T> {
	
	/**
	 * Create an instance of the capability provided by this provider.
	 * Note that the capability is intended to be short-lived, and any
	 * long-lived state should be stored in the provider or other
	 * backing store.
	 * @return The capability
	 */
	T createCapability(CapabilityCreationContext context);

	/**
	 * Get the dependencies of this capability provider. The capability
	 * provider can only be used to create a capability once all of
	 * the dependencies are met.
	 * @return The dependencies that must be met to load a capability
	 */
	List<Class<T>> getDependencies();
	
}
