package net.tridentsdk.entity.decorate;

public final class Decorator {
    private Decorator() {
    }

    public static DecoratedAgeable asAgeable(LivingEntity entity, final boolean canBreed, final boolean inLove) {
        return new DecoratedAgeable(entity) {
            @Override
            public boolean canBreed() {
                return canBreed;
            }

            @Override
            public boolean isInLove() {
                return inLove;
            }
        };
    }

    public static DecoratedEquippable asEquippable(LivingEntity entity) {
        return new DecoratedEquippable(entity);
    }
}
