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

package java.net.tridentsdk.util;

import net.tridentsdk.util.PartRotation;
import org.junit.Assert;
import org.junit.Test;

public class PartRotationTest {

    @Test
    public void testClass() throws Exception {
        PartRotation partRotation = new PartRotation(10, 20, 30);

        Assert.assertEquals(partRotation.getRotX(), 10);
        Assert.assertEquals(partRotation.getRotY(), 20);
        Assert.assertEquals(partRotation.getRotZ(), 30);

        partRotation.setRotX(40);
        partRotation.setRotY(50);
        partRotation.setRotZ(60);

        Assert.assertEquals(partRotation.getRotX(), 40);
        Assert.assertEquals(partRotation.getRotY(), 50);
        Assert.assertEquals(partRotation.getRotZ(), 60);
    }
}