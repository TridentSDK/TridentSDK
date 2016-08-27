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
package net.tridentsdk.doc;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents a feature that is enabled for testing purpose
 * only and should be removed before production.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Debug {
    /**
     * The default value that the given feature should be
     * reverted to once Trident(SDK) is deployed to
     * production.
     *
     * @return the default, non-debug value
     */
    String value() default "";
}