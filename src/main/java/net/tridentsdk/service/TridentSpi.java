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
package net.tridentsdk.service;

/**
 * The trident service provider interface
 *
 * @author The TridentSDK Team
 */
public class TridentSpi {
    private static final ChatHandler CHAT = new ChatHandler();
    private static final PermissionHandler PERMS = new PermissionHandler();
    private static final TransactionHandler TRANSACTIONS = new TransactionHandler();

    public static ChatHandler provideChat() {
        return CHAT;
    }

    public static PermissionHandler providePerms() {
        return PERMS;
    }

    public static TransactionHandler provideTransactions() {
        return TRANSACTIONS;
    }
}
