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

package net.tridentsdk.config;

import com.google.gson.JsonArray;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.concurrent.locks.Lock;

/**
 * Section of the config dedicated to storing values from a collection
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public class ConfigSectionList<V> extends ConfigList<V> {
	
    private final ConfigSection parent;

    private final Lock lock = super.write;

    /**
     * Creates a new section list in the config
     *
     * @param parent the parent configuration section, usually a {@link net.tridentsdk.config.JsonConfig}.
     * @param handle the array handler
     */
    protected ConfigSectionList(ConfigSection parent, JsonArray handle) {
        super(handle);
        this.parent = parent;
    }

    @Override
    public boolean add(V element) {
        boolean changed = super.add(element);

        if (changed) {
            lock.lock();
            try {
                this.jsonHandle.add(((ConfigSection) element).asJsonObject());
            } finally {
                lock.unlock();
            }
        }

        return changed;
    }

    @Override
    public boolean addAll(Collection<? extends V> coll) {
        boolean changed = super.addAll(coll);

        if (changed) {
            lock.lock();
            try {
                for (V element : coll) {
                    this.jsonHandle.add(((ConfigSection) element).asJsonObject());
                }
            } finally {
                lock.unlock();
            }
        }

        return changed;
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#set(int, java.lang.Object)
     */
    @Override
    public V set(int index, V element) {
        lock.lock();
        try {
            this.jsonHandle.set(index, ((ConfigSection) element).asJsonObject());
            return super.set(index, element);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(int index) {
        lock.lock();
        try {
            this.jsonHandle.remove(index);
            return super.remove(index);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(Object element) {
        boolean success = super.remove(element);

        if (success) {
            lock.lock();
            try {
                this.jsonHandle.remove(((ConfigSection) element).asJsonObject());
            } finally {
                lock.unlock();
            }
        }

        return success;
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> coll) {
        boolean changed = super.removeAll(coll);

        if (changed) {
            for (Object o : coll) {
                lock.lock();
                try {
                    this.jsonHandle.remove(((ConfigSection) o).asJsonObject());
                } finally {
                    lock.unlock();
                }
            }
        }

        return changed;
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#clear()
     */
    @Override
    public void clear() {
        lock.lock();
        try {
            super.clear();
            this.jsonHandle = new JsonArray();
        } finally {
            lock.unlock();
        }
    }

    protected ConfigSection getParent() {
        return this.parent;
    }
    
}
