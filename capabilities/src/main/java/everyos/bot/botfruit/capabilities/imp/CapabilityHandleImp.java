package everyos.bot.botfruit.capabilities.imp;

import everyos.bot.botfruit.capabilities.CapabilityHandle;

public class CapabilityHandleImp<T> implements CapabilityHandle<T> {

	private final T capability;

	@Override
	public T getCapability() {
		return capability;
	}

	@Override
	public void close() {
		// TODO: Kill the capability
	}

	private CapabilityHandleImp(T capability) {
		this.capability = capability;
	}

	public static <T> CapabilityHandle<T> create(T capability) {
		return new CapabilityHandleImp<>(capability);
	}

}
