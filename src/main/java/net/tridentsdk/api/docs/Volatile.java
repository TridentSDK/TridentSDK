package net.tridentsdk.api.docs;

/**
 * Represents a type or member that is not safe to use normally without sufficient understanding of how it works
 *
 * @author the TridentSDK Team
 */
public @interface Volatile {
    /**
     * What is being protected and what needs to be done
     */
    String policy();

    /**
     * Why it is protected and why it is necessary
     */
    String reason();

    /**
     * Summarize how to use correctly
     */
    String fix();
}
