package everyos.bot.botfruit.capabilities;

import java.util.List;

import everyos.bot.botfruit.capabilities.imp.CapabilitiesContainerBuilderImp;
import everyos.bot.botfruit.capabilities.imp.ComposedCapabilitiesContainer;

/**
 * A capabilities container is used to register capability providers
 * used to create capabilities in the context for which the container
 * was created.
 */
public interface CapabilitiesContainer {

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
		return new CapabilitiesContainerBuilderImp();
	}

	/**
	 * Compose multiple capabilities containers into a single capabilities
	 * container. The resulting capabilities container will have all of the
	 * capabilities of the original capabilities containers. The process of
	 * resolving dependencies for a capability will have access to all of the
	 * capabilities in the composed capabilities container.
	 * The order of the capabilities containers provided will be the order
	 * of importance, such that capabilities will attempt to be resolved from
	 * the first capabilities container, then the second, and so on.
	 * @param containers The capabilities containers to compose
	 * @return The composed capabilities containers
	 */
	static CapabilitiesContainer compose(CapabilitiesContainer... containers) {
		return new ComposedCapabilitiesContainer(containers);
	}

}
