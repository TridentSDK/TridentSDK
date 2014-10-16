package net.tridentsdk.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandDescription {
    String name();

    int priority();

    String permission();

    Class [] subclasses();

    String [] aliases();
}
