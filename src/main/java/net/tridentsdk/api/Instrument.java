package net.tridentsdk.api;

/**
 * Represents the instruments that can be played on a note block
 */

public enum Instrument {
    PIANO(0x0),
    BASS_DRUM(0x1),
    SNARE_DRUM(0x2),
    STICKS(0x3),
    BASS_GUITAR(0x4);

    final byte id;

    Instrument(int i) {
        id = (byte) i;
    }

    /**
     * Resolves the Instrument from its respective Byte value
     *
     * @param b Byte representing the Instrument
     * @return Instrument from the supplied Byte
     */
    public static Instrument fromByte(byte b) {
        switch((int) b) {
            case 0x0:
                return PIANO;
            case 0x1:
                return BASS_DRUM;
            case 0x2:
                return SNARE_DRUM;
            case 0x3:
                return STICKS;
            case 0x4:
                return BASS_GUITAR;
            default:
                return null;
        }
    }

    /**
     * Returns the Byte value of the Instrument
     *
     * @return Byte value of the Instrument
     */
    public byte toByte() {
        return id;
    }

    /**
     * Returns the Byte value of an Instrument
     *
     * @param instrument Instrument
     * @return Byte value of instrument
     */
    public static byte toByte(Instrument instrument) {
        return instrument.id;
    }
}
