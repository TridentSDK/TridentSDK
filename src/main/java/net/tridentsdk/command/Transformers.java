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
package net.tridentsdk.command;

import net.tridentsdk.Server;
import net.tridentsdk.command.annotation.PlayerExactMatch;
import net.tridentsdk.command.annotation.PlayerFuzzyMatch;
import net.tridentsdk.command.annotation.PlayerRegexMatch;
import net.tridentsdk.entity.living.Player;

import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

/**
 * @author Nick Robson
 */
public final class Transformers {

    private static final Map<Class<?>, BiFunction<String, Parameter, ?>> transformers = new ConcurrentHashMap<>();
    private static final Map<Class<?>, BiFunction<String, Parameter, ?>> inheritedTransformers = new ConcurrentHashMap<>();

    static {
        registerTransformer(Byte.class, (s, p) -> {
            try {
                return Byte.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer in -128 to 127!");
            }
        });
        registerTransformer(Short.class, (s, p) -> {
            try {
                return Short.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer in -65536 to 65535!");
            }
        });
        registerTransformer(Integer.class, (s, p) -> {
            try {
                return Integer.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer!");
            }
        });
        registerTransformer(Long.class, (s, p) -> {
            try {
                return Long.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer!");
            }
        });
        registerTransformer(Float.class, (s, p) -> {
            try {
                return Float.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter a number!");
            }
        });
        registerTransformer(Double.class, (s, p) -> {
            try {
                return Double.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter a number!");
            }
        });
        registerTransformer(Number.class, (s, p) -> {
            try {
                return Double.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter a number!");
            }
        });
        registerTransformer(Boolean.class, (s, p) -> !s.isEmpty() && (s.charAt(0) == 'y' || s.charAt(0) == 't'));
        registerTransformer(String.class, (s, p) -> s);
        registerTransformer(Object.class, (s, p) -> s);
        registerTransformer(Player.class, (s, p) -> {
            PlayerExactMatch pem = p != null ? p.getAnnotation(PlayerExactMatch.class) : null;
            PlayerFuzzyMatch pfm = p != null ? p.getAnnotation(PlayerFuzzyMatch.class) : null;
            PlayerRegexMatch prm = p != null ? p.getAnnotation(PlayerRegexMatch.class) : null;
            if (pem != null) {
                return Server.getInstance().getPlayerExact(s);
            } else if (pfm != null) {
                return Server.getInstance().getPlayersFuzzyMatching(s).stream().findAny().orElse(null);
            } else if (prm != null) {
                Pattern pattern;
                try {
                    pattern = Pattern.compile(s);
                } catch (Exception ex) {
                    throw new TransformationException("Invalid regex pattern provided");
                }
                return Server.getInstance().getPlayers().stream()
                        .filter(x -> pattern.matcher(x.getName()).find())
                        .findAny()
                        .orElse(null);
            } else {
                return Server.getInstance().getPlayersMatching(s).stream().findAny().orElse(null);
            }
        });
    }

    private Transformers() {
    }

    public static <T> void registerTransformer(Class<T> clazz, BiFunction<String, Parameter, ?> transformer) {
        Objects.requireNonNull(clazz, "class cannot be null");
        Objects.requireNonNull(transformer, "transformer for " + clazz + " cannot be null");
        if (transformers.putIfAbsent(clazz, transformer) != null)
            return;
        Class<?> cls = clazz.getSuperclass();
        while (cls != null && cls != Object.class) {
            if (inheritedTransformers.putIfAbsent(cls, transformer) != null)
                break;
            cls = cls.getSuperclass();
        }
    }

    public static <T> T transform(String input, Parameter parameter, Class<T> clazz) throws Exception {
        Objects.requireNonNull(input, "input cannot be null");
        Objects.requireNonNull(parameter, "parameter cannot be null");
        BiFunction<String, Parameter, ?> transformer = transformers.get(clazz);
        if (transformer == null)
            transformer = inheritedTransformers.get(clazz);
        if (transformer == null)
            throw new UnsupportedOperationException("transformer missing for " + clazz);
        return (T) transformer.apply(input, parameter);
    }

}
