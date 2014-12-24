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
package java.net.tridentsdk.world;

import net.tridentsdk.world.LevelType;
import org.junit.Assert;
import org.junit.Test;

public class LevelTypeTest {

    @Test
    public void testToString() throws Exception{
        Assert.assertEquals(LevelType.DEFAULT.toString(), "default");
        Assert.assertEquals(LevelType.FLAT.toString(), "flat");
        Assert.assertEquals(LevelType.LARGE_BIOMES.toString(), "largeBiomes");
        Assert.assertEquals(LevelType.AMPLIFIED.toString(), "amplified");
        Assert.assertEquals(LevelType.DEFAULT_1_1.toString(), "default_1_1");
    }

    @Test
    public void testGetLevelType() throws Exception{
        Assert.assertEquals(LevelType.getLevelType("default"), LevelType.DEFAULT);
        Assert.assertEquals(LevelType.getLevelType("flat"), LevelType.FLAT);
        Assert.assertEquals(LevelType.getLevelType("largeBiomes"), LevelType.LARGE_BIOMES);
        Assert.assertEquals(LevelType.getLevelType("amplified"), LevelType.AMPLIFIED);
        Assert.assertEquals(LevelType.getLevelType("default_1_1"), LevelType.DEFAULT_1_1);
        Assert.assertEquals(LevelType.getLevelType("invalid"), LevelType.DEFAULT);
    }

}