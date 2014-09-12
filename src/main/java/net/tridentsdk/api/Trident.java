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

import com.google.common.base.Preconditions;
import net.tridentsdk.api.util.TridentLogger;

/**
 * Utility accessor to the {@link net.tridentsdk.api.Server}
 *
 * @author The TridentSDK Team
 */
public final class Trident {
    private static Server server;
    private static TridentLogger logger;

    private Trident() {}

    /**
     * Gets the server singleton that is currently running
     *
     * @return the server that is running
     */
    public static Server getServer() {
        return Trident.server;
    }

    /**
     * Do not call <p/> <p>Will throw an exception if you are not calling from a trusted source</p>
     *
     * @param s the server to set
     */
    public static void setServer(Server s) {
        Preconditions.checkState(Trident.canSet(), "Can only set server instance once!");
        Trident.server = s;
    }

    private static boolean canSet() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement element = elements[3];

        return "net.tridentsdk.server.TridentServer".equals(element.getClassName()) &&
               "createServer".equals(element.getMethodName());
    }

    /**
     * Gets the logger which the server is currently using
     *
     * @return the logger which is being used
     */
    public static TridentLogger getLogger() {
        return Trident.logger;
    }

    public static void setLogger(TridentLogger logger) {
        Trident.logger = logger;
    }
}
