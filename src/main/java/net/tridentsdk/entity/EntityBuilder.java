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