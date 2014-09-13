package net.tridentsdk.api.entity;

/**
 * Represents a Villager
 * 
 * @author TigerReborn
 */
public interface Villager extends Ageable, Tradeable, Peaceful {

    /**
     * The profession of this villager
     * 
     * @return the profession of this villager
     */
    VillagerProfession getProfession();

    /**
     * The career of this villager
     * 
     * @return the career of this villager
     */
    VillagerCareer getCareer();

    /**
     * The current level of this villager's career. Affects trades offered by
     * this villager
     * 
     * @return the current level of this villager's career
     */
    int getCareerLevel();

    /**
     * Sets the career of this villager. If the profession does not match the
     * specified career's parent profession, the profession will be set the
     * career's parent profession
     * 
     * @param career the career you want to set for this villager
     */
    void setCareer(VillagerCareer career);

    /**
     * Sets the profession of this villager. If the current career does not have
     * the profession as its parent, the current career will be to the first
     * available career
     * 
     * @param profession
     */
    void setProfession(VillagerProfession profession);
}
