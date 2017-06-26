/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.tridentsdk.effect.sound;

/**
 * Enum of all possible sound effects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public enum SoundEffectType {

    /**
     * Eerie noise
     */
    AMBIENT_CAVE("ambient.cave", 0, SoundCategory.AMBIENT),

    /**
     * Block broken
     */
    BLOCK_ANVIL_BREAK("block.anvil.break", 1, SoundCategory.BLOCK),

    /**
     * Anvil destroyed
     */
    BLOCK_ANVIL_DESTROY("block.anvil.destroy", 2, SoundCategory.BLOCK),

    BLOCK_ANVIL_FALL("block.anvil.fall", 3, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_ANVIL_HIT("block.anvil.hit", 4, SoundCategory.BLOCK),

    /**
     * Anvil landed
     */
    BLOCK_ANVIL_LAND("block.anvil.land", 5, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_ANVIL_PLACE("block.anvil.place", 6, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_ANVIL_STEP("block.anvil.step", 7, SoundCategory.BLOCK),

    /**
     * Anvil used
     */
    BLOCK_ANVIL_USE("block.anvil.use", 8, SoundCategory.BLOCK),

    /**
     * Brewing Stand bubbles
     */
    BLOCK_BREWING_STAND_BREW("block.brewing_stand.brew", 9, SoundCategory.BLOCK),

    /**
     * Chest closes
     */
    BLOCK_CHEST_CLOSE("block.chest.close", 10, SoundCategory.BLOCK),

    /**
     * Chest locked
     */
    BLOCK_CHEST_LOCKED("block.chest.locked", 11, SoundCategory.BLOCK),

    /**
     * Chest opens
     */
    BLOCK_CHEST_OPEN("block.chest.open", 12, SoundCategory.BLOCK),

    /**
     * Chorus Flower withers
     */
    BLOCK_CHORUS_FLOWER_DEATH("block.chorus_flower.death", 13, SoundCategory.BLOCK),

    /**
     * Chorus Flower grows
     */
    BLOCK_CHORUS_FLOWER_GROW("block.chorus_flower.grow", 14, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_CLOTH_BREAK("block.cloth.break", 15, SoundCategory.BLOCK),

    BLOCK_CLOTH_FALL("block.cloth.fall", 16, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_CLOTH_HIT("block.cloth.hit", 17, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_CLOTH_PLACE("block.cloth.place", 18, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_CLOTH_STEP("block.cloth.step", 19, SoundCategory.BLOCK),

    /**
     * Comparator clicks
     */
    BLOCK_COMPARATOR_CLICK("block.comparator.click", 20, SoundCategory.BLOCK),

    /**
     * Dispensed item
     */
    BLOCK_DISPENSER_DISPENSE("block.dispenser.dispense", 21, SoundCategory.BLOCK),

    /**
     * Dispenser failed
     */
    BLOCK_DISPENSER_FAIL("block.dispenser.fail", 22, SoundCategory.BLOCK),

    /**
     * Dispensed item
     */
    BLOCK_DISPENSER_LAUNCH("block.dispenser.launch", 23, SoundCategory.BLOCK),

    /**
     * Explosion
     */
    BLOCK_END_GATEWAY_SPAWN("block.end_gateway.spawn", 24, SoundCategory.BLOCK),

    /**
     * Chest closes
     */
    BLOCK_ENDERCHEST_CLOSE("block.enderchest.close", 25, SoundCategory.BLOCK),

    /**
     * Chest opens
     */
    BLOCK_ENDERCHEST_OPEN("block.enderchest.open", 26, SoundCategory.BLOCK),

    /**
     * Fence Gate creaks
     */
    BLOCK_FENCE_GATE_CLOSE("block.fence_gate.close", 27, SoundCategory.BLOCK),

    /**
     * Fence Gate creaks
     */
    BLOCK_FENCE_GATE_OPEN("block.fence_gate.open", 28, SoundCategory.BLOCK),

    /**
     * Fire crackles
     */
    BLOCK_FIRE_AMBIENT("block.fire.ambient", 29, SoundCategory.BLOCK),

    /**
     * Fire extinguished
     */
    BLOCK_FIRE_EXTINGUISH("block.fire.extinguish", 30, SoundCategory.BLOCK),

    /**
     * Furnace crackles
     */
    BLOCK_FURNACE_FIRE_CRACKLE("block.furnace.fire_crackle", 31, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_GLASS_BREAK("block.glass.break", 32, SoundCategory.BLOCK),

    BLOCK_GLASS_FALL("block.glass.fall", 33, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_GLASS_HIT("block.glass.hit", 34, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_GLASS_PLACE("block.glass.place", 35, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_GLASS_STEP("block.glass.step", 36, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_GRASS_BREAK("block.grass.break", 37, SoundCategory.BLOCK),

    BLOCK_GRASS_FALL("block.grass.fall", 38, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_GRASS_HIT("block.grass.hit", 39, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_GRASS_PLACE("block.grass.place", 40, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_GRASS_STEP("block.grass.step", 41, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_GRAVEL_BREAK("block.gravel.break", 42, SoundCategory.BLOCK),

    BLOCK_GRAVEL_FALL("block.gravel.fall", 43, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_GRAVEL_HIT("block.gravel.hit", 44, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_GRAVEL_PLACE("block.gravel.place", 45, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_GRAVEL_STEP("block.gravel.step", 46, SoundCategory.BLOCK),

    /**
     * Door creaks
     */
    BLOCK_IRON_DOOR_CLOSE("block.iron_door.close", 47, SoundCategory.BLOCK),

    /**
     * Door creaks
     */
    BLOCK_IRON_DOOR_OPEN("block.iron_door.open", 48, SoundCategory.BLOCK),

    /**
     * Trapdoor opens
     */
    BLOCK_IRON_TRAPDOOR_CLOSE("block.iron_trapdoor.close", 49, SoundCategory.BLOCK),

    /**
     * Trapdoor closes
     */
    BLOCK_IRON_TRAPDOOR_OPEN("block.iron_trapdoor.open", 50, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_LADDER_BREAK("block.ladder.break", 51, SoundCategory.BLOCK),

    BLOCK_LADDER_FALL("block.ladder.fall", 52, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_LADDER_HIT("block.ladder.hit", 53, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_LADDER_PLACE("block.ladder.place", 54, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_LADDER_STEP("block.ladder.step", 55, SoundCategory.BLOCK),

    /**
     * Lava pops
     */
    BLOCK_LAVA_AMBIENT("block.lava.ambient", 56, SoundCategory.BLOCK),

    /**
     * Lava hisses
     */
    BLOCK_LAVA_EXTINGUISH("block.lava.extinguish", 57, SoundCategory.BLOCK),

    /**
     * Lava pops
     */
    BLOCK_LAVA_POP("block.lava.pop", 58, SoundCategory.BLOCK),

    /**
     * Lever clicks
     */
    BLOCK_LEVER_CLICK("block.lever.click", 59, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_METAL_BREAK("block.metal.break", 60, SoundCategory.BLOCK),

    BLOCK_METAL_FALL("block.metal.fall", 61, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_METAL_HIT("block.metal.hit", 62, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_METAL_PLACE("block.metal.place", 63, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_METAL_STEP("block.metal.step", 64, SoundCategory.BLOCK),

    /**
     * Pressure Plate clicks
     */
    BLOCK_METAL_PRESSUREPLATE_CLICK_OFF("block.metal_pressureplate.click_off", 65, SoundCategory.BLOCK),

    /**
     * Pressure Plate clicks
     */
    BLOCK_METAL_PRESSUREPLATE_CLICK_ON("block.metal_pressureplate.click_on", 66, SoundCategory.BLOCK),

    /**
     * Noteblock plays
     */
    BLOCK_NOTE_BASEDRUM("block.note.basedrum", 67, SoundCategory.BLOCK),

    /**
     * Noteblock plays
     */
    BLOCK_NOTE_BASS("block.note.bass", 68, SoundCategory.BLOCK),

    /**
     * Noteblock plays
     */
    BLOCK_NOTE_HARP("block.note.harp", 69, SoundCategory.BLOCK),

    /**
     * Noteblock plays
     */
    BLOCK_NOTE_HAT("block.note.hat", 70, SoundCategory.BLOCK),

    BLOCK_NOTE_PLING("block.note.pling", 71, SoundCategory.BLOCK),

    /**
     * Noteblock plays
     */
    BLOCK_NOTE_SNARE("block.note.snare", 72, SoundCategory.BLOCK),

    /**
     * Piston moves
     */
    BLOCK_PISTON_CONTRACT("block.piston.contract", 73, SoundCategory.BLOCK),

    /**
     * Piston moves
     */
    BLOCK_PISTON_EXTEND("block.piston.extend", 74, SoundCategory.BLOCK),

    /**
     * Portal whooshes
     */
    BLOCK_PORTAL_AMBIENT("block.portal.ambient", 75, SoundCategory.BLOCK),

    BLOCK_PORTAL_TRAVEL("block.portal.travel", 76, SoundCategory.BLOCK),

    BLOCK_PORTAL_TRIGGER("block.portal.trigger", 77, SoundCategory.BLOCK),

    /**
     * Torch fizzes
     */
    BLOCK_REDSTONE_TORCH_BURNOUT("block.redstone_torch.burnout", 78, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_SAND_BREAK("block.sand.break", 79, SoundCategory.BLOCK),

    BLOCK_SAND_FALL("block.sand.fall", 80, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_SAND_HIT("block.sand.hit", 81, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_SAND_PLACE("block.sand.place", 82, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_SAND_STEP("block.sand.step", 83, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_SLIME_BREAK("block.slime.break", 84, SoundCategory.BLOCK),

    BLOCK_SLIME_FALL("block.slime.fall", 85, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_SLIME_HIT("block.slime.hit", 86, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_SLIME_PLACE("block.slime.place", 87, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_SLIME_STEP("block.slime.step", 88, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_SNOW_BREAK("block.snow.break", 89, SoundCategory.BLOCK),

    BLOCK_SNOW_FALL("block.snow.fall", 90, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_SNOW_HIT("block.snow.hit", 91, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_SNOW_PLACE("block.snow.place", 92, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_SNOW_STEP("block.snow.step", 93, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_STONE_BREAK("block.stone.break", 94, SoundCategory.BLOCK),

    BLOCK_STONE_FALL("block.stone.fall", 95, SoundCategory.BLOCK),

    /**
     * Block breaking
     */
    BLOCK_STONE_HIT("block.stone.hit", 96, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_STONE_PLACE("block.stone.place", 97, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_STONE_STEP("block.stone.step", 98, SoundCategory.BLOCK),

    /**
     * Button clicks
     */
    BLOCK_STONE_BUTTON_CLICK_OFF("block.stone_button.click_off", 99, SoundCategory.BLOCK),

    /**
     * Button clicks
     */
    BLOCK_STONE_BUTTON_CLICK_ON("block.stone_button.click_on", 100, SoundCategory.BLOCK),

    /**
     * Pressure Plate clicks
     */
    BLOCK_STONE_PRESSUREPLATE_CLICK_OFF("block.stone_pressureplate.click_off", 101, SoundCategory.BLOCK),

    /**
     * Pressure Plate clicks
     */
    BLOCK_STONE_PRESSUREPLATE_CLICK_ON("block.stone_pressureplate.click_on", 102, SoundCategory.BLOCK),

    /**
     * Tripwire attaches
     */
    BLOCK_TRIPWIRE_ATTACH("block.tripwire.attach", 103, SoundCategory.BLOCK),

    /**
     * Tripwire clicks
     */
    BLOCK_TRIPWIRE_CLICK_OFF("block.tripwire.click_off", 104, SoundCategory.BLOCK),

    /**
     * Tripwire clicks
     */
    BLOCK_TRIPWIRE_CLICK_ON("block.tripwire.click_on", 105, SoundCategory.BLOCK),

    /**
     * Tripwire detaches
     */
    BLOCK_TRIPWIRE_DETACH("block.tripwire.detach", 106, SoundCategory.BLOCK),

    /**
     * Water flows
     */
    BLOCK_WATER_AMBIENT("block.water.ambient", 107, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_WATERLILY_PLACE("block.waterlily.place", 108, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_WOOD_BREAK("block.wood.break", 109, SoundCategory.BLOCK),

    BLOCK_WOOD_FALL("block.wood.fall", 110, SoundCategory.BLOCK),

    /**
     * Block broken
     */
    BLOCK_WOOD_HIT("block.wood.hit", 111, SoundCategory.BLOCK),

    /**
     * Block placed
     */
    BLOCK_WOOD_PLACE("block.wood.place", 112, SoundCategory.BLOCK),

    /**
     * Footsteps
     */
    BLOCK_WOOD_STEP("block.wood.step", 113, SoundCategory.BLOCK),

    /**
     * Button clicks
     */
    BLOCK_WOOD_BUTTON_CLICK_OFF("block.wood_button.click_off", 114, SoundCategory.BLOCK),

    /**
     * Button clicks
     */
    BLOCK_WOOD_BUTTON_CLICK_ON("block.wood_button.click_on", 115, SoundCategory.BLOCK),

    /**
     * Button clicks
     */
    BLOCK_WOOD_PRESSUREPLATE_CLICK_OFF("block.wood_pressureplate.click_off", 116, SoundCategory.BLOCK),

    /**
     * Pressure Plate clicks
     */
    BLOCK_WOOD_PRESSUREPLATE_CLICK_ON("block.wood_pressureplate.click_on", 117, SoundCategory.BLOCK),

    /**
     * Door creaks
     */
    BLOCK_WOODEN_DOOR_CLOSE("block.wooden_door.close", 118, SoundCategory.BLOCK),

    /**
     * Door creaks
     */
    BLOCK_WOODEN_DOOR_OPEN("block.wooden_door.open", 119, SoundCategory.BLOCK),

    /**
     * Trapdoor creaks
     */
    BLOCK_WOODEN_TRAPDOOR_CLOSE("block.wooden_trapdoor.close", 120, SoundCategory.BLOCK),

    /**
     * Trapdoor creaks
     */
    BLOCK_WOODEN_TRAPDOOR_OPEN("block.wooden_trapdoor.open", 121, SoundCategory.BLOCK),

    /**
     * Thorns prick
     */
    ENCHANT_THORNS_HIT("enchant.thorns.hit", 122, SoundCategory.MASTER),

    /**
     * Block broken
     */
    ENTITY_ARMORSTAND_BREAK("entity.armorstand.break", 123, SoundCategory.MASTER),

    /**
     * Something fell
     */
    ENTITY_ARMORSTAND_FALL("entity.armorstand.fall", 124, SoundCategory.MASTER),

    /**
     * Block breaking
     */
    ENTITY_ARMORSTAND_HIT("entity.armorstand.hit", 125, SoundCategory.MASTER),

    /**
     * Block placed
     */
    ENTITY_ARMORSTAND_PLACE("entity.armorstand.place", 126, SoundCategory.MASTER),

    /**
     * Arrow hits
     */
    ENTITY_ARROW_HIT("entity.arrow.hit", 127, SoundCategory.MASTER),

    /**
     * Player hit
     */
    ENTITY_ARROW_HIT_PLAYER("entity.arrow.hit_player", 128, SoundCategory.MASTER),

    /**
     * Arrow fired
     */
    ENTITY_ARROW_SHOOT("entity.arrow.shoot", 129, SoundCategory.MASTER),

    /**
     * Bat screeches
     */
    ENTITY_BAT_AMBIENT("entity.bat.ambient", 130, SoundCategory.MASTER),

    /**
     * Bat dies
     */
    ENTITY_BAT_DEATH("entity.bat.death", 131, SoundCategory.MASTER),

    /**
     * Bat hurts
     */
    ENTITY_BAT_HURT("entity.bat.hurt", 132, SoundCategory.MASTER),

    ENTITY_BAT_LOOP("entity.bat.loop", 133, SoundCategory.MASTER),

    /**
     * Bat takes off
     */
    ENTITY_BAT_TAKEOFF("entity.bat.takeoff", 134, SoundCategory.MASTER),

    /**
     * Blaze breathes
     */
    ENTITY_BLAZE_AMBIENT("entity.blaze.ambient", 135, SoundCategory.MASTER),

    /**
     * Blaze crackles
     */
    ENTITY_BLAZE_BURN("entity.blaze.burn", 136, SoundCategory.MASTER),

    /**
     * Blaze dies
     */
    ENTITY_BLAZE_DEATH("entity.blaze.death", 137, SoundCategory.MASTER),

    /**
     * Blaze hurts
     */
    ENTITY_BLAZE_HURT("entity.blaze.hurt", 138, SoundCategory.MASTER),

    /**
     * Blaze shoots
     */
    ENTITY_BLAZE_SHOOT("entity.blaze.shoot", 139, SoundCategory.MASTER),

    /**
     * Fishing hook splashes
     */
    ENTITY_BOBBER_SPLASH("entity.bobber.splash", 140, SoundCategory.MASTER),

    /**
     * Bobber thrown
     */
    ENTITY_BOBBER_THROW("entity.bobber.throw", 141, SoundCategory.MASTER),

    /**
     * Cat meows
     */
    ENTITY_CAT_AMBIENT("entity.cat.ambient", 142, SoundCategory.MASTER),

    /**
     * Cat dies
     */
    ENTITY_CAT_DEATH("entity.cat.death", 143, SoundCategory.MASTER),

    ENTITY_CAT_HISS("entity.cat.hiss", 144, SoundCategory.MASTER),

    /**
     * Cat hurts
     */
    ENTITY_CAT_HURT("entity.cat.hurt", 145, SoundCategory.MASTER),

    /**
     * Cat meows
     */
    ENTITY_CAT_PURR("entity.cat.purr", 146, SoundCategory.MASTER),

    /**
     * Cat meows
     */
    ENTITY_CAT_PURREOW("entity.cat.purreow", 147, SoundCategory.MASTER),

    /**
     * Chicken clucks
     */
    ENTITY_CHICKEN_AMBIENT("entity.chicken.ambient", 148, SoundCategory.MASTER),

    /**
     * Chicken dies
     */
    ENTITY_CHICKEN_DEATH("entity.chicken.death", 149, SoundCategory.MASTER),

    /**
     * Chicken plops
     */
    ENTITY_CHICKEN_EGG("entity.chicken.egg", 150, SoundCategory.MASTER),

    /**
     * Chicken hurts
     */
    ENTITY_CHICKEN_HURT("entity.chicken.hurt", 151, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_CHICKEN_STEP("entity.chicken.step", 152, SoundCategory.MASTER),

    /**
     * Cow moos
     */
    ENTITY_COW_AMBIENT("entity.cow.ambient", 153, SoundCategory.MASTER),

    /**
     * Cow dies
     */
    ENTITY_COW_DEATH("entity.cow.death", 154, SoundCategory.MASTER),

    /**
     * Cow hurts
     */
    ENTITY_COW_HURT("entity.cow.hurt", 155, SoundCategory.MASTER),

    /**
     * Cow gets milked
     */
    ENTITY_COW_MILK("entity.cow.milk", 156, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_COW_STEP("entity.cow.step", 157, SoundCategory.MASTER),

    /**
     * Creeper dies
     */
    ENTITY_CREEPER_DEATH("entity.creeper.death", 158, SoundCategory.MASTER),

    /**
     * Creeper hurts
     */
    ENTITY_CREEPER_HURT("entity.creeper.hurt", 159, SoundCategory.MASTER),

    /**
     * Creeper hisses
     */
    ENTITY_CREEPER_PRIMED("entity.creeper.primed", 160, SoundCategory.MASTER),

    /**
     * Donkey hee-haws
     */
    ENTITY_DONKEY_AMBIENT("entity.donkey.ambient", 161, SoundCategory.MASTER),

    /**
     * Donkey neighs
     */
    ENTITY_DONKEY_ANGRY("entity.donkey.angry", 162, SoundCategory.MASTER),

    /**
     * Donkey Chest equips
     */
    ENTITY_DONKEY_CHEST("entity.donkey.chest", 163, SoundCategory.MASTER),

    /**
     * Donkey dies
     */
    ENTITY_DONKEY_DEATH("entity.donkey.death", 164, SoundCategory.MASTER),

    /**
     * Donkey hurts
     */
    ENTITY_DONKEY_HURT("entity.donkey.hurt", 165, SoundCategory.MASTER),

    /**
     * Egg flies
     */
    ENTITY_EGG_THROW("entity.egg.throw", 166, SoundCategory.MASTER),

    /**
     * Guardian moans
     */
    ENTITY_ELDER_GUARDIAN_AMBIENT("entity.elder_guardian.ambient", 167, SoundCategory.MASTER),

    /**
     * Guardian flaps
     */
    ENTITY_ELDER_GUARDIAN_AMBIENT_LAND("entity.elder_guardian.ambient_land", 168, SoundCategory.MASTER),

    /**
     * Guardian curses
     */
    ENTITY_ELDER_GUARDIAN_CURSE("entity.elder_guardian.curse", 169, SoundCategory.MASTER),

    /**
     * Guardian dies
     */
    ENTITY_ELDER_GUARDIAN_DEATH("entity.elder_guardian.death", 170, SoundCategory.MASTER),

    /**
     * Guardian dies
     */
    ENTITY_ELDER_GUARDIAN_DEATH_LAND("entity.elder_guardian.death_land", 171, SoundCategory.MASTER),

    /**
     * Guardian hurts
     */
    ENTITY_ELDER_GUARDIAN_HURT("entity.elder_guardian.hurt", 172, SoundCategory.MASTER),

    /**
     * Guardian hurts
     */
    ENTITY_ELDER_GUARDIAN_HURT_LAND("entity.elder_guardian.hurt_land", 173, SoundCategory.MASTER),

    /**
     * Dragon roars
     */
    ENTITY_ENDERDRAGON_AMBIENT("entity.enderdragon.ambient", 174, SoundCategory.MASTER),

    /**
     * Dragon dies
     */
    ENTITY_ENDERDRAGON_DEATH("entity.enderdragon.death", 175, SoundCategory.MASTER),

    /**
     * Dragon flaps
     */
    ENTITY_ENDERDRAGON_FLAP("entity.enderdragon.flap", 176, SoundCategory.MASTER),

    /**
     * Dragon growls
     */
    ENTITY_ENDERDRAGON_GROWL("entity.enderdragon.growl", 177, SoundCategory.MASTER),

    /**
     * Dragon hurts
     */
    ENTITY_ENDERDRAGON_HURT("entity.enderdragon.hurt", 178, SoundCategory.MASTER),

    /**
     * Dragon shoots
     */
    ENTITY_ENDERDRAGON_SHOOT("entity.enderdragon.shoot", 179, SoundCategory.MASTER),

    /**
     * Explosion
     */
    ENTITY_ENDERDRAGON_FIREBALL_EXPLODE("entity.enderdragon_fireball.explode", 180, SoundCategory.MASTER),

    /**
     * Endereye shoots
     */
    ENTITY_ENDEREYE_LAUNCH("entity.endereye.launch", 181, SoundCategory.MASTER),

    /**
     * Enderman vwoops
     */
    ENTITY_ENDERMEN_AMBIENT("entity.endermen.ambient", 182, SoundCategory.MASTER),

    /**
     * Enderman dies
     */
    ENTITY_ENDERMEN_DEATH("entity.endermen.death", 183, SoundCategory.MASTER),

    /**
     * Enderman hurts
     */
    ENTITY_ENDERMEN_HURT("entity.endermen.hurt", 184, SoundCategory.MASTER),

    /**
     * Enderman vwoops
     */
    ENTITY_ENDERMEN_SCREAM("entity.endermen.scream", 185, SoundCategory.MASTER),

    /**
     * Enderman cries out
     */
    ENTITY_ENDERMEN_STARE("entity.endermen.stare", 186, SoundCategory.MASTER),

    /**
     * Enderman teleports
     */
    ENTITY_ENDERMEN_TELEPORT("entity.endermen.teleport", 187, SoundCategory.MASTER),

    /**
     * Endermite scuttles
     */
    ENTITY_ENDERMITE_AMBIENT("entity.endermite.ambient", 188, SoundCategory.MASTER),

    /**
     * Endermite dies
     */
    ENTITY_ENDERMITE_DEATH("entity.endermite.death", 189, SoundCategory.MASTER),

    /**
     * Endermite hurts
     */
    ENTITY_ENDERMITE_HURT("entity.endermite.hurt", 190, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_ENDERMITE_STEP("entity.endermite.step", 191, SoundCategory.MASTER),

    /**
     * Ender Pearl flies
     */
    ENTITY_ENDERPEARL_THROW("entity.enderpearl.throw", 192, SoundCategory.MASTER),

    /**
     * Bottle thrown
     */
    ENTITY_EXPERIENCE_BOTTLE_THROW("entity.experience_bottle.throw", 193, SoundCategory.MASTER),

    /**
     * Experience gained
     */
    ENTITY_EXPERIENCE_ORB_PICKUP("entity.experience_orb.pickup", 194, SoundCategory.MASTER),

    /**
     * Experience gained
     */
    ENTITY_EXPERIENCE_ORB_TOUCH("entity.experience_orb.touch", 195, SoundCategory.MASTER),

    /**
     * Firework blasts
     */
    ENTITY_FIREWORK_BLAST("entity.firework.blast", 196, SoundCategory.MASTER),

    /**
     * Firework blasts
     */
    ENTITY_FIREWORK_BLAST_FAR("entity.firework.blast_far", 197, SoundCategory.MASTER),

    /**
     * Firework blasts
     */
    ENTITY_FIREWORK_LARGE_BLAST("entity.firework.large_blast", 198, SoundCategory.MASTER),

    /**
     * Firework blasts
     */
    ENTITY_FIREWORK_LARGE_BLAST_FAR("entity.firework.large_blast_far", 199, SoundCategory.MASTER),

    /**
     * Firework launches
     */
    ENTITY_FIREWORK_LAUNCH("entity.firework.launch", 200, SoundCategory.MASTER),

    /**
     * Firework launches
     */
    ENTITY_FIREWORK_SHOOT("entity.firework.shoot", 201, SoundCategory.MASTER),

    /**
     * Firework twinkles
     */
    ENTITY_FIREWORK_TWINKLE("entity.firework.twinkle", 202, SoundCategory.MASTER),

    /**
     * Firework twinkles
     */
    ENTITY_FIREWORK_TWINKLE_FAR("entity.firework.twinkle_far", 203, SoundCategory.MASTER),

    /**
     * Something fell
     */
    ENTITY_GENERIC_BIG_FALL("entity.generic.big_fall", 204, SoundCategory.MASTER),

    /**
     * Burning
     */
    ENTITY_GENERIC_BURN("entity.generic.burn", 205, SoundCategory.MASTER),

    /**
     * Dying
     */
    ENTITY_GENERIC_DEATH("entity.generic.death", 206, SoundCategory.MASTER),

    /**
     * Sipping
     */
    ENTITY_GENERIC_DRINK("entity.generic.drink", 207, SoundCategory.MASTER),

    /**
     * Eating
     */
    ENTITY_GENERIC_EAT("entity.generic.eat", 208, SoundCategory.MASTER),

    /**
     * Explosion
     */
    ENTITY_GENERIC_EXPLODE("entity.generic.explode", 209, SoundCategory.MASTER),

    /**
     * Fire extinguishes
     */
    ENTITY_GENERIC_EXTINGUISH_FIRE("entity.generic.extinguish_fire", 210, SoundCategory.MASTER),

    /**
     * Something hurts
     */
    ENTITY_GENERIC_HURT("entity.generic.hurt", 211, SoundCategory.MASTER),

    /**
     * Something tripped
     */
    ENTITY_GENERIC_SMALL_FALL("entity.generic.small_fall", 212, SoundCategory.MASTER),

    /**
     * Splashing
     */
    ENTITY_GENERIC_SPLASH("entity.generic.splash", 213, SoundCategory.MASTER),

    /**
     * Swimming
     */
    ENTITY_GENERIC_SWIM("entity.generic.swim", 214, SoundCategory.MASTER),

    /**
     * Ghast cries
     */
    ENTITY_GHAST_AMBIENT("entity.ghast.ambient", 215, SoundCategory.MASTER),

    /**
     * Ghast dies
     */
    ENTITY_GHAST_DEATH("entity.ghast.death", 216, SoundCategory.MASTER),

    /**
     * Ghast hurts
     */
    ENTITY_GHAST_HURT("entity.ghast.hurt", 217, SoundCategory.MASTER),

    ENTITY_GHAST_SCREAM("entity.ghast.scream", 218, SoundCategory.MASTER),

    /**
     * Ghast shoots
     */
    ENTITY_GHAST_SHOOT("entity.ghast.shoot", 219, SoundCategory.MASTER),

    /**
     * Ghast shoots
     */
    ENTITY_GHAST_WARN("entity.ghast.warn", 220, SoundCategory.MASTER),

    /**
     * Guardian moans
     */
    ENTITY_GUARDIAN_AMBIENT("entity.guardian.ambient", 221, SoundCategory.MASTER),

    /**
     * Guardian flaps
     */
    ENTITY_GUARDIAN_AMBIENT_LAND("entity.guardian.ambient_land", 222, SoundCategory.MASTER),

    /**
     * Guardian shoots
     */
    ENTITY_GUARDIAN_ATTACK("entity.guardian.attack", 223, SoundCategory.MASTER),

    /**
     * Guardian dies
     */
    ENTITY_GUARDIAN_DEATH("entity.guardian.death", 224, SoundCategory.MASTER),

    /**
     * Guardian dies
     */
    ENTITY_GUARDIAN_DEATH_LAND("entity.guardian.death_land", 225, SoundCategory.MASTER),

    /**
     * Guardian flops
     */
    ENTITY_GUARDIAN_FLOP("entity.guardian.flop", 226, SoundCategory.MASTER),

    /**
     * Guardian hurts
     */
    ENTITY_GUARDIAN_HURT("entity.guardian.hurt", 227, SoundCategory.MASTER),

    /**
     * Guardian hurts
     */
    ENTITY_GUARDIAN_HURT_LAND("entity.guardian.hurt_land", 228, SoundCategory.MASTER),

    /**
     * Horse neighs
     */
    ENTITY_HORSE_AMBIENT("entity.horse.ambient", 229, SoundCategory.MASTER),

    /**
     * Horse neighs
     */
    ENTITY_HORSE_ANGRY("entity.horse.angry", 230, SoundCategory.MASTER),

    /**
     * Horse armor equips
     */
    ENTITY_HORSE_ARMOR("entity.horse.armor", 231, SoundCategory.MASTER),

    /**
     * Horse breathes
     */
    ENTITY_HORSE_BREATHE("entity.horse.breathe", 232, SoundCategory.MASTER),

    /**
     * Horse dies
     */
    ENTITY_HORSE_DEATH("entity.horse.death", 233, SoundCategory.MASTER),

    /**
     * Horse eats
     */
    ENTITY_HORSE_EAT("entity.horse.eat", 234, SoundCategory.MASTER),

    /**
     * Horse gallops
     */
    ENTITY_HORSE_GALLOP("entity.horse.gallop", 235, SoundCategory.MASTER),

    /**
     * Horse hurts
     */
    ENTITY_HORSE_HURT("entity.horse.hurt", 236, SoundCategory.MASTER),

    /**
     * Horse jumps
     */
    ENTITY_HORSE_JUMP("entity.horse.jump", 237, SoundCategory.MASTER),

    ENTITY_HORSE_LAND("entity.horse.land", 238, SoundCategory.MASTER),

    /**
     * Saddle equips
     */
    ENTITY_HORSE_SADDLE("entity.horse.saddle", 239, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_HORSE_STEP("entity.horse.step", 240, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_HORSE_STEP_WOOD("entity.horse.step_wood", 241, SoundCategory.MASTER),

    /**
     * Something fell
     */
    ENTITY_HOSTILE_BIG_FALL("entity.hostile.big_fall", 242, SoundCategory.MASTER),

    /**
     * Dying
     */
    ENTITY_HOSTILE_DEATH("entity.hostile.death", 243, SoundCategory.MASTER),

    /**
     * Something hurts
     */
    ENTITY_HOSTILE_HURT("entity.hostile.hurt", 244, SoundCategory.MASTER),

    /**
     * Something tripped
     */
    ENTITY_HOSTILE_SMALL_FALL("entity.hostile.small_fall", 245, SoundCategory.MASTER),

    /**
     * Splashing
     */
    ENTITY_HOSTILE_SPLASH("entity.hostile.splash", 246, SoundCategory.MASTER),

    /**
     * Swimming
     */
    ENTITY_HOSTILE_SWIM("entity.hostile.swim", 247, SoundCategory.MASTER),

    /**
     * Iron Golem attacks
     */
    ENTITY_IRONGOLEM_ATTACK("entity.irongolem.attack", 248, SoundCategory.MASTER),

    /**
     * Iron Golem dies
     */
    ENTITY_IRONGOLEM_DEATH("entity.irongolem.death", 249, SoundCategory.MASTER),

    /**
     * Iron Golem hurts
     */
    ENTITY_IRONGOLEM_HURT("entity.irongolem.hurt", 250, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_IRONGOLEM_STEP("entity.irongolem.step", 251, SoundCategory.MASTER),

    /**
     * Item breaks
     */
    ENTITY_ITEM_BREAK("entity.item.break", 252, SoundCategory.MASTER),

    /**
     * Item plops
     */
    ENTITY_ITEM_PICKUP("entity.item.pickup", 253, SoundCategory.MASTER),

    /**
     * Item Frame fills
     */
    ENTITY_ITEMFRAME_ADD_ITEM("entity.itemframe.add_item", 254, SoundCategory.MASTER),

    /**
     * Item Frame breaks
     */
    ENTITY_ITEMFRAME_BREAK("entity.itemframe.break", 255, SoundCategory.MASTER),

    /**
     * Item Frame placed
     */
    ENTITY_ITEMFRAME_PLACE("entity.itemframe.place", 256, SoundCategory.MASTER),

    /**
     * Item Frame empties
     */
    ENTITY_ITEMFRAME_REMOVE_ITEM("entity.itemframe.remove_item", 257, SoundCategory.MASTER),

    /**
     * Item Frame clicks
     */
    ENTITY_ITEMFRAME_ROTATE_ITEM("entity.itemframe.rotate_item", 258, SoundCategory.MASTER),

    /**
     * Leash knot breaks
     */
    ENTITY_LEASHKNOT_BREAK("entity.leashknot.break", 259, SoundCategory.MASTER),

    /**
     * Leash knot tied
     */
    ENTITY_LEASHKNOT_PLACE("entity.leashknot.place", 260, SoundCategory.MASTER),

    /**
     * Lighting strikes
     */
    ENTITY_LIGHTNING_IMPACT("entity.lightning.impact", 261, SoundCategory.MASTER),

    /**
     * Thunder roars
     */
    ENTITY_LIGHTNING_THUNDER("entity.lightning.thunder", 262, SoundCategory.MASTER),

    /**
     * Bottle thrown
     */
    ENTITY_LINGERINGPOTION_THROW("entity.lingeringpotion.throw", 263, SoundCategory.MASTER),

    /**
     * Magma Cube dies
     */
    ENTITY_MAGMACUBE_DEATH("entity.magmacube.death", 264, SoundCategory.MASTER),

    /**
     * Magma Cube hurts
     */
    ENTITY_MAGMACUBE_HURT("entity.magmacube.hurt", 265, SoundCategory.MASTER),

    /**
     * Magma Cube squishes
     */
    ENTITY_MAGMACUBE_JUMP("entity.magmacube.jump", 266, SoundCategory.MASTER),

    /**
     * Magma Cube squishes
     */
    ENTITY_MAGMACUBE_SQUISH("entity.magmacube.squish", 267, SoundCategory.MASTER),

    ENTITY_MINECART_INSIDE("entity.minecart.inside", 268, SoundCategory.MASTER),

    /**
     * Minecart rolls
     */
    ENTITY_MINECART_RIDING("entity.minecart.riding", 269, SoundCategory.MASTER),

    /**
     * Shears click
     */
    ENTITY_MOOSHROOM_SHEAR("entity.mooshroom.shear", 270, SoundCategory.MASTER),

    /**
     * Mule hee-haws
     */
    ENTITY_MULE_AMBIENT("entity.mule.ambient", 271, SoundCategory.MASTER),

    /**
     * Mule dies
     */
    ENTITY_MULE_DEATH("entity.mule.death", 272, SoundCategory.MASTER),

    /**
     * Mule hurts
     */
    ENTITY_MULE_HURT("entity.mule.hurt", 273, SoundCategory.MASTER),

    /**
     * Painting breaks
     */
    ENTITY_PAINTING_BREAK("entity.painting.break", 274, SoundCategory.MASTER),

    /**
     * Painting placed
     */
    ENTITY_PAINTING_PLACE("entity.painting.place", 275, SoundCategory.MASTER),

    /**
     * Pig oinks
     */
    ENTITY_PIG_AMBIENT("entity.pig.ambient", 276, SoundCategory.MASTER),

    /**
     * Pig dies
     */
    ENTITY_PIG_DEATH("entity.pig.death", 277, SoundCategory.MASTER),

    /**
     * Pig hurts
     */
    ENTITY_PIG_HURT("entity.pig.hurt", 278, SoundCategory.MASTER),

    /**
     * Saddle equips
     */
    ENTITY_PIG_SADDLE("entity.pig.saddle", 279, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_PIG_STEP("entity.pig.step", 280, SoundCategory.MASTER),

    ENTITY_PLAYER_ATTACK_CRIT("entity.player.attack.crit", 281, SoundCategory.MASTER),

    ENTITY_PLAYER_ATTACK_KNOCKBACK("entity.player.attack.knockback", 282, SoundCategory.MASTER),

    ENTITY_PLAYER_ATTACK_NODAMAGE("entity.player.attack.nodamage", 283, SoundCategory.MASTER),

    ENTITY_PLAYER_ATTACK_STRONG("entity.player.attack.strong", 284, SoundCategory.MASTER),

    ENTITY_PLAYER_ATTACK_SWEEP("entity.player.attack.sweep", 285, SoundCategory.MASTER),

    ENTITY_PLAYER_ATTACK_WEAK("entity.player.attack.weak", 286, SoundCategory.MASTER),

    /**
     * Something fell
     */
    ENTITY_PLAYER_BIG_FALL("entity.player.big_fall", 287, SoundCategory.MASTER),

    ENTITY_PLAYER_BREATH("entity.player.breath", 288, SoundCategory.MASTER),

    /**
     * Burp
     */
    ENTITY_PLAYER_BURP("entity.player.burp", 289, SoundCategory.MASTER),

    /**
     * Player dies
     */
    ENTITY_PLAYER_DEATH("entity.player.death", 290, SoundCategory.MASTER),

    /**
     * Player hurts
     */
    ENTITY_PLAYER_HURT("entity.player.hurt", 291, SoundCategory.MASTER),

    /**
     * Player dings
     */
    ENTITY_PLAYER_LEVELUP("entity.player.levelup", 292, SoundCategory.MASTER),

    /**
     * Something tripped
     */
    ENTITY_PLAYER_SMALL_FALL("entity.player.small_fall", 293, SoundCategory.MASTER),

    /**
     * Splashing
     */
    ENTITY_PLAYER_SPLASH("entity.player.splash", 294, SoundCategory.MASTER),

    /**
     * Swimming
     */
    ENTITY_PLAYER_SWIM("entity.player.swim", 295, SoundCategory.MASTER),

    /**
     * Rabbit squeaks
     */
    ENTITY_RABBIT_AMBIENT("entity.rabbit.ambient", 296, SoundCategory.MASTER),

    /**
     * Rabbit attacks
     */
    ENTITY_RABBIT_ATTACK("entity.rabbit.attack", 297, SoundCategory.MASTER),

    /**
     * Rabbit dies
     */
    ENTITY_RABBIT_DEATH("entity.rabbit.death", 298, SoundCategory.MASTER),

    /**
     * Rabbit hurts
     */
    ENTITY_RABBIT_HURT("entity.rabbit.hurt", 299, SoundCategory.MASTER),

    /**
     * Rabbit hops
     */
    ENTITY_RABBIT_JUMP("entity.rabbit.jump", 300, SoundCategory.MASTER),

    /**
     * Sheep baahs
     */
    ENTITY_SHEEP_AMBIENT("entity.sheep.ambient", 301, SoundCategory.MASTER),

    /**
     * Sheep dies
     */
    ENTITY_SHEEP_DEATH("entity.sheep.death", 302, SoundCategory.MASTER),

    /**
     * Sheep hurts
     */
    ENTITY_SHEEP_HURT("entity.sheep.hurt", 303, SoundCategory.MASTER),

    /**
     * Shears click
     */
    ENTITY_SHEEP_SHEAR("entity.sheep.shear", 304, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_SHEEP_STEP("entity.sheep.step", 305, SoundCategory.MASTER),

    /**
     * Shulker lurks
     */
    ENTITY_SHULKER_AMBIENT("entity.shulker.ambient", 306, SoundCategory.MASTER),

    /**
     * Shulker closes
     */
    ENTITY_SHULKER_CLOSE("entity.shulker.close", 307, SoundCategory.MASTER),

    /**
     * Shulker dies
     */
    ENTITY_SHULKER_DEATH("entity.shulker.death", 308, SoundCategory.MASTER),

    /**
     * Shulker hurts
     */
    ENTITY_SHULKER_HURT("entity.shulker.hurt", 309, SoundCategory.MASTER),

    /**
     * Shulker hurts
     */
    ENTITY_SHULKER_HURT_CLOSED("entity.shulker.hurt_closed", 310, SoundCategory.MASTER),

    /**
     * Shulker opens
     */
    ENTITY_SHULKER_OPEN("entity.shulker.open", 311, SoundCategory.MASTER),

    /**
     * Shulker shoots
     */
    ENTITY_SHULKER_SHOOT("entity.shulker.shoot", 312, SoundCategory.MASTER),

    /**
     * Shulker teleports
     */
    ENTITY_SHULKER_TELEPORT("entity.shulker.teleport", 313, SoundCategory.MASTER),

    /**
     * Shulker bullet explodes
     */
    ENTITY_SHULKER_BULLET_HIT("entity.shulker_bullet.hit", 314, SoundCategory.MASTER),

    /**
     * Shulker bullet breaks
     */
    ENTITY_SHULKER_BULLET_HURT("entity.shulker_bullet.hurt", 315, SoundCategory.MASTER),

    /**
     * Silverfish hisses
     */
    ENTITY_SILVERFISH_AMBIENT("entity.silverfish.ambient", 316, SoundCategory.MASTER),

    /**
     * Silverfish dies
     */
    ENTITY_SILVERFISH_DEATH("entity.silverfish.death", 317, SoundCategory.MASTER),

    /**
     * Silverfish hurts
     */
    ENTITY_SILVERFISH_HURT("entity.silverfish.hurt", 318, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_SILVERFISH_STEP("entity.silverfish.step", 319, SoundCategory.MASTER),

    /**
     * Skeleton rattles
     */
    ENTITY_SKELETON_AMBIENT("entity.skeleton.ambient", 320, SoundCategory.MASTER),

    /**
     * Skeleton dies
     */
    ENTITY_SKELETON_DEATH("entity.skeleton.death", 321, SoundCategory.MASTER),

    /**
     * Skeleton hurts
     */
    ENTITY_SKELETON_HURT("entity.skeleton.hurt", 322, SoundCategory.MASTER),

    /**
     * Skeleton shoots
     */
    ENTITY_SKELETON_SHOOT("entity.skeleton.shoot", 323, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_SKELETON_STEP("entity.skeleton.step", 324, SoundCategory.MASTER),

    /**
     * Skeleton Horse cries
     */
    ENTITY_SKELETON_HORSE_AMBIENT("entity.skeleton_horse.ambient", 325, SoundCategory.MASTER),

    /**
     * Skeleton Horse dies
     */
    ENTITY_SKELETON_HORSE_DEATH("entity.skeleton_horse.death", 326, SoundCategory.MASTER),

    /**
     * Skeleton Horse hurts
     */
    ENTITY_SKELETON_HORSE_HURT("entity.skeleton_horse.hurt", 327, SoundCategory.MASTER),

    /**
     * Slime attacks
     */
    ENTITY_SLIME_ATTACK("entity.slime.attack", 328, SoundCategory.MASTER),

    /**
     * Slime dies
     */
    ENTITY_SLIME_DEATH("entity.slime.death", 329, SoundCategory.MASTER),

    /**
     * Slime hurts
     */
    ENTITY_SLIME_HURT("entity.slime.hurt", 330, SoundCategory.MASTER),

    /**
     * Slime squishes
     */
    ENTITY_SLIME_JUMP("entity.slime.jump", 331, SoundCategory.MASTER),

    /**
     * Slime squishes
     */
    ENTITY_SLIME_SQUISH("entity.slime.squish", 332, SoundCategory.MASTER),

    /**
     * Magma Cube dies
     */
    ENTITY_SMALL_MAGMACUBE_DEATH("entity.small_magmacube.death", 333, SoundCategory.MASTER),

    /**
     * Magma Cube hurts
     */
    ENTITY_SMALL_MAGMACUBE_HURT("entity.small_magmacube.hurt", 334, SoundCategory.MASTER),

    /**
     * Magma Cube squishes
     */
    ENTITY_SMALL_MAGMACUBE_SQUISH("entity.small_magmacube.squish", 335, SoundCategory.MASTER),

    /**
     * Slime dies
     */
    ENTITY_SMALL_SLIME_DEATH("entity.small_slime.death", 336, SoundCategory.MASTER),

    /**
     * Slime hurts
     */
    ENTITY_SMALL_SLIME_HURT("entity.small_slime.hurt", 337, SoundCategory.MASTER),

    ENTITY_SMALL_SLIME_JUMP("entity.small_slime.jump", 338, SoundCategory.MASTER),

    ENTITY_SMALL_SLIME_SQUISH("entity.small_slime.squish", 339, SoundCategory.MASTER),

    /**
     * Snowball flies
     */
    ENTITY_SNOWBALL_THROW("entity.snowball.throw", 340, SoundCategory.MASTER),

    ENTITY_SNOWMAN_AMBIENT("entity.snowman.ambient", 341, SoundCategory.MASTER),

    /**
     * Snow Golem dies
     */
    ENTITY_SNOWMAN_DEATH("entity.snowman.death", 342, SoundCategory.MASTER),

    /**
     * Snow Golem hurts
     */
    ENTITY_SNOWMAN_HURT("entity.snowman.hurt", 343, SoundCategory.MASTER),

    /**
     * Snowball flies
     */
    ENTITY_SNOWMAN_SHOOT("entity.snowman.shoot", 344, SoundCategory.MASTER),

    /**
     * Spider hisses
     */
    ENTITY_SPIDER_AMBIENT("entity.spider.ambient", 345, SoundCategory.MASTER),

    /**
     * Spider dies
     */
    ENTITY_SPIDER_DEATH("entity.spider.death", 346, SoundCategory.MASTER),

    /**
     * Spider hurts
     */
    ENTITY_SPIDER_HURT("entity.spider.hurt", 347, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_SPIDER_STEP("entity.spider.step", 348, SoundCategory.MASTER),

    /**
     * Bottle smashes
     */
    ENTITY_SPLASH_POTION_BREAK("entity.splash_potion.break", 349, SoundCategory.MASTER),

    /**
     * Bottle thrown
     */
    ENTITY_SPLASH_POTION_THROW("entity.splash_potion.throw", 350, SoundCategory.MASTER),

    /**
     * Squid swims
     */
    ENTITY_SQUID_AMBIENT("entity.squid.ambient", 351, SoundCategory.MASTER),

    /**
     * Squid dies
     */
    ENTITY_SQUID_DEATH("entity.squid.death", 352, SoundCategory.MASTER),

    /**
     * Squid hurts
     */
    ENTITY_SQUID_HURT("entity.squid.hurt", 353, SoundCategory.MASTER),

    /**
     * TNT fizzes
     */
    ENTITY_TNT_PRIMED("entity.tnt.primed", 354, SoundCategory.MASTER),

    /**
     * Villager mumbles
     */
    ENTITY_VILLAGER_AMBIENT("entity.villager.ambient", 355, SoundCategory.MASTER),

    /**
     * Villager dies
     */
    ENTITY_VILLAGER_DEATH("entity.villager.death", 356, SoundCategory.MASTER),

    /**
     * Villager hurts
     */
    ENTITY_VILLAGER_HURT("entity.villager.hurt", 357, SoundCategory.MASTER),

    /**
     * Villager disagrees
     */
    ENTITY_VILLAGER_NO("entity.villager.no", 358, SoundCategory.MASTER),

    /**
     * Villager trades
     */
    ENTITY_VILLAGER_TRADING("entity.villager.trading", 359, SoundCategory.MASTER),

    /**
     * Villager agrees
     */
    ENTITY_VILLAGER_YES("entity.villager.yes", 360, SoundCategory.MASTER),

    /**
     * Witch giggles
     */
    ENTITY_WITCH_AMBIENT("entity.witch.ambient", 361, SoundCategory.MASTER),

    /**
     * Witch dies
     */
    ENTITY_WITCH_DEATH("entity.witch.death", 362, SoundCategory.MASTER),

    /**
     * Witch drinks
     */
    ENTITY_WITCH_DRINK("entity.witch.drink", 363, SoundCategory.MASTER),

    /**
     * Witch hurts
     */
    ENTITY_WITCH_HURT("entity.witch.hurt", 364, SoundCategory.MASTER),

    /**
     * Witch throws
     */
    ENTITY_WITCH_THROW("entity.witch.throw", 365, SoundCategory.MASTER),

    /**
     * Wither angers
     */
    ENTITY_WITHER_AMBIENT("entity.wither.ambient", 366, SoundCategory.MASTER),

    /**
     * Wither attacks
     */
    ENTITY_WITHER_BREAK_BLOCK("entity.wither.break_block", 367, SoundCategory.MASTER),

    /**
     * Wither dies
     */
    ENTITY_WITHER_DEATH("entity.wither.death", 368, SoundCategory.MASTER),

    /**
     * Wither hurts
     */
    ENTITY_WITHER_HURT("entity.wither.hurt", 369, SoundCategory.MASTER),

    /**
     * Wither attacks
     */
    ENTITY_WITHER_SHOOT("entity.wither.shoot", 370, SoundCategory.MASTER),

    /**
     * Wither released
     */
    ENTITY_WITHER_SPAWN("entity.wither.spawn", 371, SoundCategory.MASTER),

    /**
     * Wolf pants
     */
    ENTITY_WOLF_AMBIENT("entity.wolf.ambient", 372, SoundCategory.MASTER),

    /**
     * Wolf dies
     */
    ENTITY_WOLF_DEATH("entity.wolf.death", 373, SoundCategory.MASTER),

    /**
     * Wolf growls
     */
    ENTITY_WOLF_GROWL("entity.wolf.growl", 374, SoundCategory.MASTER),

    ENTITY_WOLF_HOWL("entity.wolf.howl", 375, SoundCategory.MASTER),

    /**
     * Wolf hurts
     */
    ENTITY_WOLF_HURT("entity.wolf.hurt", 376, SoundCategory.MASTER),

    /**
     * Wolf pants
     */
    ENTITY_WOLF_PANT("entity.wolf.pant", 377, SoundCategory.MASTER),

    /**
     * Wolf shakes
     */
    ENTITY_WOLF_SHAKE("entity.wolf.shake", 378, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_WOLF_STEP("entity.wolf.step", 379, SoundCategory.MASTER),

    /**
     * Wolf pants
     */
    ENTITY_WOLF_WHINE("entity.wolf.whine", 380, SoundCategory.MASTER),

    /**
     * Zombie groans
     */
    ENTITY_ZOMBIE_AMBIENT("entity.zombie.ambient", 381, SoundCategory.MASTER),

    /**
     * Block broken
     */
    ENTITY_ZOMBIE_ATTACK_DOOR_WOOD("entity.zombie.attack_door_wood", 382, SoundCategory.MASTER),

    /**
     * Block broken
     */
    ENTITY_ZOMBIE_ATTACK_IRON_DOOR("entity.zombie.attack_iron_door", 383, SoundCategory.MASTER),

    /**
     * Block broken
     */
    ENTITY_ZOMBIE_BREAK_DOOR_WOOD("entity.zombie.break_door_wood", 384, SoundCategory.MASTER),

    /**
     * Zombie dies
     */
    ENTITY_ZOMBIE_DEATH("entity.zombie.death", 385, SoundCategory.MASTER),

    /**
     * Zombie hurts
     */
    ENTITY_ZOMBIE_HURT("entity.zombie.hurt", 386, SoundCategory.MASTER),

    /**
     * Zombie infects
     */
    ENTITY_ZOMBIE_INFECT("entity.zombie.infect", 387, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_ZOMBIE_STEP("entity.zombie.step", 388, SoundCategory.MASTER),

    /**
     * Zombie Horse cries
     */
    ENTITY_ZOMBIE_HORSE_AMBIENT("entity.zombie_horse.ambient", 389, SoundCategory.MASTER),

    /**
     * Zombie Horse dies
     */
    ENTITY_ZOMBIE_HORSE_DEATH("entity.zombie_horse.death", 390, SoundCategory.MASTER),

    /**
     * Zombie Horse hurts
     */
    ENTITY_ZOMBIE_HORSE_HURT("entity.zombie_horse.hurt", 391, SoundCategory.MASTER),

    /**
     * Zombie Pigman grunts
     */
    ENTITY_ZOMBIE_PIG_AMBIENT("entity.zombie_pig.ambient", 392, SoundCategory.MASTER),

    /**
     * Zombie Pigman angers
     */
    ENTITY_ZOMBIE_PIG_ANGRY("entity.zombie_pig.angry", 393, SoundCategory.MASTER),

    /**
     * Zombie Pigman dies
     */
    ENTITY_ZOMBIE_PIG_DEATH("entity.zombie_pig.death", 394, SoundCategory.MASTER),

    /**
     * Zombie Pigman hurts
     */
    ENTITY_ZOMBIE_PIG_HURT("entity.zombie_pig.hurt", 395, SoundCategory.MASTER),

    /**
     * Zombie groans
     */
    ENTITY_ZOMBIE_VILLAGER_AMBIENT("entity.zombie_villager.ambient", 396, SoundCategory.MASTER),

    /**
     * Zombie vociferates
     */
    ENTITY_ZOMBIE_VILLAGER_CONVERTED("entity.zombie_villager.converted", 397, SoundCategory.MASTER),

    /**
     * Zombie snuffles
     */
    ENTITY_ZOMBIE_VILLAGER_CURE("entity.zombie_villager.cure", 398, SoundCategory.MASTER),

    /**
     * Zombie dies
     */
    ENTITY_ZOMBIE_VILLAGER_DEATH("entity.zombie_villager.death", 399, SoundCategory.MASTER),

    /**
     * Zombie hurts
     */
    ENTITY_ZOMBIE_VILLAGER_HURT("entity.zombie_villager.hurt", 400, SoundCategory.MASTER),

    /**
     * Footsteps
     */
    ENTITY_ZOMBIE_VILLAGER_STEP("entity.zombie_villager.step", 401, SoundCategory.MASTER),

    /**
     * Chain armor jingles
     */
    ITEM_ARMOR_EQUIP_CHAIN("item.armor.equip_chain", 402, SoundCategory.MASTER),

    /**
     * Diamond armor clangs
     */
    ITEM_ARMOR_EQUIP_DIAMOND("item.armor.equip_diamond", 403, SoundCategory.MASTER),

    /**
     * Gear equipped
     */
    ITEM_ARMOR_EQUIP_GENERIC("item.armor.equip_generic", 404, SoundCategory.MASTER),

    /**
     * Gold armor clinks
     */
    ITEM_ARMOR_EQUIP_GOLD("item.armor.equip_gold", 405, SoundCategory.MASTER),

    /**
     * Iron armor clanks
     */
    ITEM_ARMOR_EQUIP_IRON("item.armor.equip_iron", 406, SoundCategory.MASTER),

    /**
     * Leather armor rustles
     */
    ITEM_ARMOR_EQUIP_LEATHER("item.armor.equip_leather", 407, SoundCategory.MASTER),

    /**
     * Bottle fills
     */
    ITEM_BOTTLE_FILL("item.bottle.fill", 408, SoundCategory.MASTER),

    /**
     * Bottle fills
     */
    ITEM_BOTTLE_FILL_DRAGONBREATH("item.bottle.fill_dragonbreath", 409, SoundCategory.MASTER),

    /**
     * Bucket empties
     */
    ITEM_BUCKET_EMPTY("item.bucket.empty", 410, SoundCategory.MASTER),

    /**
     * Bucket empties
     */
    ITEM_BUCKET_EMPTY_LAVA("item.bucket.empty_lava", 411, SoundCategory.MASTER),

    /**
     * Bucket fills
     */
    ITEM_BUCKET_FILL("item.bucket.fill", 412, SoundCategory.MASTER),

    /**
     * Bucket fills
     */
    ITEM_BUCKET_FILL_LAVA("item.bucket.fill_lava", 413, SoundCategory.MASTER),

    /**
     * Player teleports
     */
    ITEM_CHORUS_FRUIT_TELEPORT("item.chorus_fruit.teleport", 414, SoundCategory.MASTER),

    /**
     * Fireball whooshes
     */
    ITEM_FIRECHARGE_USE("item.firecharge.use", 415, SoundCategory.MASTER),

    /**
     * Flint and Steel click
     */
    ITEM_FLINTANDSTEEL_USE("item.flintandsteel.use", 416, SoundCategory.MASTER),

    /**
     * Hoe tills
     */
    ITEM_HOE_TILL("item.hoe.till", 417, SoundCategory.MASTER),

    /**
     * Shield blocks
     */
    ITEM_SHIELD_BLOCK("item.shield.block", 418, SoundCategory.MASTER),

    /**
     * Item breaks
     */
    ITEM_SHIELD_BREAK("item.shield.break", 419, SoundCategory.MASTER),

    /**
     * Shovel flattens
     */
    ITEM_SHOVEL_FLATTEN("item.shovel.flatten", 420, SoundCategory.MASTER),

    MUSIC_CREATIVE("music.creative", 421, SoundCategory.MUSIC),

    MUSIC_CREDITS("music.credits", 422, SoundCategory.MUSIC),

    MUSIC_DRAGON("music.dragon", 423, SoundCategory.MUSIC),

    MUSIC_END("music.end", 424, SoundCategory.MUSIC),

    MUSIC_GAME("music.game", 425, SoundCategory.MUSIC),

    MUSIC_MENU("music.menu", 426, SoundCategory.MUSIC),

    MUSIC_NETHER("music.nether", 427, SoundCategory.MUSIC),

    RECORD_11("record.11", 428, SoundCategory.RECORD),

    RECORD_13("record.13", 429, SoundCategory.RECORD),

    RECORD_BLOCKS("record.blocks", 430, SoundCategory.RECORD),

    RECORD_CAT("record.cat", 431, SoundCategory.RECORD),

    RECORD_CHIRP("record.chirp", 432, SoundCategory.RECORD),

    RECORD_FAR("record.far", 433, SoundCategory.RECORD),

    RECORD_MALL("record.mall", 434, SoundCategory.RECORD),

    RECORD_MELLOHI("record.mellohi", 435, SoundCategory.RECORD),

    RECORD_STAL("record.stal", 436, SoundCategory.RECORD),

    RECORD_STRAD("record.strad", 437, SoundCategory.RECORD),

    RECORD_WAIT("record.wait", 438, SoundCategory.RECORD),

    RECORD_WARD("record.ward", 439, SoundCategory.RECORD),

    UI_BUTTON_CLICK("ui.button.click", 440, SoundCategory.MASTER),

    /**
     * Rain falls
     */
    WEATHER_RAIN("weather.rain", 441, SoundCategory.WEATHER),

    /**
     * Rain falls
     */
    WEATHER_RAIN_ABOVE("weather.rain.above", 442, SoundCategory.WEATHER);

    private final String name;
    private final int id;
    private final SoundCategory category;

    SoundEffectType(String name, int id, SoundCategory category) {
        this.name = name;
        this.id = id;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public SoundCategory getCategory() {
        return category;
    }

}