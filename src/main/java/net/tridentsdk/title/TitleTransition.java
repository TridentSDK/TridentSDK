package net.tridentsdk.title;

/**
 * Represents a title's transition.
 * @author PizzaCrust
 */
public class TitleTransition {
    /**
     * Time of fade in
     */
    private int fadeIn;
    /**
     * Time of the title showing
     */
    private int time;
    /**
     * Time of fade out
     */
    private int fadeOut;

    /**
     * Creates a title transition object
     * @param fadeIn the fade in time
     * @param time the time of the title
     * @param fadeOut the fade out time
     */
    public TitleTransition(int fadeIn, int time, int fadeOut){
        this.fadeIn = fadeIn;
        this.time = time;
        this.fadeOut = fadeOut;
    }

    /**
     * Gets the fade in time of the transition
     * @return the fade in time
     */
    public int getFadeInTime(){
        return this.fadeIn;
    }

    /**
     * Gets the title time of the transition
     * @return the title time
     */
    public int getTitleTime(){
        return this.time;
    }

    /**
     * Gets the fade out time of the transition
     * @return the fade out time
     */
    public int getFadeOutTime(){
        return this.fadeOut;
    }
}
