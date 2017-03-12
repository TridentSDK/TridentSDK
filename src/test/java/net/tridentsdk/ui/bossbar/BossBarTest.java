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
package net.tridentsdk.ui.bossbar;

import net.tridentsdk.Impl;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

public class BossBarTest {
    static {
        Impl.setImpl(Mockito.mock(Impl.ImplementationProvider.class));
        Mockito.when(Impl.get().newBossBar()).thenReturn(Mockito.mock(BossBar.class));
    }

    @Test
    public void newBossBar() throws Exception {
        assertNotNull(BossBar.newBossBar());
    }
}