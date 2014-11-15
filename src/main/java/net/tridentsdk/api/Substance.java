/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api;

/**
 * Enumeration of possible materials in minecraft
 *
 * @author The TridentSDK Team
 */
public enum Substance {
    // Recreate because it's clearly copy-pasted

    private final String id;
    private final String data;
    private final int maxStack;
    private final int idInt;

    Material(String id, int stack, String data) {
        this.id = id;
        this.maxStack = stack;
        this.data = data;

        this.idInt = Integer.parseInt(id);
    }

    Material(String id, int stack) {
        this(id, stack, "");
    }

    Material(String id) {
        this(id, 0, "");
    }

    /**
     * Matches a Material from a String
     *
     * @param id String to be matched
     * @return Material which was matched using id
     */
    public static Material fromString(String id) {
        for (Material mat : Material.values()) {
            if (mat.getId().replaceAll("_", " ").equalsIgnoreCase(id))
                return mat;
        }

        return null;
    }

    /**
     * Gets the block ID of the Material
     *
     * @return ID of the Material
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets data of this Material
     *
     * @return Data of this Material
     */
    public String getData() {
        return this.data;
    }

    /**
     * Checks if the material is a block
     *
     * @return True if the material is a block
     */
    public boolean isBlock() {
        return Integer.parseInt(this.id) < 255;
    }

    /**
     * Check the maximum size of a stack for this Material
     *
     * @return The maximum size of a stack for this Material
     */
    public int getMaxStackSize() {
        return this.maxStack;
    }

    /**
     * Checks if the material is edible or not
     *
     * @return True if the material is edible
     */
    public boolean isEdible() {
        switch (this) {
            case BREAD:
            case CARROT_ITEM:
            case BAKED_POTATO:
            case POTATO_ITEM:
            case POISONOUS_POTATO:
            case GOLDEN_CARROT:
            case PUMPKIN_PIE:
            case COOKIE:
            case MELON:
            case MUSHROOM_SOUP:
            case RAW_CHICKEN:
            case COOKED_CHICKEN:
            case RAW_BEEF:
            case COOKED_BEEF:
            case RAW_FISH:
            case COOKED_FISH:
            case PORK:
            case GRILLED_PORK:
            case APPLE:
            case GOLDEN_APPLE:
            case ROTTEN_FLESH:
            case SPIDER_EYE:
            case RABBIT:
            case COOKED_RABBIT:
            case RABBIT_STEW:
            case MUTTON:
            case COOKED_MUTTON:
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if a item is a disc (record)
     *
     * @return True if the item is a disc (record)
     */
    public boolean isDisc() {
        return !this.isBlock() && this.idInt >= 2256;
    }

    /**
     * Check if a item is wearable (armor)
     *
     * @return True if the item is wearable (armor)
     */
    public boolean isWearable() {
        return !this.isBlock() && this.idInt >= 298 && this.idInt <= 317;
    }

    /**
     * Check if a block is solid.
     *
     * @return True if the block is solid
     */
    public boolean isSolid() {
        if (!this.isBlock()) {
            return false;
        }

        switch (this) {
            case WATER:
            case STATIONARY_WATER:
            case LAVA:
            case STATIONARY_LAVA:
                return false;
            default:
                return true;
        }
    }

    /**
     * Check if a block is flammable or not
     *
     * @return True if a block is flammable
     */
    public boolean isFlammable() {
        if (!this.isBlock()) {
            return false;
        }

        switch (this) {
            case WOOD:
            case LOG:
            case LEAVES:
            case NOTE_BLOCK:
            case BED_BLOCK:
            case LONG_GRASS:
            case DEAD_BUSH:
            case WOOL:
            case TNT:
            case BOOKSHELF:
            case WOOD_STAIRS:
            case CHEST:
            case WORKBENCH:
            case SIGN_POST:
            case WOODEN_DOOR:
            case WALL_SIGN:
            case WOOD_PLATE:
            case JUKEBOX:
            case FENCE:
            case TRAP_DOOR:
            case HUGE_MUSHROOM_1:
            case HUGE_MUSHROOM_2:
            case VINE:
            case FENCE_GATE:
            case WOOD_DOUBLE_STEP:
            case WOOD_STEP:
            case SPRUCE_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case TRAPPED_CHEST:
            case DAYLIGHT_DETECTOR:
            case CARPET:
            case LEAVES_2:
            case LOG_2:
            case ACACIA_STAIRS:
            case DARK_OAK_STAIRS:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case DARK_OAK_FENCE:
            case ACACIA_FENCE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if a block can burn away
     *
     * @return True if a block can burn away
     */
    public boolean isBurnable() {
        if (!this.isBlock()) {
            return false;
        }

        switch (this) {
            case WOOD:
            case LOG:
            case LEAVES:
            case LONG_GRASS:
            case WOOL:
            case YELLOW_FLOWER:
            case RED_ROSE:
            case TNT:
            case BOOKSHELF:
            case WOOD_STAIRS:
            case FENCE:
            case VINE:
            case WOOD_DOUBLE_STEP:
            case WOOD_STEP:
            case SPRUCE_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case HAY_BLOCK:
            case COAL_BLOCK:
            case LEAVES_2:
            case LOG_2:
            case CARPET:
            case DOUBLE_PLANT:
            case ACACIA_STAIRS:
            case DARK_OAK_STAIRS:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case DARK_OAK_FENCE:
            case ACACIA_FENCE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Gets if a block is affected by gravity, and falls when you place it.
     *
     * @return True if the block is affected by gravity, and falls when you place it.
     */
    public boolean hasGravity() {
        if (!this.isBlock()) {
            return false;
        }

        switch (this) {
            case SAND:
            case ANVIL:
            case GRAVEL:
                return true;
            default:
                return false;
        }
    }

}
