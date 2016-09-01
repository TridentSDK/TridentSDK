package net.tridentsdk.entity.meta.living;

import net.tridentsdk.base.VillagerProfession;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface VillagerMeta extends AgeableMeta{

    VillagerProfession getProfession();

    void setProfession(VillagerProfession profession);

}
