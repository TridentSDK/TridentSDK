package net.tridentsdk.entity.meta.living;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface VillagerMeta extends AgeableMeta{

    VillagerProfession getProfession();

    void setProfession(VillagerProfession profession);

    enum VillagerProfession {

        FARMER(0),
        LIBRARIAN(1),
        PRIEST(2),
        BLACKSMITH(3),
        BUTCHER(4);

        @Getter
        private final int data;

        private VillagerProfession(int data) {
            this.data = data;
        }

        private static final Map<Integer, VillagerProfession> dataToType = new HashMap<>();

        public static VillagerProfession of(int data) {
            VillagerProfession profession = dataToType.get(data);
            if (profession == null) {
                throw new IllegalArgumentException("no villager profession with id = " + data);
            }
            return profession;
        }

        static {
            for (VillagerProfession type : values()) {
                dataToType.put(type.data, type);
            }
        }

    }

}
