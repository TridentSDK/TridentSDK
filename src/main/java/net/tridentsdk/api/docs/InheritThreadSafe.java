package net.tridentsdk.api.docs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 * Marks that the class is thread safe. Similar to {@link javax.annotation.concurrent.ThreadSafe}.
 *
 * <p>Also marks that inheriting classes are also thread safe.</p>
 *
 * @author The TridentSDK Team
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
public @interface InheritThreadSafe {
}
