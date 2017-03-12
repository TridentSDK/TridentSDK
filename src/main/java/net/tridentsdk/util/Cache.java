/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.util;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * A cache that expires stored instances after specified timeframe
 *
 * @param <T> The key type of the cache
 * @param <M> The value type of the cache
 */
public class Cache<T, M> {

    private final Map<T, Tuple<M, Long>> cache = new ConcurrentHashMap<>();
    private final long timeout;
    private final BiConsumer<T, M> expire;
    
    public Cache(long timeout){
        this(timeout, null);
    }
    
    public Cache(long timeout, BiConsumer<T, M> expire){
        this.timeout = timeout;
        this.expire = expire;
    }
    
    public M get(T key, Callable<? extends M> loader){
        boolean contains = this.cache.containsKey(key);
        boolean expired = contains && System.currentTimeMillis() - this.cache.get(key).getB() > this.timeout;
        
        if(!contains || expired){
            if(expired){
                if(this.expire != null){
                    this.expire.accept(key, this.cache.get(key).getA());
                    this.cache.remove(key);
                }
            }
            
            try{
                return this.cache.computeIfAbsent(key, t -> {
                    try{
                        return new Tuple<>(loader.call(), System.currentTimeMillis());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    
                    return null;
                }).getA();
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        
        return this.cache.get(key).getA();
    }
    
    public M getIfPresent(T key){
        Tuple<M, Long> instance = this.cache.get(key);
        if(instance == null){
            return null;
        }
        
        if(System.currentTimeMillis() - instance.getB() > this.timeout){
            if(this.expire != null){
                this.expire.accept(key, instance.getA());
            }

            this.cache.remove(key);
            return null;
        }
        
        return instance.getA();
    }
    
    public void put(T key, M value){
        this.cache.put(key, new Tuple<>(value, System.currentTimeMillis()));
    }

}
