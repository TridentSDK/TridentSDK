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
import net.tridentsdk.docs.AccessNoDoc;
import net.tridentsdk.util.TridentLogger;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A LinkedList [implementation] that also makes changes to the underlying JsonArray object
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public class ConfigList<V> implements List<V> {
    private static final long serialVersionUID = -7535821700183585211L;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    protected final Lock write = lock.writeLock();
    private final Lock read = lock.readLock();
    @GuardedBy("lock")
    private final Node<V> head = new Node<>(null, null, null);
    @GuardedBy("lock")
    private final Node<V> tail = head;
    @GuardedBy("write")
    JsonArray jsonHandle;
    @GuardedBy("lock")
    private int size = 0;

    /**
     * Creates a new linked list for the JSON config serializable
     *
     * @param handle the array handler for the list
     */
    protected ConfigList(JsonArray handle) {
        this.jsonHandle = handle;
        head.next = tail;
    }

    /**
     * Creates a new linked list by transferring elements from an existing collection
     *
     * @param handle the array handler for the new list
     * @param c      the initializing elements
     */
    protected ConfigList(JsonArray handle, Collection<V> c) {
        this(handle);
        addAll(c);
    }

    private void lockFully() {
        write.unlock();
        read.lock();
    }

    private void unlockFully() {
        read.unlock();
        write.unlock();
    }

    private void checkElementIndex(int index) {
        int size = this.size;

        if (index < 0 && index > size)
            TridentLogger.error(new IndexOutOfBoundsException("Index: " + index + ", Size: " + size));
    }

    private Node<V> nodeAt(int index) {
        int idx = 0;
        Node<V> node = head;
        while ((node = node.next) != null) {
            if (idx == index)
                return node;
            idx++;
        }

        return null;
    }

    @Override
    public V get(int index) {
        read.lock();
        try {
            checkElementIndex(index);
            return nodeAt(index).value;
        } finally {
            read.unlock();
        }
    }

    @Override
    public int size() {
        read.lock();
        try {
            return size;
        } finally {
            read.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() != 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<V> iterator() {
        return new ConfigIterator(0);
    }

    @Override
    public boolean add(V element) {
        write.lock();
        try {
            tail.next = new Node<>(element, null, tail);
            size += 1;

            this.jsonHandle.add(GsonFactory.gson().toJsonTree(element));
        } finally {
            write.unlock();
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends V> coll) {
        // Locked the entire loop instead of for each individual element

        write.lock();
        try {
            for (V element : coll) {
                tail.next = new Node<>(element, null, tail);
                size += 1;

                this.jsonHandle.add(GsonFactory.gson().toJsonTree(element));
            }
        } finally {
            write.unlock();
        }

        return true;
    }

    /* (non-Javadoc)
     * @see java.util.List#set(int, java.lang.Object)
     */
    @Override
    public V set(int index, V element) {
        V oldValue;
        Node<V> node;

        read.lock();
        try {
            checkElementIndex(index);
            node = nodeAt(index);
            oldValue = node.value;
        } finally {
            read.unlock();
        }

        write.lock();
        try {
            this.jsonHandle.set(index, GsonFactory.gson().toJsonTree(element));
            node.value = element;
        } finally {
            write.unlock();
        }

        return oldValue;
    }

    @Override
    public V remove(int index) {
        write.lock();
        try {
            checkElementIndex(index);

            this.jsonHandle.remove(index);

            V value = tail.value;
            tail.value = null;
            tail.prev.next = null;

            size -= 1;

            return value;
        } finally {
            write.unlock();
        }
    }

    @Override
    public int indexOf(Object o) {
        read.lock();
        try {
            int idx = 0;
            Node<V> node = head;
            while ((node = node.next) != null) {
                if (node.value == o)
                    return idx;
                idx++;
            }
        } finally {
            read.unlock();
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        read.lock();
        try {
            int idx = size - 1;
            Node<V> node = tail;
            while ((node = node.prev) != null) {
                if (node.value == o)
                    return idx;
                idx--;
            }
        } finally {
            read.unlock();
        }

        return -1;
    }

    @Override
    public ListIterator<V> listIterator() {
        return new ConfigIterator(0);
    }

    @Override
    public ListIterator<V> listIterator(int index) {
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node<V> tail = null;
        int index = -1;

        read.lock();
        try {
            int idx = 0;
            Node<V> node = head;
            while ((node = node.next) != null) {
                if (node.value == element) {
                    tail = node;
                    index = idx;

                    break;
                }
                idx++;
            }
        } finally {
            read.unlock();
        }

        if (index == -1)
            return false;

        write.lock();
        try {
            this.jsonHandle.remove(index);

            tail.value = null;
            tail.prev.next = null;

            size -= 1;

            return true;
        } finally {
            write.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        read.lock();
        try {
            for (Object o : c) {
                if (indexOf(o) == -1)
                    return false;
            }
        } finally {
            read.unlock();
        }

        return true;
    }

    /* (non-Javadoc)
     * @see java.util.List#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> coll) {
        lockFully();
        try {
            for (Object o : coll) {
                Node<V> tail = null;
                int index = -1;

                int idx = 0;
                Node<V> node = head;
                while ((node = node.next) != null) {
                    if (node.value == o) {
                        tail = node;
                        index = idx;

                        break;
                    }
                    idx++;
                }

                if (index == -1)
                    continue;

                this.jsonHandle.remove(index);

                tail.value = null;
                tail.prev.next = null;

                size -= 1;

                return true;
            }
        } finally {
            unlockFully();
        }

        return false;
    }

    /* (non-Javadoc)
     * @see java.util.List#clear()
     */
    @Override
    public void clear() {
        write.lock();
        try {
            Node<V> node = head;
            while ((node = node.next) != null) {
                node.value = null;
                node.prev.next = null;

                size -= 1;
            }

            this.jsonHandle = new JsonArray();
        } finally {
            write.unlock();
        }
    }

    /**
     * Not implemented
     */
    @Override
    public void add(int index, V element) {
        TridentLogger.error(new UnsupportedOperationException("Cannot invoke on Lists from Config"));
    }

    /**
     * Not implemented
     */
    @Override
    public boolean addAll(int arg0, Collection<? extends V> arg1) {
        TridentLogger.error(new UnsupportedOperationException("Cannot invoke on Lists from Config"));
        return false;
    }

    /**
     * Not implemented
     */
    @Override
    public boolean retainAll(Collection<?> arg0) {
        TridentLogger.error(new UnsupportedOperationException("Cannot invoke on Lists from Config"));
        return false;
    }

    /**
     * Not implemented
     */
    @Override
    public List<V> subList(int arg0, int arg1) {
        TridentLogger.error(new UnsupportedOperationException("Cannot invoke on Lists from Config"));
        return null;
    }

    /**
     * Not implemented
     */
    @Override
    public V[] toArray() {
        TridentLogger.error(new UnsupportedOperationException("Cannot invoke on Lists from Config"));
        return null;
    }

    /**
     * Not implemented
     */
    @Override
    public <T> T[] toArray(T[] arg0) {
        TridentLogger.error(new UnsupportedOperationException("Cannot invoke on Lists from Config"));
        return null;
    }

    @AccessNoDoc
    private class Node<E> {
        @GuardedBy("lock")
        E value;
        @GuardedBy("lock")
        Node<E> next;
        @GuardedBy("lock")
        Node<E> prev;

        private Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @AccessNoDoc
    private class ConfigIterator implements ListIterator<V> {
        private final AtomicInteger current = new AtomicInteger();

        public ConfigIterator(int index) {
            current.set(index);
        }

        @Override
        public boolean hasNext() {
            return (current.get() + 1) <= size();
        }

        @Override
        public V next() {
            return get(current.incrementAndGet());
        }

        @Override
        public boolean hasPrevious() {
            return current.get() > 0;
        }

        @Override
        public V previous() {
            read.lock();
            try {
                return nodeAt(current.get()).prev.value;
            } finally {
                read.unlock();
            }
        }

        @Override
        public int nextIndex() {
            return current.get() + 1;
        }

        @Override
        public int previousIndex() {
            return current.get() - 1;
        }

        @Override
        public void remove() {
            ConfigList.this.remove(current.get());
        }

        @Override
        public void set(V v) {
            ConfigList.this.set(current.get(), v);
        }

        @Override
        public void add(V v) {
            ConfigList.this.add(v);
        }
    }
}
