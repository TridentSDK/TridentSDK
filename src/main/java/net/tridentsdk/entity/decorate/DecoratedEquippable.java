package net.tridentsdk.entity.decorate;

import net.tridentsdk.window.inventory.ItemStack;

public class DecoratedEquippable extends DecorationAdapter<LivingEntity> implements Equippable {
    private ItemStack[] equipment = new ItemStack[4];

    protected DecoratedEquippable(LivingEntity entity) {
        super(entity);
    }

    @Override
    public ItemStack[] getEquipment() {
        return equipment;
    }

    @Override
    public void setEquipment(ItemStack[] stack) {
        this.equipment = stack;
        // TODO apply
    }
}
