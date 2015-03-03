package net.tridentsdk.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.tridentsdk.docs.AccessNoDoc;

import java.util.List;
import java.util.Map;

@AccessNoDoc
class TransactionAudit {
    private final Map<Integer, List<Transaction>> audits = Maps.newHashMap();

    public void put(int account, Transaction transaction) {
        synchronized (audits) {
            List<Transaction> transactions = audits.get(account);
            if (transactions == null)
                transactions = Lists.newArrayList();

            transactions.add(transaction);
            audits.put(account, transactions);
        }
    }

    public List<Transaction> transactionsFor(int account) {
        synchronized (audits) {
            return audits.get(account);
        }
    }
}
