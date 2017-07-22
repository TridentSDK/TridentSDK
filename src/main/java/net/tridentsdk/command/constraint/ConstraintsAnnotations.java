package net.tridentsdk.command.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.tridentsdk.command.CommandSourceType;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface ConstraintsAnnotations {

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
    @interface Constrain {
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
        CommandSourceType[] src() default { CommandSourceType.ALL };

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

    /**
     * A repeater class used to repeat the {@link Constrain}
     * annotation.
     *
     * @author TridentSDK
     * @since 0.5-alpha
     */
    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Constrains {
        Constrain[] value();
    }
}
