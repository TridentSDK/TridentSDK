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
package net.tridentsdk.docs;

import java.lang.annotation.Documented;

/**
 * Use to document usage of esoteric or difficult/subtle to understand portions of code
 *
 * @author The TridentSDK Team
 */
@Documented
public @interface Policy {
    /**
     * The policy of which to abide by when using the particular member
     *
     * @return the policy
     */
    String value();

    /**
     * Denotes a volatile array whose elements should not be mutated singularly
     *
     * <p>Possible solutions include:
     * <ul>
     * <li>Use an {@link java.util.concurrent.atomic.AtomicReferenceArray} instead</li>
     * <li>Modify the array as a whole instead of the single element</li>
     * <li>Contact the concurrency supervisor</li>
     * </ul></p>
     *
     * <p>Justification: Volatile arrays entail a volatile reference to the whole memory block;
     * not each individual element. Mutations of a point along the block is a violation of object consistency</p>
     */
    String VOLATILE_ARRAY = "";
}
