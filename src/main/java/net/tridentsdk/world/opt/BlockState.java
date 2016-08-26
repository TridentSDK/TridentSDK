/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
        return (this.id << 4) | (this.data & 0xF);
    }

    public char toChar() {
        return (char) (this.id << 4 | this.data);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof BlockState && this.id == ((BlockState) o).id && this.data == ((BlockState) o).data;

    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + (int) this.data;
        return result;
    }

    @Override
    public String toString() {
        return "BlockState{" +
                "id=" + this.id +
                ", data=" + this.data +
                '}';
    }

}