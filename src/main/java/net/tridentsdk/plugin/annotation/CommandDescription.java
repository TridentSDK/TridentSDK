package net.tridentsdk.plugin.annotation;

import net.tridentsdk.plugin.Command.SubCommandHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandDescription {
    String name();

    int priority() default 1;

    String permission();

    String [] aliases();

    Class<? extends SubCommandHandler> [] subCommands() default {};
}
