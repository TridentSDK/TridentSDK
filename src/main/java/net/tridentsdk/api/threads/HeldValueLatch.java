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
package net.tridentsdk.api.threads;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A one-time latch that holds a value
 *
 * @param <V> the value type of the latch
 */
@ThreadSafe
public class HeldValueLatch<V> {
    private final CountDownLatch latch = new CountDownLatch(1);
    private final AtomicReference<V> value = new AtomicReference<>();

    /**
     * Sets the value in the latch
     *
     * <p>The effects of setting this only once is unspecified</p>
     *
     * <p>This is unsynchronized because all actions prior to counting down <em>happens-before</em> another thread
     * awaits the value</p>
     *
     * @param value the value to set to the latch
     */
    public void countDown(V value) {
        this.value.set(value);
        latch.countDown();
    }

    /**
     * Acquires the value held be the latch, or blocks to wait for the value to become available
     *
     * @return the value held by the latch
     * @throws InterruptedException if the operation was interrupted while blocked
     */
    public V await() throws InterruptedException {
        latch.await();
        return value.get();
    }
}
