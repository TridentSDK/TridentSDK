/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.tridentsdk.api;

/**
 * Immutable value representing the pitch of a note played
 *
 * @author The TridentSDK Team
 */
public class Note {
    private final short id;

    Note(int id) {
        if (id > 24) {
            throw new IllegalArgumentException("Note is too high!");
        } else if (id < 0) {
            throw new IllegalArgumentException("Note is too low!");
        }

        this.id = (short) id;
    }

    /**
     * Returns a note one step sharper than this
     */
    public Note sharpen() {
        if ((int) this.id + 1 > 24) {
            throw new IllegalArgumentException("Cannot sharpen this note, it is already the max");
        }
        return new Note((int) this.id + 1);
    }

    /**
     * Returns a note flatter than this
     */
    public Note flatten() {
        if ((int) this.id - 1 < 0)
            throw new IllegalArgumentException("Cannot flatten this note, it is already the max");
        return new Note((int) this.id - 1);
    }

    // TODO: make this more notable & remove the horrible puns
}
