package everyos.bot.botfruit.capabilities;

import java.util.Optional;

import everyos.bot.botfruit.capabilities.exceptions.UnmetDependencyException;
import everyos.bot.botfruit.capabilities.imp.CapabilitiesResolverImp;

/**
 * A capabilities resolver is used to resolve the dependencies of
 * capabilities and generate handles to use the capabilities once
 * loaded.
 */
public interface CapabilitiesResolver extends AutoCloseable {
	
	/**
	 * Creates a capability handle for the given capability class.
	 * The capability handle can be used to retrieve the capability
	 * and manage its lifecycle. Capabilities should be short-lived,
	 * so it is important to close the handle when the capability is
	 * no longer needed.
	 * @param <T> The capability type
	 * @param capabilityClass The capability class
	 * @return An optional capability handle. If the capability could
	 *  not be created, the optional will be empty.
	 * @throws UnmetDependencyException If the capability has unmet
	 * dependencies
	 */
	<T> Optional<CapabilityHandle<T>> createCapability(Class<T> capabilityClass) throws UnmetDependencyException;

	/**
	 * Close the capabilities resolver. This will close all of the
	 * capabilities handles created by this capabilities resolver.
	 */
	@Override
	void close();

	/**
	 * Create a new capabilities resolver, which can be used to
	 * resolve the dependencies of capabilities and generate handles
	 * to use the capabilities once loaded.
	 * @param container The capabilities container to use to resolve
	 *  the dependencies of capabilities
	 * @return The capabilities resolver
	 */
	static CapabilitiesResolver create(CapabilitiesContainer container) {
		return new CapabilitiesResolverImp(container);
	}

}
