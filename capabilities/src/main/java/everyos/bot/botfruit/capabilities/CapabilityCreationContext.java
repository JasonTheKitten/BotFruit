package everyos.bot.botfruit.capabilities;

import everyos.bot.botfruit.capabilities.exceptions.UnmetDependencyException;

/**
 * A capability creation context is used to provide dependencies to
 * a capability provider when creating a capability.
 */
public interface CapabilityCreationContext {
	
	/**
	 * Get a dependency of the capability being created.
	 * @param <T> The dependency type
	 * @param capabilityClass The dependency class
	 * @throws UnmetDependencyException If the dependency is not met or no declared
	 * @return The dependency
	 */
	<T> T getDependency(Class<T> capabilityClass) throws UnmetDependencyException;

}
