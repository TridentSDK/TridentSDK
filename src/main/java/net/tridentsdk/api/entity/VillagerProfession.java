package net.tridentsdk.api.entity;

public enum VillagerProfession {
	/**
	 * Farmer
	 */
	FARMER(0),
	
	/**
	 * Librarian
	 */
	LIBRARAIAN(1),
	
	/**
	 * Priest
	 */
	PRIEST(2),
	
	/**
	 * Blacksmith
	 */
	BLACKSMITH(3),
	
	/**
	 * Butcher
	 */
	BUTCHER(4)
	;
	private int id;
	
	VillagerProfession(int id){
		this.id = id;
	}
	
	private static VillagerProfession[] byId = new VillagerProfession[5];
	
	static{
		for(VillagerProfession profession : VillagerProfession.values()){
			byId[profession.id] = profession;
		}
	}

}
