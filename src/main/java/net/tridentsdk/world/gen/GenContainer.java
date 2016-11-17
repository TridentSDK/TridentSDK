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
package net.tridentsdk.world.gen;

import javax.annotation.concurrent.Immutable;
import java.util.concurrent.Executor;

/**
 * A gen container is a context which specifies what
 * threading context to run a world generation task.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public interface GenContainer extends Executor {
    /**
     * The default container
     */
    GenContainer DEFAULT = (c) -> {
        throw new RuntimeException();
    };

    /**
     * An arbitrary container, useful for developers who are
     * familiar with multithreaded code and would like their
     * world generation code offloaded to the world handler
     */
    GenContainer ARBITRARY = (c) -> {
        throw new RuntimeException();
    };
}