package everyos.bot.botfruit.capabilities;

import java.util.List;

public interface CapabilitiesContainer {

	/**
	 * Creates a capability handle for the given capability class.
	 * The capability handle can be used to retrieve the capability
	 * and manage its lifecycle. Capabilities should be short-lived,
	 * so it is important to close the handle when the capability is
	 * no longer needed.
	 * @param <T> The capability type
	 * @param capabilityClass The capability class
	 * @return The capability handle
	 */
	<T> CapabilityHandle<T> createCapability(Class<T> capabilityClass);

	/**
	 * Get the capability provider intended to be used to create a
	 * given capability.
	 * @param <T> The capability type
	 * @param capabilityClass The capability class
	 * @return A list of possible capability providers. They are listed in
	 *  order of preference, with the first capability provider being the
	 *  most preferred. The other providers are "alternative" providers, and
	 *  can be used if prior capability providers have unmet dependencies.
	 */
	<T> List<CapabilityProvider<T>> getPossibleCapabilityProviders(Class<T> capabilityClass);

	/**
	 * Create a new builder, which can be used to configure and register
	 * capability providers for a to-be-constructed capabilities container.
	 * @return The capabilities container builder
	 */
	static CapabilitiesContainerBuilder builder() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Compose multiple capabilities containers into a single capabilities
	 * container. The resulting capabilities container will have all of the
	 * capabilities of the original capabilities containers. The process of
	 * resolving dependencies for a capability will have access to all of the
	 * capabilities in the composed capabilities container. 
	 * @param containers The capabilities containers to compose
	 * @return The composed capabilities containers
	 */
	static CapabilitiesContainer compose(CapabilitiesContainer... containers) {
		throw new UnsupportedOperationException("Not implemented");
	}

}
