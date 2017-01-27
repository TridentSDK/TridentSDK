/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tridentsdk.event;

import javax.annotation.concurrent.ThreadSafe;
import java.lang.annotation.*;

/**
 * This annotation may be added to an event listener method
 * to indicate that it is able to handle async calls.
 *
 * <p>When in doubt, <strong>DO NOT</strong> use this
 * annotation!</p>
 *
 * <p>Multithreaded servers may wish to call valid event
 * listeners that are registered as async from the target
 * thread without needing to pass to the plugin thread. If
 * your code is self-contained and thread-safe, then using
 * this annotation <em>may</em> allow the server to run
 * more efficiently.</p>
 *
 * <p>This annotation has no effect on members that are not
 * registered listeners.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ThreadSafe
public @interface Async {
}
