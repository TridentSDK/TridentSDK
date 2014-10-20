package net.tridentsdk.plugin.annotation;

import net.tridentsdk.api.CommandIssuer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommandDescription {
    String name ();
    String permission();
    Class<? extends CommandIssuer> [] targets() default {CommandIssuer.class};

}
