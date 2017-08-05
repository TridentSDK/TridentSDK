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
import net.tridentsdk.entity.living.EntityPlayer;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;

/**
 * Represents a message channel which can be opened by the
 * server or a client. This allows for custom communication
 * between plugins and the client, such as to allow mods to
 * interface across the network.
 *
 * <p>A plugin channel can be created using on of the
 * static {@code open} methods, and can be closed by
 * obtaining the instance of the channel such as through
 * {@link #get(String)} and then closing the channel, if it
 * exists.</p>
 *
 * <p>A plugin channel is implicitly closed when a player
 * leaves the server. In that case, the player will be
 * automatically removed from the plugin channel list of
 * recipients, and must be added again manually UNLESS
 * {@link #openAll(String)} was used, in which case the
 * player will be automatically added again once they join
 * the server.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@ThreadSafe
public interface PluginChannel {
    /**
     * Opens a new plugin channel with the given channel
     * name and initial players to register with.
     *
     * <p>This method ignores any players that failed to
     * register the packet, instead leaving it to return
     * {@code false} in the {@link #send(byte[])} method.
     * </p>
     *
     * <p>If the channel already exists upon attempting to
     * open the channel, then the old channel will be
     * returned and the given targets are added to the
     * existing channel's recipient list.</p>
     *
     * @param name the name of the channel
     * @param targets the initial channel recipients
     * @return the new plugin channel, or an existing
     * channel with the players in the given method
     */
    static PluginChannel open(String name, EntityPlayer... targets) {
        return Impl.get().open(name, targets);
    }

    /**
     * Opens a new plugin channel with the given channel
     * name and initial players to register with.
     *
     * <p>This method ignores any players that failed to
     * register the packet, instead leaving it to return
     * {@code false} in the {@link #send(byte[])} method.
     * </p>
     *
     * <p>If the channel already exists upon attempting to
     * open the channel, then the old channel will be
     * returned and the given targets are added to the
     * existing channel's recipient list.</p>
     *
     * @param name the name of the channel
     * @param targets the initial channel recipients
     * @return the new plugin channel, or an existing
     * channel with the players in the given method
     */
    static PluginChannel open(String name, Collection<EntityPlayer> targets) {
        return Impl.get().open(name, targets);
    }

    /**
     * Opens a new plugin channel for all the players
     * present on the server, as well as all future
     * connecting players.
     *
     * <p>If the channel already exists upon attempting to
     * open the channel, then the old channel will be
     * returned.</p>
     *
     * @param name the name of the channel
     * @return the new plugin channel, or the old one if it
     * already exists
     */
    static PluginChannel openAll(String name) {
        return Impl.get().openAll(name);
    }

    /**
     * Attempts to open the plugin channel with the given
     * channel name with a random player on the server,
     * adding that random player to the recipients list.
     *
     * <p>If no player is on the server, no channel will be
     * created, in which case {@code null} will be returned.
     * </p>
     *
     * @param name the name of the channel
     * @return the new plugin channel, or {@code null} if
     * there are no players on the server
     */
    @Nullable
    static PluginChannel tryOpen(String name) {
        return Impl.get().tryOpen(name);
    }

    /**
     * Obtains the plugin channel for the given channel
     * name.
     *
     * @param name the name of the channel to obtain
     * @return the plugin channel for that given channel
     * name, or {@code null} if none exist
     */
    @Nullable
    static PluginChannel get(String name) {
        return Impl.get().get(name);
    }

    /**
     * Closes the channel, sending all recipients in the
     * channel an {@code UNREGISTER} packet.
     */
    void close();

    /**
     * Closes the channel for the players that pass the
     * filter function, sending them an {@code UNREGISTER}
     * packet and removing from the recipients list.
     *
     * @param function returs {@code true} for players that
     * should be removed, {@code false} for those that stay
     */
    void closeFor(Function<EntityPlayer, Boolean> function);

    /**
     * Closes the channel for the players that are contained
     * in the given iterable, sending them an
     * {@code UNREGISTER} packet and removing from the
     * recipients list.
     *
     * @param players the players to remove
     * @return {@code true} if all players in the iterable
     * are removed
     */
    boolean closeFor(Collection<EntityPlayer> players);

    /**
     * Closes the channel for players that are contained in
     * the given varargs of player UUIDs, sending them an
     * {@code UNREGISTER} packet and removing from the
     * recipients list.
     *
     * @param uuids the UUIDs of the players to remove
     * @return {@code true} if all the player UUIDs were
     * removed, {@code false} otherwise
     */
    boolean closeFor(UUID... uuids);

    /**
     * Obtains the channel name for this plugin channel.
     *
     * @return the name of the channel
     */
    String getName();

    /**
     * Adds the given recipient(s) to the list of players
     * contained in this channel, sending them the
     * {@code REGISTER} channel packet.
     *
     * @param recipients the players to add
     */
    void addRecipient(EntityPlayer... recipients);

    /**
     * Adds the given recipient(s) to the list of players
     * contained in this channel, sending them the
     * {@code REGISTER} channel packet.
     *
     * @param recipients the players to add
     */
    void addRecipient(Collection<? extends EntityPlayer> recipients);

    /**
     * Obtains the recipients that have been registered in
     * this channel.
     *
     * @return a collection of players that are on the
     * recipients list
     */
    Collection<EntityPlayer> getRecipients();

    /**
     * Sends all the recipients of this plugin channel the
     * given message. This message must not exceed the
     * length of {@link Short#MAX_VALUE} in size.
     *
     * @param message the message to send to all recipients
     * @return {@code true} if any recipients received the
     * message, {@code false} if no packets were sent or if
     * the channel has been closed
     */
    boolean send(byte[] message);
}