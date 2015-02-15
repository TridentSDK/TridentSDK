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
package net.tridentsdk.inject;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.PeekingIterator;
import net.tridentsdk.util.TridentLogger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Injects the target class, or creates a new instance of an injected class
 *
 * @author The TridentSDK Team
 * @param <T> the type to inject for
 */
public final class Injector<T> {
    private static final Map<Class<?>, Producer<?>> injectors = Maps.newConcurrentMap();

    private final Class<T> clazz;

    private Injector(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Binds a class
     *
     * <p>This returns an injector which you MUST use to bind the producer</p>
     *
     * @param clazz the class to bind
     * @param <T> the type of the class
     * @return the injector which provides binding members
     */
    public static <T> Injector<T> inject(Class<T> clazz) {
        return new Injector<>(clazz);
    }

    /**
     * Binds the provided class to the producer
     *
     * <p>The producer creates objects for injectable fields with that particular type</p>
     *
     * @param producer the producing class
     */
    public void with(Producer<T> producer) {
        injectors.put(this.clazz, producer);
    }

    /**
     * Binds the provided class to the instance given
     *
     * <p>This provides singleton-like functionality for the injector</p>
     *
     * @param instance the instance to return for any injection
     */
    public void with(final T instance) {
        with(new Producer<T>() {
            @Override
            public T produce() {
                return instance;
            }

            @Override
            public T produce(Class<?> metadata) {
                return instance;
            }
        });
    }

    /**
     * Injects all static fields in the class
     *
     * <p>This does not inject any constructors, only static fields of the provided class to inject</p>
     *
     * @param clazz the class with static fields to inject
     */
    public static void staticInject(Class<?> clazz) {
        for (Field field : findFields(clazz)) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            setField(null, field);
        }
    }

    /**
     * Creates a new object which has injectable fields, using the injectable constructor
     *
     * @param clazz the class to instantiate
     * @param args the parameters, not including the injectable classes, in order of declaration
     * @param <T> the type to return
     * @return the new object
     */
    public static <T> T newObject(Class<T> clazz, Object... args) {
        for (Map.Entry<Constructor, Class<?>[]> entry : findConstructors(clazz).entrySet()) {
            List<Object> arguments = Lists.newArrayList();
            Constructor constructor = entry.getKey();
            Class<?>[] constructorParameters = entry.getValue();
            PeekingIterator<Object> iterator = Iterators.peekingIterator(Iterators.forArray(args));

            if (!checkArray(args, constructorParameters))
                continue;

            for (Class<?> c : constructorParameters) {
                Producer<?> producer = injectors.get(c);
                Inject inject = constructor.getDeclaredAnnotation(Inject.class);

                if (iterator.hasNext()) {
                    if (iterator.peek().getClass() == c) {
                        arguments.add(iterator.next());
                        continue;
                    }
                }

                if (producer == null) {
                    if (iterator.hasNext()) {
                        if (iterator.peek().getClass() == c) {
                            arguments.add(iterator.next());
                            continue;
                        }
                    }

                    TridentLogger.error(new IllegalArgumentException(
                            "Constructor " + clazz.getName() + "(" +
                                    Arrays.toString(constructorParameters)
                                            .replaceAll("class ", "")
                                            .replaceAll("\\[", "")
                                            .replaceAll("\\]", "") + ") " +
                                    "does not provide or registered parameter " + c.getName()));
                    return null;
                } else {
                    if (inject.meta() == Class.class) {
                        arguments.add(producer.produce());
                    } else {
                        arguments.add(producer.produce(inject.meta()));
                    }
                }
            }

            try {
                T t = (T) constructor.newInstance(arguments.toArray());
                for (Field field : findFields(clazz)) {
                    setField(t, field);
                }
                return t;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                TridentLogger.error(e);
                return null;
            }
        }

        try {
            T t = clazz.newInstance();
            for (Field field : findFields(clazz)) {
                setField(t, field);
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            TridentLogger.error(e);
            return null;
        }
    }

    /**
     * Injects all fields of an existing object
     *
     * @param instance the instance of the object to inject
     */
    public static void injectAll(Object instance) {
        for (Field field : findFields(instance.getClass())) {
            setField(instance, field);
        }
    }

    private static void setField(Object instance, Field field) {
        field.setAccessible(true);

        Class<?> type = field.getType();
        Producer<?> producer = injectors.get(type);
        if (producer == null) {
            TridentLogger.error(new IllegalArgumentException("Class " +
                    instance.getClass().getName() + " does not have bound injector for type " + type.getName()));
            return;
        }

        Inject inject = field.getAnnotation(Inject.class);
        try {
            if (inject.meta() == Class.class) {
                field.set(instance, producer.produce());
            } else {
                field.set(instance, producer.produce(inject.meta()));
            }
        } catch (IllegalAccessException e) {
            TridentLogger.error(e);
        }
    }

    private static List<Field> findFields(Class<?> c) {
        List<Field> fields = Lists.newArrayList();
        for (Field field : c.getDeclaredFields()) {
            if (field.getAnnotation(Inject.class) != null)
                fields.add(field);
        }
        return fields;
    }

    private static Map<Constructor, Class<?>[]> findConstructors(Class<?> c) {
        Map<Constructor, Class<?>[]> map = Maps.newHashMap();
        for (Constructor constructor : c.getDeclaredConstructors()) {
            if (constructor.getAnnotation(Inject.class) != null)
                map.put(constructor, constructor.getParameterTypes());
        }

        for (Constructor constructor : c.getConstructors()) {
            if (map.containsKey(constructor)) continue;
            if (constructor.getAnnotation(Inject.class) != null)
                map.put(constructor, constructor.getParameterTypes());
        }

        return map;
    }

    private static boolean checkArray(Object[] args, Class<?>[] params) {
        Iterator<Class<?>> iterator = Iterators.forArray(params);
        for (Object o : args) {
            if (!Iterators.contains(iterator, o.getClass()))
                return false;
        }

        return true;
    }
}