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
    public void testFromByte() throws Exception{
        Assert.assertEquals(Instrument.fromByte((byte) 0x0), Instrument.PIANO);
        Assert.assertEquals(Instrument.fromByte((byte) 0x1), Instrument.BASS_DRUM);
        Assert.assertEquals(Instrument.fromByte((byte) 0x2), Instrument.SNARE_DRUM);
        Assert.assertEquals(Instrument.fromByte((byte) 0x3), Instrument.STICKS);
        Assert.assertEquals(Instrument.fromByte((byte) 0x4), Instrument.BASS_GUITAR);
    }

    @Test
    public void testToByte() throws Exception{
        Assert.assertEquals(Instrument.PIANO.toByte(), (byte) 0x0);
        Assert.assertEquals(Instrument.BASS_DRUM.toByte(), (byte) 0x1);
        Assert.assertEquals(Instrument.SNARE_DRUM.toByte(), (byte) 0x2);
        Assert.assertEquals(Instrument.STICKS.toByte(), (byte) 0x3);
        Assert.assertEquals(Instrument.BASS_GUITAR.toByte(), (byte) 0x4);
    }

    @Test
    public void testToByte1() throws Exception{
        Assert.assertEquals(Instrument.toByte(Instrument.PIANO), (byte) 0x0);
        Assert.assertEquals(Instrument.toByte(Instrument.BASS_DRUM), (byte) 0x1);
        Assert.assertEquals(Instrument.toByte(Instrument.SNARE_DRUM), (byte) 0x2);
        Assert.assertEquals(Instrument.toByte(Instrument.STICKS), (byte) 0x3);
        Assert.assertEquals(Instrument.toByte(Instrument.BASS_GUITAR), (byte) 0x4);
    }

}