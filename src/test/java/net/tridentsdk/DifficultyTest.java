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

package net.tridentsdk;

import org.junit.Assert;
import org.junit.Test;

public class DifficultyTest {

    @Test
    public void testToByte() throws Exception {
        Assert.assertEquals(Difficulty.PEACEFUL.toByte(), 0);
        Assert.assertEquals(Difficulty.EASY.toByte(), 1);
        Assert.assertEquals(Difficulty.NORMAL.toByte(), 2);
        Assert.assertEquals(Difficulty.HARD.toByte(), 3);
    }

    @Test
    public void testGetDifficulty() throws Exception {
        Assert.assertEquals(Difficulty.difficultyOf(0), Difficulty.PEACEFUL);
        Assert.assertEquals(Difficulty.difficultyOf(1), Difficulty.EASY);
        Assert.assertEquals(Difficulty.difficultyOf(2), Difficulty.NORMAL);
        Assert.assertEquals(Difficulty.difficultyOf(3), Difficulty.HARD);

        Assert.assertEquals(Difficulty.difficultyOf(10), Difficulty.NORMAL);
    }
}