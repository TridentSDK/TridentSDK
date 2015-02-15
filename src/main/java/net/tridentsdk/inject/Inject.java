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
package net.tridentsdk.inject;

import java.lang.annotation.*;

/**
 * Marks a constructor or field that is eligable for instance injection when constructed with an
 * {@link net.tridentsdk.inject.Injector}
 *
 * @author The TridentSDK Team
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR})
public @interface Inject {
    /**
     * This is the implementation selector
     *
     * <p>Used by the injector to provide different implementations. Leave empty if default should be used.</p>
     *
     * @return the implementation class
     */
    public Class<?> meta() default Class.class;
}
