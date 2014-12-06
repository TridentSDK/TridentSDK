package net.tridentsdk.entity.decorate;

/**
 * Represents a LivingEntity that has an age and has the ability to bread
 *
 * @author TridentSDK Team
 */
public abstract class DecoratedAgeable extends DecorationAdapter<LivingEntity> {
    private int age;

    protected DecoratedAgeable(LivingEntity entity) {
        super(entity);
    }

    /**
     * The current age of this entity, in ticks
     *
     * @return the age of this entity
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the current age of this entity, in ticks
     *
     * @param ticks the age to set
     */
    public void setAge(int ticks) {
        this.age = ticks;
    }

    /**
     * Whether or not this entity can breed or not, where the ability to breed represents whether or not this entity can
     * become "in love"
     *
     * @return whether or not this entity can be bred
     */
    public abstract boolean canBreed();

    /**
     * Whether or not this entity is "in love", such that it will actively display the particle effect for breeding
     * hearts and search for a mate
     *
     * @return whether or not this entity is in love
     */
    public abstract boolean isInLove();

    public void applyBreed() {

    }

    public void applyLove() {

    }
}
