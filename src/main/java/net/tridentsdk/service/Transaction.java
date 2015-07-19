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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a transaction of a type between supported entities, including players via the
 * {@link Transactions}, and the callback which is executed for the transaction to occur
 *
 * @author The TridentSDK Team
 */
public abstract class Transaction<S, R> {
    private final Object item;
    private final S sender;
    private final R receiver;
    final AtomicInteger amount = new AtomicInteger();

    /**
     * Provides the construction support for transactions
     *
     * @param item the item type to transact
     * @param sender the sender of the transaction. The person being withdrawn from in a withdrawl.
     * @param receiver the receiver of the transaction
     * @param amount the amount to operate upon
     */
    public Transaction(Object item, S sender, R receiver, int amount) {
        this.item = item;
        this.sender = sender;
        this.receiver = receiver;
        this.amount.set(amount);
    }

    /**
     * Creates a new transaction that does not have a callback
     *
     * @param item the item type to transact
     * @param sender the sender of the transaction. The person being withdrawn from in a withdrawl.
     * @param receiver the receiver of the transaction
     * @param amount the amount to operate upon. Do not make negative for withdrawls.
     * @return the new transaction
     */
    public static <S, R> Transaction<S, R> quietTransaction(Object item, S sender, R receiver, int amount) {
        return new Transaction<S, R>(item, sender, receiver, amount) {
            @Override
            void doTransaction(Type type) {
            }
        };
    }

    /**
     * The callback after the transaction completes. Use this to send messages or send physical items.
     *
     * @param type the type of transaction occuring
     */
    abstract void doTransaction(Type type);

    /**
     * The item type being transacted
     *
     * @return the transacted item
     */
    public Object item() {
        return this.item;
    }

    /**
     * The sender of the transaction
     *
     * @return the transaction's sender
     */
    public S sender() {
        return this.sender;
    }

    /**
     * The receiver of the transaction
     *
     * @return the transaction's receiver
     */
    public R receiver() {
        return this.receiver;
    }

    /**
     * The amount of this transaction, always supposed to be positive.
     *
     * <p>This returns the absolute value of the amount, in case a withdrawl is thought to have a negative number.</p>
     *
     * @return the amount of the transaction
     */
    public int amount() {
        return this.amount.get();
    }

    /**
     * The transaction type
     */
    public enum Type {
        /**
         * The transaction represents a deposit
         */
        DEPOSIT,
        /**
         * The transaction represents a withdrawl
         */
        WITHDRAW
    }
}