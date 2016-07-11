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
package net.tridentsdk.meta.item;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a book's (BOOK_AND_QUILL, WRITTEN_BOOK) additional metadata, which
 * include title, pages, tier and author.
 */
public interface BookMeta extends ItemMeta {
    /**
     * Returns copy tier of the book.
     *
     *
     * <p>0 - original 1 - copy of original 2 - copy of copy, > 2 - cannot be
     * copied</p>
     *
     * @return Copy tier
     */
    int copyTier();

    /**
     * Manually set the copy tier of the book
     *
     * @param copyTier
     *            Copy tier you wish to set it to
     * @see #copyTier()
     */
    void setCopyTier(int copyTier);

    /**
     * Returns last known author of the book.
     *
     * @return The ast known author.
     */
    String author();

    /**
     * Sets the author of the book.
     *
     * @param name
     *            Name of the author.
     */
    void setAuthor(String name);

    /**
     * Gets the title of the book.
     *
     * @return The title.
     */
    String title();

    /**
     * Sets the title of the book
     *
     * @param title
     *            The title.
     */
    void setTitle(String title);

    /**
     * Gets the pages of the book as a List.
     *
     * @return The pages.
     */
    List<String> pages();

    /**
     * Set the pages of the book.
     *
     * @param pages
     *            The pages.
     */
    void setPages(List<String> pages);

    /**
     * Set the pages of the book.
     *
     * @param pages
     *            The pages.
     */
    default void setPages(String... pages) {
        setPages(Arrays.asList(pages));
    }
}
