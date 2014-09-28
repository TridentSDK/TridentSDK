/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.plugin;

import net.tridentsdk.api.event.*;
import net.tridentsdk.api.util.TridentLogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PluginManager {

    /**
     * When there is an action, the event for that action is called through this method <p/> TODO: Exception handling in
     * a more robust way
     *
     * @param event the event that has been called
     */
    public static void passEvent(Event event)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ManagerList listenerlist = ManagerList.getManagers().get(event.getClass());
        if (listenerlist != null) listenerlist.execute(event);
    }

    /**
     * Register a listener from a plugin. Reflects any method with the @EventHandler annotation, creates a
     * RegisteredListener for each individual listener method, and registers the RegisteredListener to its appropriate
     * EventList (creates an event list if it is null). EventLists are sorted by event type.
     *
     * @param listener the listener class registered from a plugin
     */
    public void registerListener(Listener listener) {
        //Iterate through list of methods from listener
        Method[] methods = listener.getClass().getMethods();

        for (Method method : methods) {

            //Isolate methods using @EventHandler annotation
            if (method.isAnnotationPresent(EventHandler.class)) {

                //Get importance of method from handler
                EventHandler handler = method.getAnnotation(EventHandler.class);
                Importance importance = handler.importance();
                Class<?>[] params = method.getParameterTypes();

                if (params.length == 1) {

                    Class<?> clazz = params[0];

                    //Loop to get the superclass below Object of the parameter (should be Event)
                    Class<?> c = clazz;
                    boolean b = true;

                    while (b) {
                        if (!c.getSuperclass().equals(Object.class)) {
                            c = c.getSuperclass();
                        } else {
                            //End loop
                            b = false;
                        }
                    }

                    //Check to make sure class is an event
                    if (c.equals(Event.class)) {
                        Class<? extends Event> eventType = clazz.asSubclass(Event.class);
                        ManagerList listeners = ManagerList.getManagers().get(eventType);

                        if (listeners == null) {

                            listeners = new ManagerList();
                            ManagerList.getManagers().put(eventType, listeners);
                        }

                        //Create RegisteredListener and register it to the appropriate EventList
                        RegisteredListener rl = new RegisteredListener(listener, importance, method);
                        listeners.register(rl);
                    }
                } else {
                    TridentLogger.getGlobal().warning("Listener " + method.getName() + " has " + (params.length == 0 ? "no parameters!" : "more than one parameter") + "!");
                }
            }
        }
    }
}
