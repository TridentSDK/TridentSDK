package net.tridentsdk.event;

import java.lang.annotation.*;

/**
 * This class marks a supertype event class that should not
 * be registered. Events marked by this annotation should
 * only provide functional supermethods, and listeners are
 * not allowed to have handlers that take that particular
 * event.
 *
 * <p>This annotation has no functional effects on the
 * class. It is there to provide runtime checking when the
 * event listeners are registered.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Supertype {
}