package net.tridentsdk.meta.entity.living.monster;

import net.tridentsdk.meta.entity.living.VillagerProfession;

public interface ZombieVillagerMeta extends ZombieMeta {

    boolean isConverting();

    ZombieMeta setConverting(boolean converting);

    VillagerProfession getProfession();

    ZombieMeta setProfession(VillagerProfession profession);

}
