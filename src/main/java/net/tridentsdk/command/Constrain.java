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

import java.lang.annotation.*;

/**
 * A constraint for the command handler.
 *
 * <p>A constraint is a property which filters out illegal
 * calls to a command, such as args too small or too large.
 * </p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Documented
@Repeatable(Constrains.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constrain {
    /**
     * The constraining handler.
     *
     * @return the handler
     */
    Class<? extends Constraint> value();

    /**
     * The constraint type, by default there is none.
     *
     * @return the type
     */
    ConstraintType type() default ConstraintType.NONE;

    /**
     * The type of command sources allowed to run the
     * command. Must set {@link #type()} to
     * {@link ConstraintType#SOURCE}.
     *
     * @return the sources
     */
    CmdSourceType[] src() default { CmdSourceType.ALL };

    /**
     * An integer constraint. Must set {@link #type()} to
     * {@link ConstraintType#INT}.
     *
     * @return an integer constraint
     */
    int integer() default 0;

    /**
     * A string constraint. Must set {@link #type()} to
     * {@link ConstraintType#STRING}.
     *
     * @return a string constraint
     */
    String str() default "";
}