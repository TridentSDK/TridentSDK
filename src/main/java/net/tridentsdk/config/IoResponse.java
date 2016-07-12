package net.tridentsdk.config;

/**
 * This class represents the set of responses that may be
 * returned by a method which performs I/O operations.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum IoResponse {
    /**
     * The I/O operation should have completed successfully
     * if this response is returned.
     */
    SUCCESS,
    /**
     * The I/O operation failed because the resource, i.e.
     * the file that should be written was not present, or
     * there was a permissions error.
     */
    FILE_NOT_PRESENT,
    /**
     * If an unknown {@link java.io.IOException} occurred,
     * this will be returned.
     */
    IO_EXCEPTION,
    /**
     * If some other error occurred while the operation is
     * taking place, this will be returned.
     */
    OTHER
}