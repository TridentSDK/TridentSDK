package net.tridentsdk.api;

public interface Messagable {

    /**
     * Send an array of messages to this recipient
     *
     * @param messages String[] messages to be sent
     */
    void sendMessage (String... messages);

    /**
     * Gets the last message sent to this Messagable
     * @return the last method sent to this messagable
     */
    String getLastMessage();

}
