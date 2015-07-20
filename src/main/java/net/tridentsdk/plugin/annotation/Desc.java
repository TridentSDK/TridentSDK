/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
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

package net.tridentsdk.plugin.annotation;

import java.lang.annotation.*;

/**
 * Describes a plugin properties and information
 *
 * <p>The information provided to each field has no semantic properties</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Desc {
    // TODO: Provide documentation on what this priority actually is
    int priority() default 1;

    /**
     * The plugin name, which will always be loaded even if it has the same name as another
     */
    String name();

    /**
     * The plugin version
     */
    String version() default "1.0";

    /**
     * Purpose of the plugin
     */
    String description() default "Plugin made for TridentSDK";

    /**
     * The person who wrote the plugin
     */
    String author() default "";

    /**
     * The people that wrote the plugin
     */
    String[] authors() default { };

    /**
     * The plugin(s) which is depended on by the described plugin
     */
    String[] dependencies() default { };
}
