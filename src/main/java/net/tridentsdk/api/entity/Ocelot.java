package net.tridentsdk.api.entity;

/**
 * Represents an Ocelot
 * 
 * @author TigerReborn
 */
public interface Ocelot extends Tameable, Peaceful {
	
	/**
	 * The breed of this ocelot, represented as a {@link net.tridentsdk.api.entity.OcelotType}OcelotType
	 * 
	 * @return the breed of this ocelot
	 */
	OcelotType getBreed();

}
