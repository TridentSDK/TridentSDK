package net.tridentsdk.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.tridentsdk.command.CommandSourceType;

/**
 * Add to a method so only certain types of {@link net.tridentsdk.command.CommandSource CommandSources} may use the command.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedSourceTypes {

    CommandSourceType[] value();

}
