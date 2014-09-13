package net.tridentsdk.api.entity;

import java.util.*;

import com.google.common.collect.*;

public enum VillagerCareer {
	/**
	 * Fletcher
	 */
	FLETCHER(VillagerProfession.FARMER, 0),
	
	/**
	 * Farmer
	 */
	FARMER(VillagerProfession.FARMER, 1),
	
	/**
	 * Fisherman
	 */
	FISHERMAN(VillagerProfession.FARMER, 2),
	
	/**
	 * Shepherd
	 */
	SHEPHERD(VillagerProfession.FARMER, 3),
	
	/**
	 * Librarian
	 */
	LIBRARIAN(VillagerProfession.LIBRARAIAN, 0),
	
	/**
	 * Cleric
	 */
	CLERIC(VillagerProfession.PRIEST, 0),
	
	/**
	 * Tool smith
	 */
	TOOL_SMITH(VillagerProfession.BLACKSMITH, 0),
	
	/**
	 * Armorer
	 */
	ARMORER(VillagerProfession.BLACKSMITH, 1),
	
	/**
	 * Weapon smith
	 */
	WEAPON_SMITH(VillagerProfession.BLACKSMITH, 2),
	
	/**
	 * Butcher
	 */
	BUTCHER(VillagerProfession.BUTCHER, 0),
	
	/**
	 * Leatherworker
	 */
	LEATHERWORKER(VillagerProfession.BUTCHER, 1)
	;
	private VillagerProfession parent;
	private int id;
	
	VillagerCareer(VillagerProfession parent, int id){
		this.parent = parent;
		this.id = id;
	}
	
	private static Map<VillagerProfession, Collection<VillagerCareer>> byProfession = Maps.newHashMap();
	
	static{
		Collection<VillagerCareer> parentColl = Lists.newArrayList();
		VillagerProfession current = null;
		for(VillagerCareer carrer : VillagerCareer.values()){
			if(current == null){
				current = carrer.parent;
				parentColl.add(carrer);
				continue;
			}
			if(current == carrer.parent){
				parentColl.add(carrer);
			} else {
				byProfession.put(current, parentColl);
				parentColl = Lists.newArrayList();
				current = carrer.parent;
			}
		}
	}

}
