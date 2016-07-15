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