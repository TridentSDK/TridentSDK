package net.tridentsdk.bar;

/**
 * A player entity or something that can recieve bars.
 *
 * @author The TridentSDK Team
 */
public interface BarReceiver {
    /**
     * Sends a bar accordingly to the type and the message.
     *
     * @param barType the type of bar
     * @param message the message on the bar
     */
    void sendBar(BarType barType, String message);

    /**
     * Sends a bar accordingly to the type id and the message.
     *
     * @param typeId the type id of the bar
     * @param message the message on the bar
     */
    void sendBar(int typeId, String message);
}
