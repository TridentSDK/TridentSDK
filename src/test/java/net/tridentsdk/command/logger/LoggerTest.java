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
package net.tridentsdk.command.logger;

import net.tridentsdk.Impl;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

public class LoggerTest {
    private static final String NAME = LoggerTest.class.getSimpleName();

    static {
        Impl.setImpl(Mockito.mock(Impl.ImplementationProvider.class));
        Mockito.when(Impl.get().newLogger(NAME)).thenReturn(Mockito.mock(Logger.class));
    }

    @Test
    public void get() throws Exception {
        assertNotNull(Logger.get(NAME));
    }

    @Test
    public void get1() throws Exception {
        assertNotNull(Logger.get(this.getClass()));
    }
}