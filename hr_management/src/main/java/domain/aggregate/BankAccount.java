package domain.aggregate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private BigDecimal balance;
    private List<Transaction> transactions = new ArrayList<>();
    private Status status;

    private BankAccount() {

    }

    public static BankAccount open(String accountNumber, BigDecimal balance) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.accountNumber = accountNumber;
        bankAccount.balance = balance;
        bankAccount.status = Status.ACTIVE;

        return bankAccount;
    }

    public void frozen() {
        if (this.status == Status.FROZEN) {
            throw new IllegalStateException("Cannot frozen this bank account.");
        }
        this.status = Status.FROZEN;
    }

    public void withdraw(BigDecimal amount) {
        if (status == Status.FROZEN) {
            throw new IllegalStateException("Tài khoả̉n bị đóng băng");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Số dư không đủ");
        }

        this.balance = balance.subtract(amount);
        transactions.add(Transaction.withdraw(amount));
    }

    public void deposit(BigDecimal amount) {
        if (status == Status.FROZEN || status == Status.CLOSED) {
            throw new IllegalArgumentException("Your account must be active");
        }

        if (amount.compareTo(new BigDecimal(500)) < 0) {
            throw new RuntimeException("Amount must be >=500");
        }

        this.balance = balance.add(amount);
        this.transactions.add(transactions.get(transactions.size() - 1));

    }

}

class Transaction {
    public static Transaction withdraw(BigDecimal amount) {
        return new Transaction();
    }
}

enum Status {
    ACTIVE,
    FROZEN,
    CLOSED
}