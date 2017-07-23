package net.tridentsdk.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add to a method so only {@link net.tridentsdk.command.CommandSource CommandSources} with one of the given permissions
 * may use the command.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionRequired {

    String[] value();

}
