package net.tridentsdk.api.util;

/**
 * Represents the rotation of an Armor Stand part
 * 
 * @author TigerReborn
 */
public class PartRotation {
    
    private int rotX, rotY, rotZ;
    
    public PartRotation(int rotX, int rotY, int rotZ){
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }
   
    public int getRotX() {
        return rotX;
    }

    public void setRotX(int rotX) {
        this.rotX = rotX;
    }

    public int getRotY() {
        return rotY;
    }

    public void setRotY(int rotY) {
        this.rotY = rotY;
    }

    public int getRotZ() {
        return rotZ;
    }

    public void setRotZ(int rotZ) {
        this.rotZ = rotZ;
    }
}
