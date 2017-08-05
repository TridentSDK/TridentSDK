package net.tridentsdk.meta.entity.living.monster;

public interface EvokerMeta extends MonsterMeta {

    EvokerSpellType getCurrentSpell();

    EvokerMeta setCurrentSpell(EvokerSpellType type);

}
