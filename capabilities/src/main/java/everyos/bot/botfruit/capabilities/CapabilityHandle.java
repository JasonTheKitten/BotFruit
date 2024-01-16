package everyos.bot.botfruit.capabilities;

import java.io.Closeable;

/**
 * A capability handle is a handle holding a capability, as well
 * as methods for managing that capability's lifecycle.
 */
public interface CapabilityHandle<T> extends Closeable {
	
	/**
	 * Get the capability held by this handle. Capabilities should
	 * be short-lived, so it is important to close the handle when
	 * the capability is no longer needed.
	 * @return The capability
	 */
	T getCapability();

	/**
	 * Close the capability handle, invalidating the capability and
	 * preventing it from being further used.
	 */
	@Override
	void close();

}
