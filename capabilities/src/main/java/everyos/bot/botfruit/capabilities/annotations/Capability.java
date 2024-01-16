package everyos.bot.botfruit.capabilities.annotations;

/**
 * The capability annotation is used to mark a class as a capability.
 * A capability is an interface that provides a fine-grained piece of
 * functionality, and is designed to have enhanced security and
 * automatic degradation when dependencies are not met.
 * 
 * The capability annotation can also influence the security policies
 * of the capability. Additional security policies may be located elsewhere
 * in the codebase, and some settings given by this annotation may be
 * ignored depending on the security model.
 */
public @interface Capability {
	
}
