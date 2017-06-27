/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.plugin.channel;

import net.tridentsdk.Impl;
import net.tridentsdk.doc.Policy;
import net.tridentsdk.entity.living.Player;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;

/**
 * A listener which is registered to listen to plugin
 * channel events.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@NotThreadSafe
public abstract class SimpleChannelListener {
    /**
     * Registers the given channel listener to the listener
     * list to receive channel events.
     *
     * @param listener the listener to remove
     */
    public static void register(SimpleChannelListener listener) {
        Impl.get().register(listener);
    }

    /**
     * Removes the given channel listener from the listener
     * list.
     *
     * @param cls the class of the channel listener to
     * remove
     * @return {@code true} if a listener was removed,
     * {@code false} if there was no listener in the first
     * place
     */
    public static boolean unregister(Class<? extends SimpleChannelListener> cls) {
        return Impl.get().unregister(cls);
    }

    /**
     * A channel open event is dispatched when a plugin
     * channel is created by sending a {@code REGISTER}
     * packet.
     *
     * @param channel the channel that was created
     * @param dest the destination of the registry packet
     * (i.e. dest=CLIENT means the channel was created by
     * the server and then sent to the client)
     */
    @Policy("call on plugin thread")
    public void channelOpened(PluginChannel channel, Destination dest, Collection<? extends Player> players) {
    }

    /**
     * A channel open event is dispatched when a plugin
     * channel is closed by sending a {@code UNREGISTER}
     * packet.
     *
     * <p>It is recommended in this method to remove this
     * listener using {@link #unregister(Class)} if the
     * listener no longer has any channels to listen to, and
     * will not have any channels to listen to.</p>
     *
     * @param channel the channel that was closed
     * @param dest the destination of the unregistry packet
     * @param players the players that are to unregister
     * from this channel
     * (i.e. dest=CLIENT means the channel was created by
     * the server and then sent to the client)
     */
    @Policy("call on plugin thread")
    public void channelClosed(PluginChannel channel, Destination dest, Collection<? extends Player> players) {
    }

    /**
     * A message received event is dispatched when a channel
     * receives a message from a client.
     *
     * <p>Destination is implicitly
     * {@link Destination#SERVER}</p>
     *
     * <p>The channel may not always be present, hence it is
     * sent as a channel name rather than an object.</p>
     *
     * @param channel the channel receiving the message
     * @param sender the sender of the message
     * @param message the message received
     */
    @Policy("call on plugin thread")
    public void messageReceived(String channel, Player sender, byte[] message) {
    }

    /**
     * A message send event is dispatched when a channel
     * propagates a message to its recipients.
     *
     * <p>Destination is implicitly
     * {@link Destination#CLIENT}</p>
     *
     * @param channel the channel sending the message
     * @param message the message being sent
     */
    @Policy("call on plugin thread")
    public void messageSent(PluginChannel channel, byte[] message) {
    }

    /**
     * A filtering method used to determine whether the
     * event should continue to be passed on to its
     * respective listener.
     *
     * @param channel the channel dispatching the event
     * @param dest the destination of the event
     * @return {@code true} if the event passes the filter
     * and should continue to the event listener, or
     * {@code false} if it should be ignored by this
     * listener
     */
    @Policy("call on plugin thread")
    public abstract boolean listenFor(PluginChannel channel, Destination dest);
}