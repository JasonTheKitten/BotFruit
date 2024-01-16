package everyos.bot.botfruit.capabilities.exceptions;

/**
 * An unmet dependency exception is thrown when a capability provider
 * attempts to access a dependency that either has not been declared
 * or has not been met.
 */
public class UnmetDependencyException extends RuntimeException {
	
	private static final long serialVersionUID = -1L;
	
	/**
	 * Exception with default message.
	 */
	public UnmetDependencyException() {
		super("Attempt to access unmet or undeclared dependency");
	}
	
	/**
	 * Exception with custom message.
	 * @param message The message
	 */
	public UnmetDependencyException(String message) {
		super(message);
	}
	
	/**
	 * Exception with custom message and cause.
	 * @param message The message
	 * @param cause The cause
	 */
	public UnmetDependencyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Exception with custom cause.
	 * @param cause The cause
	 */
	public UnmetDependencyException(Throwable cause) {
		super(cause);
	}

}
