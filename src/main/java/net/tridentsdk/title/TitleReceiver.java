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
 * A player entity or something that can receive titles.
 *
 * @author The TridentSDK Team
 */
public interface TitleReceiver {
    /**
     * Sends a regular title
     *
     * @param title the title message
     */
    void sendTitle(String title);

    /**
     * Sends a title with a subtitle
     *
     * @param title the title message
     * @param subtitle the subtitle message
     */
    void sendTitle(String title, String subtitle);

    /**
     * Sends a regular time with a transition
     *
     * @param title the title message
     * @param transition the transition object for the title
     */
    void sendTitle(String title, TitleTransition transition);

    /**
     * Sends a title with a subtitle and a transition
     *
     * @param title the title message
     * @param subtitle the subtitle message
     * @param transition the transition object for the title
     */
    void sendTitle(String title, String subtitle, TitleTransition transition);
}
