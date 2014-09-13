package net.tridentsdk.api.entity;

/**
 * Represents a Rabbit
 * 
 * @author TigerReborn
 */
public interface Rabbit extends Neutral{
    
    /**
     * Get the breed of Rabbit that this Rabbit is
     * 
     * @return the breed of Rabbit this rabbit is
     */
    RabbitType getBreed();

}
