package net.tridentsdk.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add to a player parameter to only match the player with the exact name!
 * In variable length parameters, it will match each argument as a new player, and only return the matched players.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PlayerExactMatch {}
