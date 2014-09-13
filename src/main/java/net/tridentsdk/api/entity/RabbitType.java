package net.tridentsdk.api.entity;

public enum RabbitType {
    
    BROWN(0),
    
    WHITE(1),
    
    BLACK(2),
    
    WHITE_AND_BLACK(3),
    
    GOLD(4),
    
    SALT_AND_PEPPER(5),
    
    KILLER_RABBIT(99)
    ;
    
    private int id;
    
    RabbitType(int id){
        this.id = id;
    }
    
    private static RabbitType[] byId = new RabbitType[7];
    
    static{
        for(RabbitType type : RabbitType.values()){
            byId[type.id] = type;
        }
    }

}
