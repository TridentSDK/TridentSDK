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
package net.tridentsdk.util;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

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
        boolean expired = cache.containsKey(key) && System.currentTimeMillis() - cache.get(key).getB() > timeout;
        if(!cache.containsKey(key) || expired){
            if(expired){
                if(expire != null){
                    expire.accept(key, cache.get(key).getA());
                }
            }
            
            try{
                cache.put(key, new Tuple<>(loader.call(), System.currentTimeMillis()));
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        
        return cache.get(key).getA();
    }
    
    public M getIfPresent(T key){
        if(!cache.containsKey(key)){
            return null;
        }
        
        if(System.currentTimeMillis() - cache.get(key).getB() > timeout){
            if(expire != null){
                expire.accept(key, cache.get(key).getA());
            }
            
            cache.remove(key);
            return null;
        }
        
        return cache.get(key).getA();
    }
    
    public void put(T key, M value){
        cache.put(key, new Tuple<>(value, System.currentTimeMillis()));
    }

}
