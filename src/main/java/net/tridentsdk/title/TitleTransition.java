/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
     *
     * @param fadeIn the fade in time
     *
     * @param time the time of the title
     *
     * @param fadeOut the fade out time
     */
    public TitleTransition(int fadeIn, int time, int fadeOut){
        this.fadeIn = fadeIn;
        this.time = time;
        this.fadeOut = fadeOut;
    }

    /**
     * Gets the fade in time of the transition
     *
     * @return the fade in time
     */
    public int getFadeInTime(){
        return this.fadeIn;
    }

    /**
     * Gets the title time of the transition
     *
     * @return the title time
     */
    public int getTitleTime(){
        return this.time;
    }

    /**
     * Gets the fade out time of the transition
     *
     * @return the fade out time
     */
    public int getFadeOutTime(){
        return this.fadeOut;
    }
}
