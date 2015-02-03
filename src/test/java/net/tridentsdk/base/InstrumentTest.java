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

import org.junit.Assert;
import org.junit.Test;

public class InstrumentTest {

    @Test
    public void testFromByte() throws Exception {
        Assert.assertEquals(Instrument.fromByte((byte) 0x0), Instrument.PIANO);
        Assert.assertEquals(Instrument.fromByte((byte) 0x1), Instrument.BASS_DRUM);
        Assert.assertEquals(Instrument.fromByte((byte) 0x2), Instrument.SNARE_DRUM);
        Assert.assertEquals(Instrument.fromByte((byte) 0x3), Instrument.STICKS);
        Assert.assertEquals(Instrument.fromByte((byte) 0x4), Instrument.BASS_GUITAR);
        Assert.assertEquals(Instrument.fromByte((byte) 0x5), null);
    }

    @Test
    public void testToByte() throws Exception {
        Assert.assertEquals(Instrument.PIANO.asByte(), (byte) 0x0);
        Assert.assertEquals(Instrument.BASS_DRUM.asByte(), (byte) 0x1);
        Assert.assertEquals(Instrument.SNARE_DRUM.asByte(), (byte) 0x2);
        Assert.assertEquals(Instrument.STICKS.asByte(), (byte) 0x3);
        Assert.assertEquals(Instrument.BASS_GUITAR.asByte(), (byte) 0x4);
    }
}