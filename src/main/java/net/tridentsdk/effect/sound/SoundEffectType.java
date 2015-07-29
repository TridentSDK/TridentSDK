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
     * Being within 10 to 20 blocks of an area not exposed to the sky with a light level of less than eight
     */
    AMBIENT_CAVE("ambient.cave.cave"),

    /**
     * Being close to the surface of a block open to the sky when it is raining
     */
    AMBIENT_RAIN("ambient.weather.rain"),

    /**
     * Lightning strikes
     */
    AMBIENT_THUNDER("ambient.weather.thunder"),

    /**
     * A player contacting the surface of a block after falling from a tall height
     */
    PLAYER_FALL_BIG("game.player.hurt.fall.big"),

    /**
     * A passive mob contacting the surface of a block after falling from a tall height
     */
    NEUTRAL_FALL_BIG("game.neutral.hurt.fall.big"),

    /**
     * A hostile mob contacting the surface of a block after falling from a tall height
     */
    HOSTILE_FALL_BIG("game.hostile.hurt.fall.big"),

    /**
     * A player contacting the surface of a block after falling from a short height
     */
    PLAYER_FALL_SMALL("game.player.hurt.fall.small"),

    /**
     * A passive mob contacting the surface of a block after falling from a short height
     */
    NEUTRAL_FALL_SMALL("game.neutral.hurt.fall.small"),

    /**
     * A hostile mob contacting the surface of a block after falling from a short height
     */
    HOSTILE_FALL_SMALL("game.hostile.hurt.fall.small"),

    /**
     * A player taking damage
     */
    PLAYER_HURT("game.player.hurt"),

    /**
     * A passive mob taking damage.
     * Always overridden by the damage sound specific to each mob
     */
    NEUTRAL_HURT("game.neutral.hurt"),

    /**
     * A hostile mob taking damage. Used only by Giants.
     * Overridden by the damage sound specific to each mob in any other case
     */
    HOSTILE_HURT("game.hostile.hurt"),

    /**
     * A player dies
     */
    PLAYER_DIE("game.player.die"),

    /**
     * A passive mob dies. Always overridden by the death sound specific to each mob
     */
    NEUTRAL_DIE("game.neutral.die"),

    /**
     * A hostile mob dies. Used only by Giants.
     * Overridden by the death sound specific to each mob in any other case
     */
    HOSTILE_DIE("game.hostile.die"),

    /**
     * Placing or breaking blocks classified as "cloth"
     */
    DIG_CLOTH("dig.cloth"),

    /**
     * Placing or breaking blocks classified as "glass"
     */
    DIG_GLASS("dig.glass"),

    /**
     * Placing or breaking blocks classified as "grass" or a Sheep eats Grass
     */
    DIG_GRASS("dig.grass"),

    /**
     * Placing or breaking blocks classified as "gravel"
     */
    DIG_GRAVEL("dig.gravel"),

    /**
     * Placing or breaking blocks classified as "sand"
     */
    DIG_SAND("dig.sand"),

    /**
     * Placing or breaking blocks classified as "snow"
     */
    DIG_SNOW("dig.snow"),

    /**
     * Placing or breaking blocks classified as "stone"
     */
    DIG_STONE("dig.stone"),

    /**
     * Placing or breaking blocks classified as "wood"
     */
    DIG_WOOD("dig.wood"),

    /**
     * A thrown Splash Potion breaks
     */
    POTION_SMASH("game.potion.smash"),

    /**
     * Being near Fire or a Blaze
     */
    FIRE_ACTIVE("fire.fire"),

    /**
     * Setting Fire or igniting a Creeper using a Flint and Steel
     */
    FIRE_IGNITE("fire.ignite"),

    /**
     * Setting Fire using a Fire Charge
     */
    FIRECHARGE_USE("item.fireCharge.use"),

    /**
     * A small, close firework explosion
     */
    FIREWORKS_BLAST("fireworks.blast"),

    /**
     * A small, far firework explosion
     */
    FIREWORKS_BLAST_FAR("fireworks.blast_far"),

    /**
     * A large, close firework explosion
     */
    FIREWORKS_LARGE_BLAST("fireworks.largeBlast"),

    /**
     * A large, far firework explosion
     */
    FIREWORKS_LARGE_BLAST_FAR("fireworks.largeBlast_far"),

    /**
     * A firework being launched by a player or a dispenser
     */
    FIREWORKS_LAUNCH("fireworks.launch"),

    /**
     * A close firework twinkles
     */
    FIREWORKS_TWINKLE("fireworks.twinkle"),

    /**
     * A faraway firework twinkles
     */
    FIREWORKS_TWINKLE_FAR("fireworks.twinkle_far"),

    /**
     * A player is swimming
     */
    PLAYER_SWIM("game.player.swim"),

    /**
     * A passive mob is swimming
     */
    NEUTRAL_SWIM("game.neutral.swim"),

    /**
     * A hostile mob is swimming
     */
    HOSTILE_SWIM("game.hostile.swim"),

    /**
     * A player falls into water
     */
    PLAYER_SWIM_SPLASH("game.player.swim.splash"),

    /**
     * A passive mob falls into water or a Fishing Rod Bobber is cast into Water
     */
    NEUTRAL_SWIM_SPLASH("game.neutral.swim.splash"),

    /**
     * A hostile mob falls into water
     */
    HOSTILE_SWIM_SPLASH("game.hostile.swim.splash"),

    /**
     * Being near Lava
     */
    LAVA("liquid.lava"),

    /**
     * Randomly when being near Lava. Accompanied by a lava fireball particle effect
     */
    LAVAPOP("liquid.lavapop"),

    /**
     * Being near flowing Water
     */
    WATER("liquid.water"),

    /**
     * A minecart moving
     */
    MINECART_BASE("minecart.base"),

    /**
     * Being inside a moving minecart
     */
    MINECART_INSIDE("minecart.inside"),

    /**
     * ...?
     */
    NOTE_BASS("note.bass"),

    /**
     * A Note Block that is on top of a "wood" block is clicked or powered by redstone
     */
    NOTE_BASSATTACK("note.bassattack"),

    /**
     * A Note Block that is on top of a "stone" block is clicked or powered by redstone
     */
    NOTE_BD("note.bd"),

    /**
     * A Note Block that is on top of an "other" block is clicked or powered by redstone
     */
    NOTE_HARP("note.harp"),

    /**
     * A Note Block that is on top of a "glass" block is clicked or powered by redstone
     */
    NOTE_HAT("note.hat"),

    /**
     * ...?
     */
    NOTE_PLING("note.pling"),

    /**
     * A Note Block that is on top of a "sand" block is clicked or powered by redstone
     */
    NOTE_SNARE("note.snare"),

    /**
     * Being near a Nether portal block
     */
    PORTAL("portal.portal"),

    /**
     * ...?
     */
    PORTAL_TRAVEL("portal.travel"),

    /**
     * A player travels through or steps out of a Nether portal
     */
    PORTAL_TRIGGER("portal.trigger"),

    /**
     * An Anvil breaks
     */
    RANDOM_ANVIL_BREAK("random.anvil_break"),

    /**
     * An Anvil is placed or lands after falling
     */
    RANDOM_ANVIL_LAND("random.anvil_land"),

    /**
     * A player removes an item from the output slot in the Anvil GUI
     */
    RANDOM_ANVIL_USE("random.anvil_use"),

    /**
     * A player or Skeleton shoots an Arrow.
     * A player casts a Fishing Rod.
     * A player throws a Snowball, Egg, Splash Potion, Bottle O' Enchanting, Ender Pearl, or Eye of Ender.
     * A Snow Golem throws a snowball at a mob.
     * A Dispenser shoots an Arrow, Snowball, Egg, Splash Potion, or Bottle O' Enchanting
     */
    RANDOM_BOW("random.bow"),

    /**
     * An Arrow gets stuck in a block, an arrow hits an entity.
     * An active Tripwire (connected to Tripwire Hooks) is broken with Shears.
     * An active Tripwire Hook is broken
     */
    RANDOM_BOW_HIT("random.bowhit"),

    /**
     * A player breaks a Pickaxe, Sword, Axe, Shovel, Bow, Flint and Steel, Shears, or Fishing Rod.
     * A piece of armor a mob (excluding players) is wearing breaks
     */
    RANDOM_BREAK("random.break"),

    /**
     * A player consumes a piece of food
     */
    RANDOM_BURP("random.burp"),

    /**
     * A Chest is closed
     */
    RANDOM_CHEST_CLOSE("random.chestclosed"),

    /**
     * A Chest is opened
     */
    RANDOM_CHEST_OPEN("random.chestopen"),

    /**
     * Any kind of switch changes its power state (on or off)
     * A Dispenser or Dropper is powered
     * A Redstone Comparator's mode is changed
     * Two Tripwire Hooks are connected with String
     */
    RANDOM_CLICK("random.click"),

    /**
     * A Door, Trapdoor, or Fence Gate is opened or closed
     * A player attempts to open a locked container and is not holding the correct key item
     */
    RANDOM_DOOR_OPEN("random.door_open"),

    /**
     * A Door, Trapdoor, or Fence Gate is opened or closed
     * A player attempts to open a locked container and is not holding the correct key item
     */
    RANDOM_DOOR_CLOSE("random.door_close"),

    /**
     * A player is drinking a Potion or Milk
     */
    RANDOM_DRINK("random.drink"),

    /**
     * A player is eating food
     */
    RANDOM_EAT("random.eat"),

    /**
     * TNT, a Creeper, a Ghast's fireball, a Wither Skull, an Ender Crystal, or a Bed (in the Nether or the End) explodes
     * Lightning strikes at close range
     */
    RANDOM_EXPLODE("random.explode"),

    /**
     * Fire is extinguished
     * An Item or Experience Orb is destroyed by Lava
     * Lava touches Water
     * Lava destroys a non-solid block in its path
     * A Redstone Torch burns out
     */
    RANDOM_FIZZ("random.fizz"),

    /**
     * A player reaches an experience level that is a multiple of 5
     */
    RANDOM_LEVEL_UP("random.levelup"),

    /**
     * A player gains experience points
     */
    RANDOM_ORB("random.orb"),

    /**
     * A player picks up an Item
     */
    RANDOM_POP("random.pop"),

    /**
     * A fish is caught on a fishing rod bobber
     */
    RANDOM_SPLASH("random.splash"),

    /**
     * A player shoots an armored player with an Arrow
     */
    RANDOM_SUCCESSFUL_HIT("random.successful_hit"),

    /**
     * ...?
     */
    RANDOM_WOOD_CLICK("random.wood_click"),

    /**
     * A button in the menu or a GUI is clicked
     */
    GUI_BUTTON_PRESS("gui.button.press"),

    /**
     * TNT is ignited
     */
    TNT_PRIME("game.tnt.primed"),

    /**
     * An entity walks on a block classified as "cloth"
     * A player is breaking a block classified as "cloth" (during the cracking animation)
     */
    STEP_CLOTH("step.cloth"),

    /**
     * An entity walks on a block classified as "grass"\
     * A player is breaking a block classified as "grass" (during the cracking animation)
     */
    STEP_GRASS("step.grass"),

    /**
     * An entity walks on a block classified as "gravel"
     * Dirt/Grass/Mycelium is tilled with a Hoe (creating Farmland)
     * A player is breaking a block classified as "gravel" (during the cracking animation)
     */
    STEP_GRAVEL("step.gravel"),

    /**
     * An entity climbs or descends a Ladder
     * A player is breaking a Ladder (during the cracking animation)
     */
    STEP_LADDER("step.ladder"),

    /**
     * An entity walks on a block classified as "sand"
     * A player is breaking a block classified as "sand" (during the cracking animation)
     */
    STEP_SAND("step.sand"),

    /**
     * An entity walks on a block classified as "snow"
     * A player is breaking a block classified as "snow" (during the cracking animation)
     */
    STEP_SNOW("step.snow"),

    /**
     * An entity walks on a block classified as "stone" or "glass"
     * A player is breaking a block classified as "stone" or "glass" (during the cracking animation)
     * A player places a block classified as "glass"
     */
    STEP_STONE("step.stone"),

    /**
     * An entity walks on a block classified as "wood"
     * A player is breaking a block classified as "wood" (during the cracking animation)
     */
    STEP_WOOD("step.wood"),

    /**
     * A Piston retracts
     */
    PISTON_IN("tile.piston.in"),

    /**
     * A Piston extends
     */
    PISTON_OUT("tile.piston.out"),

    /**
     * A Bat dies
     */
    BAT_DEATH("mob.bat.death"),

    /**
     * A Bat taking damage
     */
    BAT_HURT("mob.bat.hurt"),

    /**
     * Randomly when a Bat is within 16 blocks
     */
    BAT_IDLE("mob.bat.idle"),

    /**
     * ...?
     */
    BAT_LOOP("mob.bat.loop"),

    /**
     * A Bat takes off/begins to fly
     */
    BAT_TAKEOFF("mob.bat.takeoff"),

    /**
     * Randomly when a Blaze is within 16 blocks
     */
    BLAZE_BREATHE("mob.blaze.breathe"),

    /**
     * A Blaze dies
     */
    BLAZE_DEATH("mob.blaze.death"),

    /**
     * A Blaze takes damage
     */
    BLAZE_HIT("mob.blaze.hit"),

    /**
     * ...?
     */
    CAT_HISS("mob.cat.hiss"),

    /**
     * A Cat or Ocelot takes damage or dies
     */
    CAT_HITT("mob.cat.hitt"),

    /**
     * Randomly when a Tamed Cat is within 16 blocks
     */
    CAT_MEOW("mob.cat.meow"),

    /**
     * When Tamed Cats mate after being fed Raw Fish
     */
    CAT_PURR("mob.cat.purr"),

    /**
     * Randomly when a Tamed Cat is within 16 blocks
     */
    CAT_PURREOW("mob.cat.purreow"),

    /**
     * A Chicken takes damage or dies
     */
    CHICKEN_HURT("mob.chicken.hurt"),

    /**
     * A Chicken lays an Egg
     */
    CHICKEN_PLOP("mob.chicken.plop"),

    /**
     * Randomly when a Chicken is within 16 blocks
     */
    CHICKEN_SAY("mob.chicken.say"),

    /**
     * A Chicken is walking
     */
    CHICKEN_STEP("mob.chicken.step"),

    /**
     * A Cow or Mooshroom takes damage or dies
     */
    COW_HURT("mob.cow.hurt"),

    /**
     * Randomly when a Cow or Mooshroom is within 16 blocks
     */
    COW_SAY("mob.cow.say"),

    /**
     * A Cow or Mooshroom is walking
     */
    COW_STEP("mob.cow.step"),

    /**
     * A Creeper just about to explode
     */
    CREEPER_PRIME("creeper.primed"),

    /**
     * A Creeper dies
     */
    CREEPER_DEATH("mob.creeper.death"),

    /**
     * A Creeper takes damage
     */
    CREEPER_SAY("mob.creeper.say"),

    /**
     * An Ender Dragon dies
     */
    ENDERDRAGON_END("mob.enderdragon.end"),

    /**
     * Randomly when an Ender Dragon is within 50 blocks of the Player
     */
    ENDERDRAGON_GROWL("mob.enderdragon.growl"),

    /**
     * An Ender Dragon takes damage
     */
    ENDERDRAGON_HIT("mob.enderdragon.hit"),

    /**
     * An Ender Dragon flaps its wings
     */
    ENDERDRAGON_WINGS("mob.enderdragon.wings"),

    /**
     * An Enderman dies
     */
    ENDERMEN_DEATH("mob.endermen.death"),

    /**
     * An Enderman takes damage
     */
    ENDERMEN_HIT("mob.endermen.hit"),

    /**
     * Randomly when an Enderman is within 16 blocks and not angry
     */
    ENDERMEN_IDLE("mob.endermen.idle"),

    /**
     * An Enderman teleports
     */
    ENDERMEN_PORTAL("mob.endermen.portal"),

    /**
     * Randomly when an Endermen is within 16 blocks and angry
     */
    ENDERMEN_SCREAM("mob.endermen.scream"),

    /**
     * A player looks at an Enderman, angering it
     */
    ENDERMEN_STARE("mob.endermen.stare"),

    /**
     * ...?
     */
    GHAST_AFFECTIONATE_SCREAM("mob.ghast.affectionate_scream"),

    /**
     * Right before a Ghast shoots a fireball
     */
    GHAST_CHARGE("mob.ghast.charge"),

    /**
     * A Ghast dies
     */
    GHAST_DEATH("mob.ghast.death"),

    /**
     * A Ghast, Blaze, or Dispenser shoots a fireball
     */
    GHAST_FIREBALL("mob.ghast.fireball"),

    /**
     * Randomly when a Ghast is within 100 blocks
     */
    GHAST_MOAN("mob.ghast.moan"),

    /**
     * A Ghast takes damage
     */
    GHAST_SCREAM("mob.ghast.scream"),

    /**
     * A regular Guardian takes damage while in water
     */
    GUARDIAN_HIT("mob.guardian.hit"),

    /**
     * Randomly when a regular Guardian is within 16 blocks and is in water
     */
    GUARDIAN_IDLE("mob.guardian.idle"),

    /**
     * A regular Guardian dies while in water
     */
    GUARDIAN_DEATH("mob.guardian.death"),

    /**
     * An Elder Guardian takes damage while in water
     */
    GUARDIAN_ELDER_HIT("mob.guardian.elder.hit"),

    /**
     * Randomly when an Elder Guardian is within 16 blocks and is in water
     */
    GUARDIAN_ELDER_IDLE("mob.guardian.elder.idle"),

    /**
     * An Elder Guardian dies while in water
     */
    GUARDIAN_ELDER_DEATH("mob.guardian.elder.death"),

    /**
     * A Guardian takes damage while not in water
     */
    GUARDIAN_LAND_HIT("mob.guardian.land.hit"),

    /**
     * Randomly when a Guardian is within 16 blocks and not in water
     */
    GUARDIAN_LAND_IDLE("mob.guardian.land.idle"),

    /**
     * A Guardian dies while not in water
     */
    GUARDIAN_LAND_DEATH("mob.guardian.land.death"),

    /**
     * A player is within 50 blocks of an Elder Guardian and does not have the Mining Fatigue effect,
     * has Mining Fatigue II or lower, or has less than a minute left of Mining Fatigue III.
     *
     * Accompanied by a ghostly image of an Elder Guardian and Mining Fatigue III for five minutes
     */
    GUARDIAN_CURSE("mob.guardian.curse"),

    /**
     * A Guardian's attack beam is aimed at a player or a squid. Can be heard up to 50 blocks away
     */
    GUARDIAN_ATTACK("mob.guardian.attack"),

    /**
     * A Guardian is hopping around out of water
     */
    GUARDIAN_FLOP("mob.guardian.flop"),

    /**
     * A player is thrown off of an untamed Horse
     */
    HORSE_ANGRY("mob.horse.angry"),

    /**
     * A player puts Horse Armor onto a Horse
     */
    HORSE_ARMOR("mob.horse.armor"),

    /**
     * Randomly when a tamed Horse is galloping
     */
    HORSE_BREATHE("mob.horse.breathe"),

    /**
     * A Horse dies
     */
    HORSE_DEATH("mob.horse.death"),

    /**
     * A player is thrown off an untamed Donkey or Mule
     */
    HORSE_DONKEY_ANGRY("mob.horse.donkey.angry"),

    /**
     * A Donkey or Mule dies
     */
    HORSE_DONKEY_DEATH("mob.horse.donkey.death"),

    /**
     * A Donkey or Mule takes damage
     */
    HORSE_DONKEY_HIT("mob.horse.donkey.hit"),

    /**
     * Randomly when a Donkey or Mule is within 16 blocks
     */
    HORSE_DONKEY_IDLE("mob.horse.donkey.idle"),

    /**
     * A ridden Horse is galloping
     */
    HORSE_GALLOP("mob.horse.gallop"),

    /**
     * A Horse takes damage
     */
    HORSE_HIT("mob.horse.hit"),

    /**
     * Randomly when a Horse is within 16 blocks
     */
    HORSE_IDLE("mob.horse.idle"),

    /**
     * A Horse jumps while galloping
     */
    HORSE_JUMP("mob.horse.jmp"),

    /**
     * A Horse lands after jumping
     */
    HORSE_LAND("mob.horse.land"),

    /**
     * A player puts a Saddle on a Horse or Pig
     */
    HORSE_LEATHER("mob.horse.leather"),

    /**
     * A Skeleton Horse dies
     */
    HORSE_SKELETON_DEATH("mob.horse.skeleton.death"),

    /**
     * A Skeleton Horse takes damage
     */
    HORSE_SKELETON_HIT("mob.horse.skeleton.hit"),

    /**
     * Randomly when a Skeleton Horse is within 16 blocks
     */
    HORSE_SKELETON_IDLE("mob.horse.skeleton.idle"),

    /**
     * A Horse (not being ridden) is walking
     */
    HORSE_SOFT("mob.horse.soft"),

    /**
     * A ridden Horse is walking
     */
    HORSE_WOOD("mob.horse.wood"),

    /**
     * A Zombie Horse dies
     */
    HORSE_ZOMBIE_DEATH("mob.horse.zombie.death"),

    /**
     * A Zombie Horse takes damage
     */
    HORSE_ZOMBIE_HIT("mob.horse.zombie.hit"),

    /**
     * Randomly when a Zombie Horse is within 16 blocks
     */
    HORSE_ZOMBIE_IDLE("mob.horse.zombie.idle"),

    /**
     * An Iron Golem dies
     */
    IRON_GOLEM_DEATH("mob.irongolem.death"),

    /**
     * An Iron Golem takes damage
     */
    IRON_GOLEM_HIT("mob.irongolem.hit"),

    /**
     * An Iron Golem attacks a mob
     */
    IRON_GOLEM_THROW("mob.irongolem.throw"),

    /**
     * An Iron Golem is walking
     */
    IRON_GOLEM_WALK("mob.irongolem.walk"),

    /**
     * A big or small Magma Cube jumps, takes damage, or dies
     */
    MAGMA_CUBE_BIG("mob.magmacube.big"),

    /**
     * ...?
     */
    MAGMA_CUBE_JUMP("mob.magmacube.jump"),

    /**
     * A tiny Magma Cube jumps, takes damage, or dies
     */
    MAGMA_CUBE_SMALL("mob.magmacube.small"),

    /**
     * A Pig dies
     */
    PIG_DEATH("mob.pig.death"),

    /**
     * Randomly when a Pig is within 16 blocks or when a Pig takes damage
     */
    PIG_SAY("mob.pig.say"),

    /**
     * A Pig is walking
     */
    PIG_STEP("mob.pig.step"),

    /**
     * A Rabbit takes damage
     */
    RABBIT_HURT("mob.rabbit.hurt"),

    /**
     * Randomly when a Rabbit is within 16 blocks
     */
    RABBIT_IDLE("mob.rabbit.idle"),

    /**
     * A Rabbit hops
     */
    RABBIT_HOP("mob.rabbit.hop"),

    /**
     * A Rabbit dies
     */
    RABBIT_DEATH("mob.rabbit.death"),

    /**
     * Randomly when a Sheep is within 16 blocks or when a Sheep takes damage or dies
     */
    SHEEP_SAY("mob.sheep.say"),

    /**
     * A Sheep or Mooshroom is sheared
     */
    SHEEP_SHEAR("mob.sheep.shear"),

    /**
     * A Sheep is walking
     */
    SHEEP_STEP("mob.sheep.step"),

    /**
     * A Silverfish takes damage
     */
    SILVERFISH_HIT("mob.silverfish.hit"),

    /**
     * A Silverfish dies
     */
    SILVERFISH_KILL("mob.silverfish.kill"),

    /**
     * Randomly when a Silverfish is within 16 blocks
     */
    SILVERFISH_SAY("mob.silverfish.say"),

    /**
     * ...?
     */
    SILVERFISH_STEP("mob.silverfish.step"),

    /**
     * A Skeleton or a Wither Skeleton dies
     */
    SKELETON_DEATH("mob.skeleton.death"),

    /**
     * A Skeleton or a Wither Skeleton takes damage
     */
    SKELETON_HURT("mob.skeleton.hurt"),

    /**
     * Randomly when a Skeleton or a Wither Skeleton is within 16 blocks
     */
    SKELETON_SAY("mob.skeleton.say"),

    /**
     * A Skeleton or a Wither Skeleton is walking
     */
    SKELETON_STEP("mob.skeleton.step"),

    /**
     * ...?
     */
    SLIME_ATTACK("mob.slime.attack"),

    /**
     * A big or small Slime jumps, takes damage, or dies; or a Slime Block is placed
     */
    SLIME_BIG("mob.slime.big"),

    /**
     * A tiny Slime jumps, takes damage, or dies; or a mob walks on a Slime Block
     */
    SLIME_SMALL("mob.slime.small"),

    /**
     * A Spider dies
     */
    SPIDER_DEATH("mob.spider.death"),

    /**
     * Randomly when a Spider is within 16 blocks or when a Spider takes damage
     */
    SPIDER_SAY("mob.spider.say"),

    /**
     * A Spider is walking
     */
    SPIDER_STEP("mob.spider.step"),

    /**
     * A Villager dies
     */
    VILLAGER_DEATH("mob.villager.death"),

    /**
     * A player right-clicks on a Villager, opening the trading GUI
     */
    VILLAGER_HAGGLE("mob.villager.haggle"),

    /**
     * A Villager takes damage
     */
    VILLAGER_HIT("mob.villager.hit"),

    /**
     * Randomly when a Villager is within 16 blocks
     */
    VILLAGER_IDLE("mob.villager.idle"),

    /**
     * A player exits a trading option
     */
    VILLAGER_NO("mob.villager.no"),

    /**
     * A player trades with a Villager (removes an item from the right slot in the trading GUI)
     */
    VILLAGER_YES("mob.villager.yes"),

    /**
     * A Wither dies
     */
    WITHER_DEATH("mob.wither.death"),

    /**
     * A Wither takes damage
     */
    WITHER_HURT("mob.wither.hurt"),

    /**
     * Randomly when a Wither is within 16 blocks
     */
    WITHER_IDLE("mob.wither.idle"),

    /**
     * A Wither shoots a Wither Skull
     */
    WITHER_SHOOT("mob.wither.shoot"),

    /**
     * A Wither is spawned
     */
    WITHER_SPAWN("mob.wither.spawn"),

    /**
     * Randomly when a Wolf is within 16 blocks and is not angry
     */
    WOLF_BARK("mob.wolf.bark"),

    /**
     * A Wolf dies
     */
    WOLF_DEATH("mob.wolf.death"),

    /**
     * Randomly when an angry Wolf is within 16 blocks
     */
    WOLF_GROWL("mob.wolf.growl"),

    /**
     * ...?
     */
    WOLF_HOWL("mob.wolf.howl"),

    /**
     * A Wolf takes damage
     */
    WOLF_HURT("mob.wolf.hurt"),

    /**
     * Randomly when a Wolf is within 16 blocks and not angry
     */
    WOLF_PANTING("mob.wolf.panting"),

    /**
     * A Wolf shakes itself dry after exiting water
     */
    WOLF_SHAKE("mob.wolf.shake"),

    /**
     * A Wolf is walking
     */
    WOLF_STEP("mob.wolf.step"),

    /**
     * Randomly when a tamed Wolf has low health
     */
    WOLF_WHINE("mob.wolf.whine"),

    /**
     * A Zombie dies
     */
    ZOMBIE_DEATH("mob.zombie.death"),

    /**
     * A Zombie takes damage
     */
    ZOMBIE_HURT("mob.zombie.hurt"),

    /**
     * A villager is turned into a Zombie Villager
     */
    ZOMBIE_INFECT("mob.zombie.infect"),

    /**
     * ...?
     */
    ZOMBIE_METAL("mob.zombie.metal"),

    /**
     * A Zombie Villager is fed a Golden Apple while having the Weakness effect
     */
    ZOMBIE_REMEDY("mob.zombie.remedy"),

    /**
     * Randomly when a Zombie is within 16 blocks
     */
    ZOMBIE_SAY("mob.zombie.say"),

    /**
     * A Zombie or Zombie Pigman is walking
     */
    ZOMBIE_STEP("mob.zombie.step"),

    /**
     * A Zombie Villager is turned into a Villager
     */
    ZOMBIE_UNFECT("mob.zombie.unfect"),

    /**
     * A Zombie pounds on a Wooden Door
     */
    ZOMBIE_WOOD("mob.zombie.wood"),

    /**
     * A Zombie breaks a Wooden Door or a Wither breaks blocks around it
     */
    ZOMBIE_WOOD_BREAK("mob.zombie.woodbreak"),

    /**
     * Randomly when a Zombie Pigman is within 16 blocks
     */
    ZOMBIE_PIGMAN_SAY("mob.zombiepig.zpig"),

    /**
     * When Zombie Pigmen become hostile to a Player
     */
    ZOMBIE_PIGMAN_ANGRY("mob.zombiepig.zpigangry"),

    /**
     * A Zombie Pigman dies
     */
    ZOMBIE_PIGMAN_DEATH("mob.zombiepig.zpigdeath"),

    /**
     * A Zombie Pigman takes damage
     */
    ZOMBIE_PIGMAN_HURT("mob.zombiepig.zpighurt"),

    /**
     * A player inserts a "11" Music Disc into a Jukebox
     */
    RECORD_11("records.11"),

    /**
     * A player inserts a "13" Music Disc into a Jukebox
     */
    RECORD_13("records.13"),

    /**
     * A player inserts a "blocks" Music Disc into a Jukebox
     */
    RECORD_BLOCKS("records.blocks"),

    /**
     * A player inserts a "cat" Music Disc into a Jukebox
     */
    RECORD_CAT("records.cat"),

    /**
     * A player inserts a "chirp" Music Disc into a Jukebox
     */
    RECORD_CHIRP("records.chirp"),

    /**
     * A player inserts a "far" Music Disc into a Jukebox
     */
    RECORD_FAR("records.far"),

    /**
     * A player inserts a "mall" Music Disc into a Jukebox
     */
    RECORD_MALL("records.mall"),

    /**
     * A player inserts a "mellohi" Music Disc into a Jukebox
     */
    RECORD_MELLOHI("records.mellohi"),

    /**
     * A player inserts a "stal" Music Disc into a Jukebox
     */
    RECORD_STAL("records.stal"),

    /**
     * A player inserts a "strad" Music Disc into a Jukebox
     */
    RECORD_STRAD("records.strad"),

    /**
     * A player inserts a "wait" Music Disc into a Jukebox
     */
    RECORD_WAIT("records.wait"),

    /**
     * A player inserts a "ward" Music Disc into a Jukebox
     */
    RECORD_WARD("records.ward"),

    /**
     * Every 5 minutes when a menu screen is open (not playing the game)
     */
    MUSIC_MENU("music.menu"),

    /**
     * During sunrise, noon, sunset, and midnight.
     *
     * If the day cycle is turned off, the music plays every 5 minutes
     */
    MUSIC_GAME("music.game"),

    /**
     * During sunrise, noon, sunset, and midnight (player must be in Creative mode).
     *
     * If the day cycle is turned off, the music plays every 5 minutes
     */
    MUSIC_GAME_CREATIVE("music.game.creative"),

    /**
     * Being in the End
     */
    MUSIC_GAME_END("music.game.end"),

    /**
     * Being near the Ender Dragon
     */
    MUSIC_GAME_END_DRAGON("music.game.end.dragon"),

    /**
     * Entering the exit portal in the End
     */
    MUSIC_GAME_END_CREDITS("music.game. end.credits"),

    /**
     * Being in the Nether
     */
    MUSIC_GAME_NETHER("music.game.nether");

    private final String s;

    SoundEffectType(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }

}
