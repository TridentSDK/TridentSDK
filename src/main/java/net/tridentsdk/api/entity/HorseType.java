package net.tridentsdk.api.entity;

public enum HorseType {
	
	/**
	 * Generic Horse
	 */
	HORSE(0),
	
	/**
	 * Donkey
	 */
	DONKEY(1),
	
	/**
	 * Mule
	 */
	MULE(2),
	
	/**
	 * Zombie horse
	 */
	ZOMBIE(3),
	
	/**
	 * Skeleton horse
	 */
	SKELETON(4)
	;
	private int id;
	
	HorseType(int id){
		this.id = id;
	}
	
	private static HorseType[] byId = new HorseType[5];
	
	static{
		for(HorseType type : HorseType.values()){
			byId[type.id] = type;
		}
	}

}
