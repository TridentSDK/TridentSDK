package net.tridentsdk.entity.meta.living;

import net.tridentsdk.base.VillagerProfession;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface VillagerMeta extends AgeableMeta{

    VillagerProfession getProfession();

    void setProfession(VillagerProfession profession);

}
