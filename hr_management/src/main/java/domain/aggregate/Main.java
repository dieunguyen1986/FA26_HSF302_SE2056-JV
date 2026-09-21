package domain.aggregate;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = BankAccount.open("999999", new BigDecimal(1000));

        // Call service
        // Domain Service

        // Map to entity
        // Call repo


        bankAccount.withdraw(new BigDecimal(2000));


    }
}
