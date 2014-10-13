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

package net.tridentsdk.api.util;

/**
 * Represents the rotation of an Armor Stand part
 *
 * @author TridentSDK Team
 */
public class PartRotation {
    private int rotX;
    private int rotY;
    private int rotZ;

    /**
     * @param rotX Rotation value on the X plane
     * @param rotY Rotation value on the Y plane
     * @param rotZ Rotation value on the Z plane
     */
    public PartRotation(int rotX, int rotY, int rotZ) {
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }

    /**
     * Get the rotation on the X plane
     *
     * @return Integer rotation on the X plane
     */
    public int getRotX() {
        return this.rotX;
    }

    /**
     * Set the rotation on the X plane
     *
     * @param rotX rotation on the X plane
     */
    public void setRotX(int rotX) {
        this.rotX = rotX;
    }

    /**
     * Get the rotation on the Y plane
     *
     * @return Integer rotation on the Y plane
     */
    public int getRotY() {
        return this.rotY;
    }

    /**
     * Set the rotation on the Y plane
     *
     * @param rotY rotation on the Y plane
     */
    public void setRotY(int rotY) {
        this.rotY = rotY;
    }

    /**
     * Get the rotation on the Z plane
     *
     * @return Integer rotation on the Z plane
     */
    public int getRotZ() {
        return this.rotZ;
    }

    /**
     * Set the rotation on the Z plane
     *
     * @param rotZ rotation on the Z plane
     */
    public void setRotZ(int rotZ) {
        this.rotZ = rotZ;
    }
}
