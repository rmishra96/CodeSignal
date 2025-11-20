package com.codingsignaltest.bankingsystem.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankingSystem {
    private Map<String, Long> accountTransactionValues; // Stores total transaction value for each account

    public BankingSystem() {
        this.accountTransactionValues = new HashMap<>();
    }

    // Simulates a deposit transaction
    public void deposit(String accountId, long amount) {
        accountTransactionValues.merge(accountId, amount, Long::sum);
    }

    // Simulates a withdrawal transaction
    public void withdraw(String accountId, long amount) {
        accountTransactionValues.merge(accountId, amount, Long::sum);
    }

    // Simulates a transfer transaction (ignoring for now as per problem description)
    public void transfer(String fromAccountId, String toAccountId, long amount) {
        // For now, we'll just add the 'amount' to both accounts' total transaction value
        // as per the definition "sum of all transactions ... including ... transferred"
        accountTransactionValues.merge(fromAccountId, amount, Long::sum);
        accountTransactionValues.merge(toAccountId, amount, Long::sum);
    }

    /**
     * Returns the top n accounts with the highest total value of transactions in descending order.
     * The timestamp parameter is currently ignored as transaction history isn't maintained in this simplified model.
     *
     * @param timestamp An ignored timestamp (for future extensions).
     * @param n The number of top accounts to return.
     * @return A string representing an array of accounts and transaction values in the format "<accountId1>(<transactionValue1>)".
     */
    public String topActivity(long timestamp, int n) {
        // Create a list of map entries to sort
        List<Map.Entry<String, Long>> sortedAccounts = new ArrayList<>(accountTransactionValues.entrySet());

        // Sort accounts by transaction value in descending order
        sortedAccounts.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        // Get the top n accounts or all accounts if less than n exist
        List<Map.Entry<String, Long>> topNAccounts = sortedAccounts.stream()
                .limit(n)
                .collect(Collectors.toList());

        // Format the output string
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < topNAccounts.size(); i++) {
            Map.Entry<String, Long> entry = topNAccounts.get(i);
            result.append(entry.getKey()).append("(").append(entry.getValue()).append(")");
            if (i < topNAccounts.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();

        bankingSystem.deposit("accountA", 100);
        bankingSystem.withdraw("accountB", 50);
        bankingSystem.deposit("accountA", 200);
        bankingSystem.transfer("accountA", "accountC", 75);
        bankingSystem.deposit("accountD", 300);

        System.out.println(bankingSystem.topActivity(0, 2)); // Expected: [accountA(375), accountD(300)]
        System.out.println(bankingSystem.topActivity(0, 3)); // Expected: [accountA(375), accountD(300), accountC(75)]
        System.out.println(bankingSystem.topActivity(0, 5)); // Expected: [accountA(375), accountD(300), accountC(75), accountB(50)]
        System.out.println(bankingSystem.topActivity(0, 1)); // Expected: [accountA(375)]
    }
}
