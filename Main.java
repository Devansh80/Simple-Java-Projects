import java.util.Scanner;

class Account {
    private String name;
    private String pin;
    private double balance;
    private String[] transactionHistory;
    private int transactionCount;

    public Account(String name, String pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new String[10]; // Assuming a maximum of 10 transactions
        this.transactionCount = 0;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("$" + amount + " deposited. New balance: $" + balance);
        addToTransactionHistory("Deposit: $" + amount);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else if (amount > 500) {
            System.out.println("Withdrawal limit exceeded.");
        } else {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn. New balance: $" + balance);
            addToTransactionHistory("Withdrawal: $" + amount);
        }
    }

    public void checkBalance() {
        System.out.println("Account balance: $" + balance);
    }

    public void checkHistory() {
        System.out.println("Transaction history:");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactionHistory[i]);
        }
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public String getPin() {
        return pin;
    }
    public String getName() {
        return name;
    }

    private void addToTransactionHistory(String transaction) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount++] = transaction;
        } else {
            System.out.println("Transaction history full. Unable to record further transactions.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create multiple accounts
        Account[] accounts = {
            new Account("Devansh", "1234", 1000),
            new Account("Atharv", "5678", 2000),
            new Account("Shaurya", "4321", 1500)
        };

        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        // Find the matching account
        Account account = null;
        for (Account acc : accounts) {
            if (acc.getPin().equals(enteredPin)) {
                account = acc;
                break;
            }
        }

        if (account == null) {
            System.out.println("Try again. Password was wrong.");
            scanner.close();
            return;
        }

        System.out.println("Welcome, " + account.getName() + "!");

        while (true) {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check balance");
            System.out.println("4. Check transaction history");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    account.checkHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (account.getTransactionCount() >= 10) {
                System.out.println("Transaction history limit reached.");
                break;
            }
        }

        scanner.close();
    }
}
