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

package net.tridentsdk.base;

import net.tridentsdk.util.TridentLogger;

import javax.annotation.concurrent.Immutable;

/**
 * Immutable value representing the pitch of a note played
 *
 * @author The TridentSDK Team
 */
@Immutable
public class Note {
    private final short id;

    public Note(int id) {
        if (id > 24) {
            TridentLogger.error(new IllegalArgumentException("Note is too high!"));
        } else if (id < 0) {
            TridentLogger.error(new IllegalArgumentException("Note is too low!"));
        }
        // just noticing, since this is sandwiched between 0 <= id <= 24, a byte can be used instead of a short -- Nick R
        this.id = (short) id;
    }

    /**
     * Returns a note one step sharper than this
     */
    public Note sharpen() {
        if ((int) this.id + 1 > 24) {
            TridentLogger.error(new IllegalArgumentException("Cannot sharpen this note, it is already the maximum"));
        }
        return new Note((int) this.id + 1);
    }

    /**
     * Returns a note flatter than this
     */
    public Note flatten() {
        if ((int) this.id - 1 < 0) {
            TridentLogger.error(new IllegalArgumentException("Cannot flatten this note, it is already the minimum"));
        }
        return new Note((int) this.id - 1);
    }

    public short id() {
        return id;
    }
}
