package net.tridentsdk.api.docs;

import java.lang.annotation.Documented;

/**
 * Marks a member of the API or implementation that is designed to be used internally only.
 *
 * <p>The behavior of callers on those members marked by this annotation is unspecified and left undocumented.</p>
 */
@Documented
public @interface InternalUseOnly {
}
