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

package net.tridentsdk.entity;

import net.tridentsdk.Coordinates;
import net.tridentsdk.factory.ExecutorFactory;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.UUID;

/**
 * Builds an entity from initializer components and auto-spawns safely
 *
 * <p>This is not thread safe. Do not share across methods, and you should be good.</p>
 *
 * @author The TridentSDK Team
 */
@NotThreadSafe // Designed for use in a single method
public abstract class EntityBuilder {
    public abstract EntityBuilder uuid(UUID uuid);

    public abstract EntityBuilder spawnLocation(Coordinates spawn);

    public abstract EntityBuilder executor(ExecutorFactory<? extends Entity> executor);

    public abstract EntityBuilder god(boolean god);

    public abstract EntityBuilder passenger(Entity passenger);

    public abstract EntityBuilder displayName(String displayName);

    public abstract EntityBuilder silent(boolean silent);

    public abstract <T extends Entity> T build(Class<T> entityType);

    public abstract <T extends Entity> T build(Class<T> entityType, ParameterValue<?>... parameterValues);
}