/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.tridentsdk.api;

/**
 * Represents the instruments that can be played on a note block
 *
 * @author The TridentSDK Team
 */
public enum Instrument {
    /**
     * Piano note
     */
    PIANO(0x0),
    /**
     * Bass drum note
     */
    BASS_DRUM(0x1),
    /**
     * Snare drum note
     */
    SNARE_DRUM(0x2),
    /**
     * Stick note
     */
    STICKS(0x3),
    /**
     * Bass guitar note
     */
    BASS_GUITAR(0x4);

    final byte id;

    Instrument(int i) {
        this.id = (byte) i;
    }

    /**
     * Resolves the Instrument from its respective Byte value
     *
     * @param b Byte representing the Instrument
     * @return Instrument from the supplied Byte
     */
    public static Instrument fromByte(byte b) {
        switch ((int) b) {
            case 0x0:
                return Instrument.PIANO;
            case 0x1:
                return Instrument.BASS_DRUM;
            case 0x2:
                return Instrument.SNARE_DRUM;
            case 0x3:
                return Instrument.STICKS;
            case 0x4:
                return Instrument.BASS_GUITAR;
            default:
                return null;
        }
    }

    /**
     * Returns the {@code byte} value of an Instrument
     *
     * @param instrument Instrument
     * @return Byte value of the instrument
     */
    public static byte toByte(Instrument instrument) {
        return instrument.id;
    }

    /**
     * Returns the {@code byte} value of the Instrument
     *
     * @return Byte value of the Instrument
     */
    public byte toByte() {
        return this.id;
    }
}
