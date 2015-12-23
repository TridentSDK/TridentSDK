package net.tridentsdk.title;

/**
 * A player entity or something that can receive titles.
 * @author PizzaCrust
 */
public interface TitleReciever {
    /**
     * Sends a regular title
     * @param title the title message
     */
    void sendTitle(String title);

    /**
     * Sends a title with a subtitle
     * @param title the title message
     * @param subtitle the subtitle message
     */
    void sendTitle(String title, String subtitle);

    /**
     * Sends a regular time with a transition
     * @param title the title message
     * @param transition the transition object for the title
     */
    void sendTitle(String title, TitleTransition transition);

    /**
     * Sends a title with a subtitle and a transition
     * @param title the title message
     * @param subtitle the subtitle message
     * @param transition the transition object for the title
     */
    void sendTitle(String title, String subtitle, TitleTransition transition);
}
