package net.tridentsdk.world.gen;

import net.tridentsdk.doc.Internal;

import javax.annotation.concurrent.Immutable;
import java.util.function.Supplier;

/**
 * A gen container is a context which specifies what
 * threading context to run a world generation task.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public interface GenContainer {
    /**
     * Create a new gen container that doesn't do anything.
     *
     * <p>For internal use only.</p>
     *
     * @return a useless gen container
     */
    @Internal
    static GenContainer none() {
        return new GenContainer() {
            @Override
            public <R> R run(Supplier<R> supplier) {
                throw new RuntimeException("Do not use this method");
            }
        };
    }

    /**
     * The default container
     */
    GenContainer DEFAULT = none();
    /**
     * An arbitrary container, useful for developers who are
     * familiar with multithreaded code and would like their
     * world generation code offloaded to the world handler
     */
    GenContainer ARBITRARY = none();

    /**
     * Runs the given function that supplies the result of
     * performing the operation.
     *
     * @param supplier the function which is run by this
     *                 container
     * @param <R>      the type of result that is produced
     * @return the result
     */
    <R> R run(Supplier<R> supplier);
}