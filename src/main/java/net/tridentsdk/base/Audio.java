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

package net.tridentsdk.base;

/**
 * Represents sound effects played to the player
 *
 * @author The TridentSDK Team TODO more darude sandstorm's
 */
public enum Audio {

    AMBIENT_CAVE("ambient.cave.cave"),
    AMBIENT_RAIN("ambient.weather.rain"),
    AMBIENT_THUNDER("ambient.weather.thunder"),

    PLAYER_FALL_BIG("game.player.hurt.fall.big"),
    NEUTRAL_FALL_BIG("game.neutral.hurt.fall.big"),
    HOSTILE_FALL_BIG("game.hostile.hurt.fall.big"),

    PLAYER_FALL_SMALL("game.player.hurt.fall.small"),
    NEUTRAL_FALL_SMALL("game.neutral.hurt.fall.small"),
    HOSTILE_FALL_SMALL("game.hostile.hurt.fall.small"),

    PLAYER_HURT("game.player.hurt"),
    NEUTRAL_HURT("game.neutral.hurt"),
    HOSTILE_HURT("game.hostile.hurt"),

    PLAYER_DIE("game.player.die"),
    NEUTRAL_DIE("game.neutral.die"),
    HOSTILE_DIE("game.hostile.die"),

    DIG_CLOTH("dig.cloth"),
    DIG_GLASS("dig.glass"),
    DIG_GRASS("dig.grass"),
    DIG_GRAVEL("dig.gravel"),
    DIG_SAND("dig.sand"),
    DIG_SNOW("dig.snow"),
    DIG_STONE("dig.stone"),

    POTION_SMASH("game.potion.smash"),

    FIRE_ACTIVE("fire.fire"),
    FIRE_IGNITE("fire.ignite"),

    FIRECHARGE_USE("item.fireCharge.use"),

    FIREWORKS_BLAST("fireworks.blast"),
    FIREWORKS_BLAST_FAR("fireworks.blast_far"),
    FIREWORKS_LARGE_BLAST("fireworks.largeBlast"),
    FIREWORKS_LARGE_BLAST_FAR("fireworks.largeBlast_far"),
    FIREWORKS_LAUNCH("fireworks.launch"),
    FIREWORKS_TWINKLE("fireworks.twinkle"),
    FIREWORKS_TWINKLE_FAR("fireworks.twinkle_far"),

    PLAYER_SWIM("game.player.swim"),
    NEUTRAL_SWIM("game.neutral.swim"),
    HOSTILE_SWIM("game.hostile.swim"),

    PLAYER_SWIM_SPLASH("game.player.swim.splash"),
    NEUTRAL_SWIM_SPLASH("game.neutral.swim.splash"),
    HOSTILE_SWIM_SPLASH("game.hostile.swim.splash"),

    LAVA("liquid.lava"),
    LAVAPOP("liquid.lavapop"),
    WATER("liquid.water"),

    MINECART_BASE("minecart.base"),
    MINECART_INSIDE("minecart.inside"),

    NOTE_BASS("note.bass"),
    NOTE_BASSATTACK("note.bassattack"),
    NOTE_BD("note.bd"),
    NOTE_HARP("note.harp"),
    NOTE_HAT("note.hat"),
    NOTE_PLING("note.pling"),
    NOTE_SNARE("note.snare"),

    PORTAL("portal.portal"),
    PORTAL_TRAVEL("portal.travel"),
    PORTAL_TRIGGER("portal.trigger"),

    RANDOM_ANVIL_BREAK("random.anvil_break"),
    RANDOM_ANVIL_LAND("random.anvil_land"),
    RANDOM_ANVIL_USE("random.anvil_use"),
    RANDOM_BOW("random.bow"),
    RANDOM_BOW_HIT("random.bowhit"),
    RANDOM_BREAK("random.break"),
    RANDOM_BURP("random.burp"),
    RANDOM_CHEST_CLOSE("random.chestclosed"),
    RANDOM_CHEST_OPEN("random.chestopen"),
    RANDOM_CLICK("random.click"),
    RANDOM_DOOR_OPEN("random.door_open"),
    RANDOM_DOOR_CLOSE("random.door_close"),
    RANDOM_DRINK("random.drink"),
    RANDOM_EAT("random.eat"),
    RANDOM_EXPLODE("random.explode"),
    RANDOM_FIZZ("random.fizz"),
    RANDOM_LEVEL_UP("random.levelup"),
    RANDOM_ORB("random.orb"),
    RANDOM_POP("random.pop"),
    RANDOM_SPLASH("random.splash"),
    RANDOM_SUCCESSFUL_HIT("random.successful_hit"),
    RANDOM_WOOD_CLICK("random.wood_click"),

    GUI_BUTTON_PRESS("gui.button.press"),

    TNT_PRIME("game.tnt.primed"),

    STEP_CLOTH("step.cloth"),
    STEP_GRASS("step.grass"),
    STEP_GRAVEL("step.gravel"),
    STEP_LADDER("step.ladder"),
    STEP_SAND("step.sand"),
    STEP_SNOW("step.snow"),
    STEP_STONE("step.stone"),
    STEP_WOOD("step.wood"),

    PISTON_IN("tile.piston.in"),
    PISTON_OUT("tile.piston.out"),

    BAT_DEATH("mob.bat.death"),
    BAT_HURT("mob.bat.hurt"),
    BAT_IDLE("mob.bat.idle"),
    BAT_LOOP("mob.bat.loop"),
    BAT_TAKEOFF("mob.bat.takeoff"),

    BLAZE_BREATHE("mob.blaze.breathe"),
    BLAZE_DEATH("mob.blaze.death"),
    BLAZE_HIT("mob.blaze.hit"),

    CAT_HISS("mob.cat.hiss"),
    CAT_HITT("mob.cat.hitt"),
    CAT_MEOW("mob.cat.meow"),
    CAT_PURR("mob.cat.purr"),
    CAT_PURREOW("mob.cat.purreow"),

    CHICKEN_HURT("mob.chicken.HURT"),
    CHICKEN_PLOP("mob.chicken.PLOP"),
    CHICKEN_SAY("mob.chicken.SAY"),
    CHICKEN_STEP("mob.chicken.STEP"),

    COW_HURT("mob.cow.hurt"),
    COW_SAY("mob.cow.say"),
    COW_STEP("mob.cow.step"),

    CREEPER_PRIME("creeper.primed"),
    CREEPER_DEATH("mob.creeper.death"),
    CREEPER_SAY("mob.creeper.say"),

    ENDERDRAGON_END("mob.enderdragon.end"),
    ENDERDRAGON_GROWL("mob.enderdragon.growl"),
    ENDERDRAGON_HIT("mob.enderdragon.hit"),
    ENDERDRAGON_WINGS("mob.enderdragon.wings"),

    ENDERMEN_DEATH("mob.endermen.death"),
    ENDERMEN_HIT("mob.endermen.hit"),
    ENDERMEN_IDLE("mob.endermen.idle"),
    ENDERMEN_PORTAL("mob.endermen.portal"),
    ENDERMEN_SCREAM("mob.endermen.scream"),
    ENDERMEN_STARE("mob.endermen.stare"),

    GHAST_AFFECTIONATE_SCREAM("mob.ghast.affectionate_scream"),
    GHAST_CHARGE("mob.ghast.charge"),
    GHAST_DEATH("mob.ghast.death"),
    GHAST_FIREBALL("mob.ghast.fireball"),
    GHAST_MOAN("mob.ghast.moan"),
    GHAST_SCREAM("mob.ghast.scream"),

    GUARDIAN_HIT("mob.guardian.hit"),
    GUARDIAN_IDLE("mob.guardian.idle"),
    GUARDIAN_DEATH("mob.guardian.death"),
    GUARDIAN_ELDER_HIT("mob.guardian.elder.hit"),
    GUARDIAN_ELDER_IDLE("mob.guardian.elder.idle"),
    GUARDIAN_ELDER_DEATH("mob.guardian.elder.death"),
    GUARDIAN_LAND_HIT("mob.guardian.land.hit"),
    GUARDIAN_LAND_IDLE("mob.guardian.land.idle"),
    GUARDIAN_LAND_DEATH("mob.guardian.land.death"),
    GUARDIAN_CURSE("mob.guardian.curse"),
    GUARDIAN_ATTACK("mob.guardian.attack"),
    GUARDIAN_FLOP("mob.guardian.flop"),

    HORSE_ANGRY("mob.horse.angry"),
    HORSE_ARMOR("mob.horse.armor"),
    HORSE_BREATHE("mob.horse.breathe"),
    HORSE_DEATH("mob.horse.death"),
    HORSE_DONKEY_ANGRY("mob.horse.donkey.angry"),
    HORSE_DONKEY_DEATH("mob.horse.donkey.death"),
    HORSE_DONKEY_HIT("mob.horse.donkey.hit"),
    HORSE_DONKEY_IDLE("mob.horse.donkey.idle"),
    HORSE_GALLOP("mob.horse.gallop"),
    HORSE_HIT("mob.horse.hit"),
    HORSE_IDLE("mob.horse.idle"),
    HORSE_JUMP("mob.horse.jmp"),
    HORSE_LAND("mob.horse.land"),
    HORSE_LEATHER("mob.horse.leather"),
    HORSE_SKELETON_DEATH("mob.horse.skeleton.death"),
    HORSE_SKELETON_HIT("mob.horse.skeleton.hit"),
    HORSE_SKELETON_IDLE("mob.horse.skeleton.idle"),
    HORSE_SOFT("mob.horse.soft"),
    HORSE_WOOD("mob.horse.wood"),
    HORSE_ZOMBIE_DEATH("mob.horse.zombie.death"),
    HORSE_ZOMBIE_HIT("mob.horse.zombie.hit"),
    HORSE_ZOMBIE_IDLE("mob.horse.zombie.idle"),

    IRON_GOLEM_DEATH("mob.irongolem.death"),
    IRON_GOLEM_HIT("mob.irongolem.hit"),
    IRON_GOLEM_THROW("mob.irongolem.throw"),
    IRON_GOLEM_WALK("mob.irongolem.walk"),

    MAGMA_CUBE_BIG("mob.magmacube.big"),
    MAGMA_CUBE_JUMP("mob.magmacube.jump"),
    MAGMA_CUBE_SMALL("mob.magmacube.small"),

    PIG_DEATH("mob.pig.death"),
    PIG_SAY("mob.pig.say"),
    PIG_STEP("mob.pig.step"),

    RABBIT_HURT("mob.rabbit.hurt"),
    RABBIT_IDLE("mob.rabbit.idle"),
    RABBIT_HOP("mob.rabbit.hop"),
    RABBIT_DEATH("mob.rabbit.death"),

    SHEEP_SAY("mob.sheep.say"),
    SHEEP_SHEAR("mob.sheep.shear"),
    SHEEP_STEP("mob.sheep.step"),

    SILVERFISH_HIT("mob.silverfish.hit"),
    SILVERFISH_KILL("mob.silverfish.kill"),
    SILVERFISH_SAY("mob.silverfish.say"),
    SILVERFISH_STEP("mob.silverfish.step"),

    SKELETON_DEATH("mob.skeleton.death"),
    SKELETON_HURT("mob.skeleton.hurt"),
    SKELETON_SAY("mob.skeleton.say"),
    SKELETON_STEP("mob.skeleton.step"),

    SLIME_ATTACK("mob.slime.attack"),
    SLIME_BIG("mob.slime.big"),
    SLIME_SMALL("mob.slime.small"),

    SPIDER_DEATH("mob.spider.death"),
    SPIDER_SAY("mob.spider.say"),
    SPIDER_STEP("mob.spider.step"),

    VILLAGER_DEATH("mob.villager.death"),
    VILLAGER_HAGGLE("mob.villager.haggle"),
    VILLAGER_HIT("mob.villager.hit"),
    VILLAGER_IDLE("mob.villager.idle"),
    VILLAGER_NO("mob.villager.no"),
    VILLAGER_YES("mob.villager.yes"),

    WITHER_DEATH("mob.wither.death"),
    WITHER_HURT("mob.wither.hurt"),
    WITHER_IDLE("mob.wither.idle"),
    WITHER_SHOOT("mob.wither.shoot"),
    WITHER_SPAWN("mob.wither.spawn"),

    WOLF_BARK("mob.wolf.bark"),
    WOLF_DEATH("mob.wolf.death"),
    WOLF_GROWL("mob.wolf.growl"),
    WOLF_HOWL("mob.wolf.howl"),
    WOLF_HURT("mob.wolf.hurt"),
    WOLF_PANTING("mob.wolf.panting"),
    WOLF_SHAKE("mob.wolf.shake"),
    WOLF_STEP("mob.wolf.step"),
    WOLF_WHINE("mob.wolf.whine"),

    ZOMBIE_DEATH("mob.zombie.death"),
    ZOMBIE_HURT("mob.zombie.hurt"),
    ZOMBIE_INFECT("mob.zombie.infect"),
    ZOMBIE_METAL("mob.zombie.metal"),
    ZOMBIE_REMEDY("mob.zombie.remedy"),
    ZOMBIE_SAY("mob.zombie.say"),
    ZOMBIE_STEP("mob.zombie.step"),
    ZOMBIE_UNFECT("mob.zombie.unfect"),
    ZOMBIE_WOOD("mob.zombie.wood"),
    ZOMBIE_WOOD_BREAK("mob.zombie.woodbreak"),

    ZOMBIE_PIGMAN_SAY("mob.zombiepig.zpig"),
    ZOMBIE_PIGMAN_ANGRY("mob.zombiepig.zpigangry"),
    ZOMBIE_PIGMAN_DEATH("mob.zombiepig.zpigdeath"),
    ZOMBIE_PIGMAN_HURT("mob.zombiepig.zpighurt"),

    RECORD_11("records.11"),
    RECORD_13("records.13"),
    RECORD_BLOCKS("records.blocks"),
    RECORD_CAT("records.cat"),
    RECORD_CHIRP("records.chirp"),
    RECORD_FAR("records.far"),
    RECORD_MALL("records.mall"),
    RECORD_MELLOHI("records.mellohi"),
    RECORD_STAL("records.stal"),
    RECORD_STRAD("records.strad"),
    RECORD_WAIT("records.wait"),
    RECORD_WARD("records.ward"),

    MUSIC_MENU("music.menu"),
    MUSIC_GAME("music.game"),
    MUSIC_GAME_CREATIVE("music.game.creative"),
    MUSIC_GAME_END("music.game.end"),
    MUSIC_GAME_END_DRAGON("music.game.end.dragon"),
    MUSIC_GAME_END_CREDITS("music.game. end.credits"),
    MUSIC_GAME_NETHER("music.game.nether");

    private final String s;

    Audio(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }
}
