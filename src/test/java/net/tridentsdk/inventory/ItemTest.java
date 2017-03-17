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
package net.tridentsdk.inventory;

import net.tridentsdk.Impl;
import net.tridentsdk.base.Substance;
import net.tridentsdk.meta.ItemMeta;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

public class ItemTest {
    static {
        Impl.setImpl(Mockito.mock(Impl.ImplementationProvider.class));
        Mockito.when(Impl.get().newItem(Substance.AIR, 1, (byte) 0, null)).
                thenReturn(Mockito.mock(Item.class));
    }

    @Test
    public void newItem() throws Exception {
        assertNotNull(Item.newItem(Substance.AIR));
    }

    @Test
    public void newItem1() throws Exception {
        assertNotNull(Item.newItem(Substance.AIR, 1));
    }

    @Test
    public void newItem2() throws Exception {
        assertNotNull(Item.newItem(Substance.AIR, 1, (byte) 0));
    }

    @Test
    public void newItem3() throws Exception {
        assertNotNull(Item.newItem(Substance.AIR, 1, (byte) 0, new ItemMeta()));
    }
}