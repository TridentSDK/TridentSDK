package net.tridentsdk.entity.living.ai;

import net.tridentsdk.entity.EntityType;

/**
 * Handles the assigning of AIs to Entities on the server.
 */
public interface AiHandler {

    /**
     * Returns the AI Module that is the default for a certain type of entity
     * @param type
     * @return
     */
    AiModule defaultAIFor(EntityType type);

    /**
     * Sets the AI for this entity type, used to provide a separate AiModule for Entities
     *
     * <p>Entities of this type in the world will immediately start using this
     * {@link net.tridentsdk.entity.living.ai.AiModule}, even if they were created before this was set.</p>
     * @param type the type of entity to replace the AI for
     * @param module the new AiModule to use
     */
    void setDefaultAiFor(EntityType type, AiModule module);


    /**
     * Returns the server's implementation of the AI for a given entity type
     * @param type
     */
    AiModule nativeAIFor(EntityType type);
}