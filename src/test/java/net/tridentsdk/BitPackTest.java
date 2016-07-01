package net.tridentsdk;

import com.google.common.collect.Lists;

import java.util.List;

public class BitPackTest {
    public static final int PACK_SIZE = 8;
    private byte pack;

    public void write(boolean b, int pos) {
        int bit = b ? 1 : 0;
        this.pack |= bit << pos;
    }

    public boolean read(int pos) {
        return (this.pack >> pos & 1) == 1;
    }

    public static void main(String[] args) {
        BitPackTest test = new BitPackTest();
        List<Boolean> bools = Lists.newArrayList(
                true, false,
                true, false,
                true, false,
                true, false);
        for (int i = 0; i < PACK_SIZE; i++) {
            test.write(bools.get(i), i);
        }

        pl("Printing the test booleans:");
        for (boolean b : bools) {
            p(b + ", ");
        }
        pl("");
        pl("");
        pl("Printing the booleans packed:");
        for (int i = 0; i < PACK_SIZE; i++) {
            p(test.read(i) + ", ");
        }
        pl("");
    }

    private static void p(String s) {
        System.out.print(s);
    }

    private static void pl(String s) {
        System.out.println(s);
    }
}