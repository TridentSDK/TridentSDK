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

package net.tridentsdk.base;

import net.tridentsdk.base.Note;
import org.junit.Assert;
import org.junit.Test;

public class NoteTest {

    @Test
    public void testCreate() throws Exception {
        try {
            Note n = new Note(-1);
            Assert.fail();
        } catch (Exception ignored) {
        }

        try {
            Note n = new Note(25);
            Assert.fail();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testSharpen() throws Exception {
        Note n = new Note(0);

        for (int i = 0; i < 24; i++) {
            n = n.sharpen();
        }

        Assert.assertEquals(n.getNote(), 24);

        try {
            n.sharpen();
            Assert.fail();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testFlatten() throws Exception {
        Note n = new Note(24);

        for (int i = 0; i < 24; i++) {
            n = n.flatten();
        }

        Assert.assertEquals(n.getNote(), 0);

        try {
            n.flatten();
            Assert.fail();
        } catch (Exception ignored) {
        }
    }
}