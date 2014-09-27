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

package net.tridentsdk.api.config;

import com.google.gson.JsonArray;

import java.util.*;

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
