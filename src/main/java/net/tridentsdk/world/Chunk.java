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
package net.tridentsdk.world;

import net.tridentsdk.base.Block;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.living.Player;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This class represents a Minecraft world chunk, a 16x16
 * section of the world which is individually loaded as
 * needed.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
public interface Chunk {
    /**
     * Obtains the x coordinate at which this chunk is
     * located.
     *
     * @return the x coordinate
     */
    int getX();

    /**
     * Obtains the z coordinate at which this chunk is
     * located.
     *
     * @return the z coordinate
     */
    int getZ();
    
    /**
     * Obtains the block at the given relative coordinates
     * inside of this chunk.
     *
     * @param x the relative x coordinate
     * @param y the relative y coordinate
     * @param z the relative z coordinate
     * @return the block at the given coordinates
     */
    @Nonnull
    Block getBlockAt(int x, int y, int z);

    /**
     * Obtains the world which contains this chunk.
     *
     * @return the container world
     */
    World getWorld();

    /**
     * Obtains a collection that contains the players that
     * currently occupy this chunk.
     *
     * <p>This collection may not be modified.</p>
     *
     * @return the players in this chunk
     */
    Set<? extends Player> getPlayers();

    /**
     * Obtains a stream of the entities, including players,
     * that currently occupy this chunk.
     *
     * @return the entities, including players in this chunk
     */
    Stream<? extends Entity> getEntities();
}