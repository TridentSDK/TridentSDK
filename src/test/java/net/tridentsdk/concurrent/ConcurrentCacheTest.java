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

package net.tridentsdk.concurrent;

import net.tridentsdk.factory.CollectFactory;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.util.TridentLogger;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentCacheTest {
    static {
        TridentLogger.init();

        // Oh well, looks like we covered for HeldValueLatch too :P
        Factories.init(new CollectFactory() {
            @Override
            public <K, V> ConcurrentMap<K, V> createMap() {
                return new ConcurrentHashMap<>();
            }
        });
    }

    private final Object key = new Object();
    private final Object value = new Object();
    private final ConcurrentCache<Object, Object> cache = ConcurrentCache.create();

    @Test
    public void exception() {
        cache.retrieve(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        Assert.assertNull(cache.retrieve(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        }));
    }

    @Test
    public void insert() {
        cache.retrieve(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return value;
            }
        });

        Assert.assertEquals(cache.retrieve(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return key;
            }
        }), value);
    }

    @Test
    public void remove() {
        insert();
        Assert.assertNotNull(cache.remove(key));
        Assert.assertNull(cache.remove(key));
    }

    @Test
    public void tableViews() {
        insert();
        Assert.assertEquals(cache.entries().size(), 1);
        Assert.assertEquals(cache.keys().toArray()[0], key);
        Assert.assertEquals(cache.values().toArray()[0], value);
    }
}
