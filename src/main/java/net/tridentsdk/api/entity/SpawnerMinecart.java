package net.tridentsdk.api.entity;

/**
 * Represents a Spawner Minecart
 * 
 * @author TigerReborn
 */
public interface SpawnerMinecart extends MinecartBase {
    
    /**
     * The spawn type of entities spawned by this Spawner Minecart
     * 
     * @return the type of entity
     */
    EntityType getSpawnType();
    
    /**
     * The properties that will be applied when an Entity is spawned by this
     * 
     * @return the properties applied
     */
    EntityProperties getAppliedProperties();

}
