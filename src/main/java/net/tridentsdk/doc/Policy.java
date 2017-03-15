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
package net.tridentsdk.doc;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation may be used to document obscure policies
 * or call attention to certain technical requirements of
 * members such as synchronization guarantees or notices
 * regarding performance.
 *
 * <p>This class provides no additional semantic effects
 * and is not present at runtime.</p>
 *
 * @author TridentSDK
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Policy {
    /**
     * The message that describes the purpose of this
     * annotation being labeled on a member.
     */
    String value();
}