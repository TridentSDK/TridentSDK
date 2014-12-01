package net.tridentsdk.api.docs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Marks a class that is meant to be thread-safe, but has not been evaluated or thoroughly tested. Use with caution.
 */
@Documented
@Target(ElementType.TYPE)
public @interface PossiblyThreadSafe {
}
