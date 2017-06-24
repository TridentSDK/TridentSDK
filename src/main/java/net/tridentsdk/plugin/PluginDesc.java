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
package net.tridentsdk.plugin;

import java.lang.annotation.*;

/**
 * This is a required annotation to be placed above the sole
 * {@link Plugin} main class in order to describe the
 * properties of the plugin when it is loaded by the server.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginDesc {
    /**
     * A unique ID that identifies a particular plugin. This
     * will also be used as the plugin's config folder name.
     *
     * <p>While not necessarily needing to conform to a
     * particular set of guidelines, it is recommended that
     * this does not change version to version to prevent
     * duplicates from being loaded concurrently. It should
     * be used to discern between two plugins of a similar
     * or even the same {@link #name()}, but perhaps
     * incorporate a prefix or an author name or something
     * of the like.</p>
     */
    String id();

    /**
     * The name of the plugin. This need not be unique, but
     * is recommended to be short and iconic. Avoid using
     * newlines.
     */
    String name() default "";

    /**
     * The plugin version.
     */
    String version() default "1.0";

    /**
     * The plugin author.
     */
    String author() default "";

    /**
     * Plugin dependencies.
     *
     * <p>Dependencies must have a special format. They
     * should be formatted {@code <id>:<version>} such that
     * the correct plugin and version should be loaded prior
     * to loading the plugin.</p>
     */
    String[] depends() default {};
}