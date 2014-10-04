/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api;

public enum Material {

    /**
     * Blocks
     */
    AIR("0"),
    STONE("1"),
    GRASS("2"),
    DIRT("3"),
    COBBLESTONE("4"),
    WOOD("5"),
    SAPLING("6"),
    BEDROCK("7"),
    WATER("8"),
    STATIONARY_WATER("9"),
    LAVA("10"),
    STATIONARY_LAVA("11"),
    SAND("12"),
    GRAVEL("13"),
    GOLD_ORE("14"),
    IRON_ORE("15"),
    COAL_ORE("16"),
    LOG("17"),
    LEAVES("18"),
    SPONGE("19"),
    GLASS("20"),
    LAPIS_ORE("21"),
    LAPIS_BLOCK("22"),
    DISPENSER("23"),
    SANDSTONE("24"),
    NOTE_BLOCK("25"),
    BED_BLOCK("26"),
    POWERED_RAIL("27"),
    DETECTOR_RAIL("28"),
    PISTON_STICKY_BASE("29"),
    WEB("30"),
    LONG_GRASS("31"),
    DEAD_BUSH("32"),
    PISTON_BASE("33"),
    PISTON_EXTENSION("34"),
    WOOL("35"),
    PISTON_MOVING_PIECE("36"),
    YELLOW_FLOWER("37"),
    RED_ROSE("38"),
    BROWN_MUSHROOM("39"),
    RED_MUSHROOM("40"),
    GOLD_BLOCK("41"),
    IRON_BLOCK("42"),
    DOUBLE_STEP("43"),
    STEP("44"),
    BRICK("45"),
    TNT("46"),
    BOOKSHELF("47"),
    MOSSY_COBBLESTONE("48"),
    OBSIDIAN("49"),
    TORCH("50"),
    FIRE("51"),
    MOB_SPAWNER("52"),
    WOOD_STAIRS("53"),
    CHEST("54"),
    REDSTONE_WIRE("55"),
    DIAMOND_ORE("56"),
    DIAMOND_BLOCK("57"),
    WORKBENCH("58"),
    CROPS("59"),
    SOIL("60"),
    FURNACE("61"),
    BURNING_FURNACE("62"),
    SIGN_POST("63"),
    WOODEN_DOOR("64"),
    LADDER("65"),
    RAILS("66"),
    COBBLESTONE_STAIRS("67"),
    WALL_SIGN("68"),
    LEVER("69"),
    STONE_PLATE("70"),
    IRON_DOOR_BLOCK("71"),
    WOOD_PLATE("72"),
    REDSTONE_ORE("73"),
    GLOWING_REDSTONE_ORE("74"),
    REDSTONE_TORCH_OFF("75"),
    REDSTONE_TORCH_ON("76"),
    STONE_BUTTON("77"),
    SNOW("78"),
    ICE("79"),
    SNOW_BLOCK("80"),
    CACTUS("81"),
    CLAY("82"),
    SUGAR_CANE_BLOCK("83"),
    JUKEBOX("84"),
    FENCE("85"),
    PUMPKIN("86"),
    NETHERRACK("87"),
    SOUL_SAND("88"),
    GLOWSTONE("89"),
    PORTAL("90"),
    JACK_O_LANTERN("91"),
    CAKE_BLOCK("92"),
    DIODE_BLOCK_OFF("93"),
    DIODE_BLOCK_ON("94"),
    STAINED_GLASS("95"),
    TRAP_DOOR("96"),
    MONSTER_EGGS("97"),
    SMOOTH_BRICK("98"),
    HUGE_MUSHROOM_1("99"),
    HUGE_MUSHROOM_2("100"),
    IRON_FENCE("101"),
    THIN_GLASS("102"),
    MELON_BLOCK("103"),
    PUMPKIN_STEM("104"),
    MELON_STEM("105"),
    VINE("106"),
    FENCE_GATE("107"),
    BRICK_STAIRS("108"),
    SMOOTH_STAIRS("109"),
    MYCEL("110"),
    WATER_LILY("111"),
    NETHER_BRICK("112"),
    NETHER_FENCE("113"),
    NETHER_BRICK_STAIRS("114"),
    NETHER_WARTS("115"),
    ENCHANTMENT_TABLE("116"),
    BREWING_STAND("117"),
    CAULDRON("118"),
    ENDER_PORTAL("119"),
    ENDER_PORTAL_FRAME("120"),
    ENDER_STONE("121"),
    DRAGON_EGG("122"),
    REDSTONE_LAMP_OFF("123"),
    REDSTONE_LAMP_ON("124"),
    WOOD_DOUBLE_STEP("125"),
    WOOD_STEP("126"),
    COCOA("127"),
    SANDSTONE_STAIRS("128"),
    EMERALD_ORE("129"),
    ENDER_CHEST("130"),
    TRIPWIRE_HOOK("131"),
    TRIPWIRE("132"),
    EMERALD_BLOCK("133"),
    SPRUCE_WOOD_STAIRS("134"),
    BIRCH_WOOD_STAIRS("135"),
    JUNGLE_WOOD_STAIRS("136"),
    COMMAND("137"),
    BEACON("138"),
    COBBLE_WALL("139"),
    FLOWER_POT("140"),
    CARROT("141"),
    POTATO("142"),
    WOOD_BUTTON("143"),
    SKULL("144"),
    ANVIL("145"),
    TRAPPED_CHEST("146"),
    GOLD_PLATE("147"),
    IRON_PLATE("148"),
    REDSTONE_COMPARATOR_OFF("149"),
    REDSTONE_COMPARATOR_ON("150"),
    DAYLIGHT_DETECTOR("151"),
    REDSTONE_BLOCK("152"),
    QUARTZ_ORE("153"),
    HOPPER("154"),
    QUARTZ_BLOCK("155"),
    QUARTZ_STAIRS("156"),
    ACTIVATOR_RAIL("157"),
    DROPPER("158"),
    STAINED_CLAY("159"),
    STAINED_GLASS_PANE("160"),
    LEAVES_2("161"),
    LOG_2("162"),
    ACACIA_STAIRS("163"),
    DARK_OAK_STAIRS("164"),
    SLIME_BLOCK("165"),
    BARRIER("166"),
    IRON_TRAP_DOOR("167"),
    PRISMARINE("168"),
    SEA_LANTERN("169"),
    HAY_BLOCK("170"),
    CARPET("171"),
    HARD_CLAY("172"),
    COAL_BLOCK("173"),
    PACKED_ICE("174"),
    DOUBLE_PLANT("175"),
    STANDING_BANNER("176"),
    WALL_BANNER("177"),
    DAYLIGHT_DETECTOR_INVERTED("178"),
    RED_SANDSTONE("179"),
    RED_SANDSTONE_STAIRS("180"),
    DOUBLE_STEP_2("181"),
    STEP_2("182"),
    SPRUCE_FENCE_GATE("183"),
    BIRCH_FENCE_GATE("184"),
    JUNGLE_FENCE_GATE("185"),
    DARK_OAK_FENCE_GATE("186"),
    ACACIA_FENCE_GATE("187"),
    SPRUCE_FENCE("188"),
    BIRCH_FENCE("189"),
    JUNGLE_FENCE("190"),
    DARK_OAK_FENCE("191"),
    ACACIA_FENCE("192"),
    /**
     * Items
     */
    IRON_SPADE("256", 1),
    IRON_PICKAXE("257", 1),
    IRON_AXE("258", 1),
    FLINT_AND_STEEL("259", 1),
    APPLE("260"),
    BOW("261", 1),
    ARROW("262"),
    COAL("263"),
    DIAMOND("264"),
    IRON_INGOT("265"),
    GOLD_INGOT("266"),
    IRON_SWORD("267", 1),
    WOOD_SWORD("268", 1),
    WOOD_SPADE("269", 1),
    WOOD_PICKAXE("270", 1),
    WOOD_AXE("271", 1),
    STONE_SWORD("272", 1),
    STONE_SPADE("273", 1),
    STONE_PICKAXE("274", 1),
    STONE_AXE("275", 1),
    DIAMOND_SWORD("276", 1),
    DIAMOND_SPADE("277", 1),
    DIAMOND_PICKAXE("278", 1),
    DIAMOND_AXE("279", 1),
    STICK("280"),
    BOWL("281"),
    MUSHROOM_SOUP("282"),
    GOLD_SWORD("283", 1),
    GOLD_SPADE("284", 1),
    GOLD_PICKAXE("285", 1),
    GOLD_AXE("286", 1),
    STRING("287"),
    FEATHER("288"),
    SULPHUR("289"),
    WOOD_HOE("290", 1),
    STONE_HOE("291", 1),
    IRON_HOE("292", 1),
    DIAMOND_HOE("293", 1),
    GOLD_HOE("294", 1),
    SEEDS("295"),
    WHEAT("296"),
    BREAD("297"),
    LEATHER_HELMET("298", 1),
    LEATHER_CHESTPLATE("299", 1),
    LEATHER_LEGGINGS("300", 1),
    LEATHER_BOOTS("301", 1),
    CHAINMAIL_HELMET("302", 1),
    CHAINMAIL_CHESTPLATE("303", 1),
    CHAINMAIL_LEGGINGS("304", 1),
    CHAINMAIL_BOOTS("305", 1),
    IRON_HELMET("306", 1),
    IRON_CHESTPLATE("307", 1),
    IRON_LEGGINGS("308", 1),
    IRON_BOOTS("309", 1),
    DIAMOND_HELMET("310", 1),
    DIAMOND_CHESTPLATE("311", 1),
    DIAMOND_LEGGINGS("312", 1),
    DIAMOND_BOOTS("313", 1),
    GOLD_HELMET("314", 1),
    GOLD_CHESTPLATE("315", 1),
    GOLD_LEGGINGS("316", 1),
    GOLD_BOOTS("317", 1),
    FLINT("318"),
    PORK("319"),
    GRILLED_PORK("320"),
    PAINTING("321"),
    GOLDEN_APPLE("322"),
    SIGN("323", 16),
    WOOD_DOOR("324"),
    BUCKET("325", 16),
    WATER_BUCKET("326", 1),
    LAVA_BUCKET("327", 1),
    MINECART("328", 1),
    SADDLE("329", 1),
    IRON_DOOR("330", 1),
    REDSTONE("331"),
    SNOW_BALL("332", 16),
    BOAT("333", 1),
    LEATHER("334"),
    MILK_BUCKET("335", 1),
    CLAY_BRICK("336"),
    CLAY_BALL("337"),
    SUGAR_CANE("338"),
    PAPER("339"),
    BOOK("340"),
    SLIME_BALL("341"),
    STORAGE_MINECART("342", 1),
    POWERED_MINECART("343", 1),
    EGG("344", 16),
    COMPASS("345"),
    FISHING_ROD("346", 1),
    WATCH("347"),
    GLOWSTONE_DUST("348"),
    RAW_FISH("349"),
    COOKED_FISH("350"),
    INK_SACK("351"),
    BONE("352"),
    SUGAR("353"),
    CAKE("354, 1"),
    BED("355, 1"),
    DIODE("356"),
    COOKIE("357"),
    MAP("358"),
    SHEARS("359", 1),
    MELON("360"),
    PUMPKIN_SEEDS("361"),
    MELON_SEEDS("362"),
    RAW_BEEF("363"),
    COOKED_BEEF("364"),
    RAW_CHICKEN("365"),
    COOKED_CHICKEN("366"),
    ROTTEN_FLESH("367"),
    ENDER_PEARL("368", 16),
    BLAZE_ROD("369"),
    GHAST_TEAR("370"),
    GOLD_NUGGET("371"),
    POTION("373", 1),
    GLASS_BOTTLE("374"),
    SPIDER_EYE("375"),
    FERMENTED_SPIDER_EYE("376"),
    BLAZE_POWDER("377"),
    MAGMA_CREAM("378"),
    BREWING_STAND_ITEM("379"),
    CAULDRON_ITEM("380"),
    EYE_OF_ENDER("381"),
    SPECKLED_MELON("382"),
    MONSTER_EGG("383", 64),
    EXP_BOTTLE("384", 64),
    FIREBALL("385", 64),
    BOOK_AND_QUILL("386", 1),
    WRITTEN_BOOK("387", 16),
    EMERALD("388", 64),
    ITEM_FRAME("389"),
    FLOWER_POT_ITEM("390"),
    CARROT_ITEM("391"),
    POTATO_ITEM("392"),
    BAKED_POTATO("393"),
    POISONOUS_POTATO("394"),
    EMPTY_MAP("395"),
    GOLDEN_CARROT("396"),
    SKULL_ITEM("397"),
    CARROT_STICK("398", 1),
    NETHER_STAR("399"),
    PUMPKIN_PIE("400"),
    FIREWORK("401"),
    FIREWORK_CHARGE("402"),
    ENCHANTED_BOOK("403", 1),
    REDSTONE_COMPARATOR("404"),
    NETHER_BRICK_ITEM("405"),
    QUARTZ("406"),
    EXPLOSIVE_MINECART("407", 1),
    HOPPER_MINECART("408", 1),
    PRISMARINE_SHARD("409"),
    PRISMARINE_CRYSTALS("410"),
    RABBIT("411"),
    COOKED_RABBIT("412"),
    RABBIT_STEW("413", 1),
    RABBIT_FOOT("414"),
    RABBIT_HIDE("415"),
    ARMOR_STAND("416"),
    IRON_BARDING("417", 1),
    GOLD_BARDING("418", 1),
    DIAMOND_BARDING("419", 1),
    LEASH("420"),
    NAME_TAG("421"),
    COMMAND_MINECART("422", 1),
    MUTTON("423"),
    COOKED_MUTTON("424"),
    BANNER("425"),
    SPRUCE_DOOR("427"),
    BIRCH_DOOR("428"),
    JUNGLE_DOOR("429"),
    ACACIA_DOOR("430"),
    DARK_OAK_DOOR("431"),
    GOLD_RECORD("2256", 1),
    GREEN_RECORD("2257", 1),
    RECORD_3("2258", 1),
    RECORD_4("2259", 1),
    RECORD_5("2260", 1),
    RECORD_6("2261", 1),
    RECORD_7("2262", 1),
    RECORD_8("2263", 1),
    RECORD_9("2264", 1),
    RECORD_10("2265", 1),
    RECORD_11("2266", 1),
    RECORD_12("2267", 1);

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
