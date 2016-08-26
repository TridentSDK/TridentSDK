package net.tridentsdk.world.opt;

public class BlockState {

    private int id;
    private byte data;

    public BlockState(int id, byte data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return this.id;
    }

    public byte getData() {
        return this.data;
    }

    public int toRaw() {
        return (id << 4) | (data & 0xF);
    }

    public char toChar() {
        return (char) (id << 4 | data);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof BlockState && id == ((BlockState) o).id && data == ((BlockState) o).data;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) data;
        return result;
    }

    @Override
    public String toString() {
        return "BlockState{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }

}