/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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