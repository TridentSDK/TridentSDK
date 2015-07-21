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

import net.tridentsdk.registry.Registry;

/**
 * Handles player transactions, such objects, items, and currency
 *
 * <p>This is designed around multiple accounts which are not really accounts, but really a system of synchronizing
 * transactions of a particular type. One account, for example, can be used for economy by one plugin, but that can
 * interfere with another plugin's economy. A new account can be made supporting the same types used in the other plugin
 * without interfering. Additionally, this has the advantage of being able to easily access another plugin's economy
 * data, useful for providing hooks.</p>
 *
 * <p>Example:
 * <pre><code>
 *     // Economy
 *     private static final Transactions handler = Registered.transactions();
 *
 *     // This must be static! Or you would end up with many different transactions
 *     // to the wrong account
 *     private static final int ECON_ID = handler.newAccount();
 *
 *     // Deposit
 *     handler.deposit(ECON_ID, new Transaction&lt;Player, Player&gt;(int.class, playerFrom, playerTo, 100) {
 *         &#64;Override public void doTransaction(Type type) {
 *             // If check is not required but is a good debugging safeguard
 *             if (type == Type.DEPOSIT) {
 *                 receiver().sendMessage(sender().name() + " has sent you $" + amount());
 *                 // You may give items here as well
 *             }
 *         }
 *     });
 *
 *     // Remove
 *     handler.withdraw(ECON_ID, new Transaction&lt;Player, Player&gt;(int.class, playerTo, playerTo, 100) {
 *         &#64;Override public void doTransaction(Type type) {
 *             // If check is not required but is a good debugging safeguard
 *             if (type == Type.WITHDRAW) {
 *                 sender().sendMessage("You have withdrawn $" + amount());
 *                 // You may give items here as well
 *             }
 *         }
 *     });
 *
 *     // Amount
 *     int amount = handler.amount(ECON_ID, playerTo, int.class);
 * </code></pre></p>
 *
 * <p>This class also contains global account IDs for economy and exchange [of items].</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface Transactions extends Registry<Transaction> {
    /**
     * Creates a new account from the internal account clock
     *
     * @return the new account ID used in transactions
     */
    int newAcount();

    /**
     * The global economy ID to be used as the transaction ID
     *
     * <p>This is just a recommendation to use for plugins. You do not need to use this.</p>
     *
     * @return the global economy transaction ID
     */
    int globalEconomy();

    /**
     * The global exchange ID to be used as the transaction ID
     *
     * <p>This is just a recommendation to use for plugins. You do not need to use this.</p>
     *
     * @return the global exchange transaction ID
     */
    int globalExchange();

    /**
     * Performs a transaction from the transaction's sender to the transaction's receiver
     *
     * @param account the account ID to be used
     * @param transaction the transaction to perform
     */
    void deposit(int account, Transaction transaction);

    /**
     * Withdraws an item from the account
     *
     * <p>IMPORTANT: The sender is the person withdrawing from the account. The receiver is not taken into
     * consideration, so that is allowed to be {@code null}.</p>
     *
     * @param account the account ID to be used
     * @param transaction the transaction to perform
     * @return {@code false} if the account for the withdrawer does not exist, {@code true} if the transaction completes
     *         successfully
     */
    boolean withdraw(int account, Transaction transaction);

    /**
     * Obtains the amount of an item as indicated by the amount field in each transaction
     *
     * @param account the account ID to be used
     * @param person the object which the transactions were sent to that is checked by this amounting
     * @param type the types to find the amount of. Also the parameter in
     *             {@link Transaction#item()}
     * @return the amount of an item of type {@code type} found the account of {@code person},
     *         or {@code Integer.MIN_VALUE} if the account for {@code person} does not exist, or the accound ID is not
     *         used for that person
     */
    int amount(int account, Object person, Object type);
}
