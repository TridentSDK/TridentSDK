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

/**
 * A class containing two separate data values
 * Usually used in return statements or as values in maps
 *
 * @param <T> The first (A) instance type
 * @param <M> The second (B) instance type
 */
public class Tuple<T, M> {
    
    private final T a;
    private final M b;
    
    public Tuple(T a, M b){
        this.a = a;
        this.b = b;
    }
    
    public T getA(){
        return this.a;
    }
    
    public M getB(){
        return this.b;
    }
    
}
