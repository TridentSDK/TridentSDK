package net.tridentsdk.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface PluginDescription {

    public int priority() default 1;

    public String name();

    public double version() default 1.0;

    public String description() default "Plugin made for TridentSDK";

    public String author() default "";

    public String[] authors() default {};

    public String[] dependencies() default {};

}
