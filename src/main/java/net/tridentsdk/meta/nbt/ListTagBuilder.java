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

package net.tridentsdk.meta.nbt;

/**
 * @author The TridentSDK Team
 */
public class ListTagBuilder<B> {
	
    private final TagContainer parent;
    private final ListTag current;
    private final B parentBuilder;
    private final TagType type;

    public ListTagBuilder(String name, TagContainer parent, B parentBuilder, TagType type) {
        this.parent = parent;
        this.parentBuilder = parentBuilder;
        this.type = type;
        this.current = new ListTag(name, type);
        parent.addTag(this.current);
    }

    public CompoundTagBuilder<ListTagBuilder<B>> beginCompoundTag(String name) {
        return new CompoundTagBuilder<>(name, this.current, this);
    }

    public B endListTag() {
        return this.parentBuilder;
    }

    public ListTagBuilder<ListTagBuilder<B>> beginListTag(String name, TagType type) {
        return new ListTagBuilder<>(name, this.current, this, type);
    }

    public ListTagBuilder<B> tag(Object value) {
        switch (this.type) {
            case BYTE:
                if (value instanceof Byte) {
                    this.current.addTag(new ByteTag(null).setValue((byte) value));
                }
                break;
            case SHORT:
                if (value instanceof Short) {
                    this.current.addTag(new ShortTag(null).setValue((short) value));
                }
                break;
            case INT:
                if (value instanceof Integer) {
                    this.current.addTag(new IntTag(null).setValue((int) value));
                }
                break;
            case LONG:
                if (value instanceof Long) {
                    this.current.addTag(new LongTag(null).setValue((long) value));
                }
                break;
            case FLOAT:
                if (value instanceof Float) {
                    this.current.addTag(new FloatTag(null).setValue((float) value));
                }
                break;
            case DOUBLE:
                if (value instanceof Double) {
                    this.current.addTag(new DoubleTag(null).setValue((double) value));
                }
                break;
            case BYTE_ARRAY:
                if (value instanceof byte[]) {
                    this.current.addTag(new ByteArrayTag(null).setValue((byte[]) value));
                }
                break;
            case STRING:
                if (value instanceof String) {
                    this.current.addTag(new StringTag(null).setValue((String) value));
                }
                break;
            case INT_ARRAY:
                if (value instanceof int[]) {
                    this.current.addTag(new IntArrayTag(null).setValue((int[]) value));
                }
                break;
            default:
                break;
        }

        return this;
    }
    
}
