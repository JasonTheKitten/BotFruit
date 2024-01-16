package everyos.bot.botfruit.capabilities;

/**
 * A capabilities container builder is used to configure and register
 * capability providers for a capabilities container that will be
 * created from it.
 */
public interface CapabilitiesContainerBuilder {

	/**
	 * Register a capability provider for a given capability class.
	 * If the capability provider is already registered, an "alternative"
	 * capability provider will be registered, which can take the place
	 * of the original capability provider if the original capability
	 * provider has unmet dependencies.
	 * @param <T> The capability type
	 * @param capabilityClass The capability class
	 * @param capabilityProvider The capability provider
	 */
	<T> CapabilitiesContainerBuilder registerCapabilityProvider(Class<T> capabilityClass, CapabilityProvider<T> capabilityProvider);

	/**
	 * Prefer a capability provider for a given capability class. This
	 * capability provider will have top priority over alternative
	 * capability providers registered prior to the capability provider
	 * being registered.
	 * @param <T> The capability type
	 * @param capabilityClass The capability class
	 * @param capabilityProvider The capability provider
	 */
	<T> CapabilitiesContainerBuilder preferCapabilityProvider(Class<T> capabilityClass, CapabilityProvider<T> capabilityProvider);

	/**
	 * Build a capabilities container using the configuration of this
	 * capabilities container builder.
	 * @return The capabilities container
	 */
	CapabilitiesContainer build();
	
}
