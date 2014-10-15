/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api.config;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// TODO: Javadoc
/**
 * An ArrayList that also makes changes to the underlying JsonArray object
 *
 * @author The TridentSDK Team
 */
public class ConfigList<V> extends ArrayList<V> {
    private static final long serialVersionUID = -7535821700183585211L;

    JsonArray jsonHandle;

    protected ConfigList(JsonArray handle) {
        this.jsonHandle = handle;
    }

    @Override
    public boolean add(V element) {
        boolean changed = super.add(element);
        if (changed) {
            this.jsonHandle.add(GsonFactory.getGson().toJsonTree(element));
        }
        return changed;
    }

    @Override
    public boolean addAll(Collection<? extends V> coll) {
        boolean changed = super.addAll(coll);
        if (changed) {
            for (V element : coll) {
                this.add(element);
            }
        }
        return changed;
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#set(int, java.lang.Object)
     */
    @Override
    public V set(int index, V element) {
        this.jsonHandle.set(index, GsonFactory.getGson().toJsonTree(element));
        return super.set(index, element);
    }

    @Override
    public V remove(int index) {
        this.jsonHandle.remove(index);
        return super.remove(index);
    }

    @Override
    public boolean remove(Object element) {
        boolean success = super.remove(element);
        if (success) {
            this.jsonHandle.remove(GsonFactory.getGson().toJsonTree(element));
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
                this.remove(o);
            }
        }
        return changed;
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#removeRange(int, int)
     */
    @Override
    protected void removeRange(int start, int end) {
        super.removeRange(start, end);
        for (int i = start; i < end; i++) {
            this.jsonHandle.remove(i);
        }
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#clear()
     */
    @Override
    public void clear() {
        super.clear();
        this.jsonHandle = new JsonArray();
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#add(int, java.lang.Object)
     */
    @Override
    public void add(int index, V element) {
        throw new UnsupportedOperationException("Cannot invoke on Lists from Config");
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#addAll(int, java.util.Collection)
     */
    @Override
    public boolean addAll(int arg0, Collection<? extends V> arg1) {
        throw new UnsupportedOperationException("Cannot invoke on Lists from Config");
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#retainAll(java.util.Collection)
     */
    @Override
    public boolean retainAll(Collection<?> arg0) {
        throw new UnsupportedOperationException("Cannot invoke on Lists from Config");
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#spliterator()
     */

    /* (non-Javadoc)
     * @see java.util.ArrayList#subList(int, int)
     */
    @Override
    public List<V> subList(int arg0, int arg1) {
        throw new UnsupportedOperationException("Cannot invoke on Lists from Config");
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#toArray()
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Cannot invoke on Lists from Config");
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#toArray(java.lang.Object[])
     */
    @Override
    public <T> T[] toArray(T[] arg0) {
        throw new UnsupportedOperationException("Cannot invoke on Lists from Config");
    }
}
