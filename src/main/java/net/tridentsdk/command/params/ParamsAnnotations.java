package net.tridentsdk.command.params;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.tridentsdk.command.CommandSourceType;

/**
 * @author Nick Robson
 */
public interface ParamsAnnotations {

    /**
     * Add to a method so only certain types of {@link net.tridentsdk.command.CommandSource CommandSources} may use the command.
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface AllowedSourceTypes {

        CommandSourceType[] value();

    }

    /**
     * Add to a method so only {@link net.tridentsdk.command.CommandSource CommandSources} with one of the given permissions
     * may use the command.
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface PermissionRequired {

        String[] value();

    }

    /**
     * Add to a variable/array parameter to restrict the minimum number of values it can have.
     */
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @interface MinCount {

        int value();

    }

    /**
     * Add to a variable/array parameter to restrict the maximum number of values it can have.
     */
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @interface MaxCount {

        int value();

    }

    /**
     * Add to a player parameter to only match the player with the exact name!
     * In variable length parameters, it will match each argument as a new player, and only return the matched players.
     */
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @interface PlayerExactMatch {}

    /**
     * Add to a player parameter to only match the player with names that fuzzy match the given string.
     * In variable length parameters, it will match each argument as a new player, and only return the matched players.
     */
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @interface PlayerFuzzyMatch {}

    /**
     * Add to a player parameter to only match the player with names that match the given regex expression.
     * In variable length parameters, it will match each argument as a new player, and only return the matched players.
     */
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @interface PlayerRegexMatch {}

}
