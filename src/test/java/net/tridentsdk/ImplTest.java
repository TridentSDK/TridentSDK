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
package net.tridentsdk;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ImplTest {
    @Test
    public void testSetGet() {
        Impl.setImpl(new IImplImpl());
        assertNotNull(Impl.get());
    }

    @Test(expected = RuntimeException.class)
    public void testError() {
        Thread current = Thread.currentThread();
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
                current.interrupt();
            } catch (InterruptedException e) {
                fail("This is not supposed to happen");
            }
        }).start();

        Impl.get();
    }

    public static class IImplImpl implements Impl.ImplementationProvider {
        @Override
        public Server svr() {
            return null;
        }
    }
}