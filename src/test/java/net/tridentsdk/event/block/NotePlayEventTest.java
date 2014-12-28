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

package net.tridentsdk.event.block;

import net.tridentsdk.base.Instrument;
import net.tridentsdk.base.Note;
import org.junit.Assert;
import org.junit.Test;

public class NotePlayEventTest {

    @Test
    public void testSetNote() throws Exception {
        NotePlayEvent event = new NotePlayEvent(null, null, new Note(10), Instrument.BASS_GUITAR);
        Assert.assertEquals(event.getNote().id(), 10);

        event.setNote(new Note(5));
        Assert.assertEquals(event.getNote().id(), 5);
    }

    @Test
    public void testSetInstrument() throws Exception {
        NotePlayEvent event = new NotePlayEvent(null, null, new Note(10), Instrument.BASS_GUITAR);
        Assert.assertEquals(event.getInstrument(), Instrument.BASS_GUITAR);

        event.setInstrument(Instrument.STICKS);
        Assert.assertEquals(event.getInstrument(), Instrument.STICKS);
    }

    @Test
    public void testIsIgnored() throws Exception {
        NotePlayEvent event = new NotePlayEvent(null, null, new Note(10), Instrument.BASS_GUITAR);
        Assert.assertEquals(event.isIgnored(), false);

        event.cancel(true);
        Assert.assertEquals(event.isIgnored(), true);
    }
}