package net.tridentsdk.api.entity;

/**
 * Represents the type of an Ocelot
 * 
 * @author TigerReborn
 */
public enum OcelotType {
	
	/**
	 * Wild
	 */
	WILD(0),
	
	/**
	 * Tuxedo
	 */
	TUXEDO(1),
	
	/**
	 * Tabby
	 */
	TABBY(2),
	
	/**
	 * Siamese
	 */
	SIAMESE(3)
	;
	
	private int id;
	
	OcelotType(int id){
		this.id = id;
	}
	
	private static OcelotType[] byId = new OcelotType[4];
	
	static{
		for(OcelotType type : OcelotType.values()){
			byId[type.id] = type;
		}
	}

}
