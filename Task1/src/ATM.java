import java.util.Scanner;

public class ATM {
    private double balance;
    private int pin;
    private Scanner scanner;

    public ATM(double initialBalance, int initialPin, Scanner scanner) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.scanner = scanner;
    }

    private boolean authenticateUser() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter your PIN: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter numeric PIN.");
                scanner.next();
                continue;
            }
            int enteredPin = scanner.nextInt();
            if (enteredPin == pin) {
                System.out.println("Authentication Successful.\n");
                return true;
            } else {
                System.out.println("Invalid PIN. Try again.\n");
                attempts++;
            }
        }
        return false;
    }

    private void showMenu() {
        int choice;
        do {
            System.out.println("========== ATM Menu ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                System.out.print("Choose an option: ");
            }
            choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please choose again.\n");
            }
        } while (choice != 5);
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " +  balance + " rupees.\n");
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next();
            System.out.print("Enter amount to deposit: ");
        }
        double depositAmount = scanner.nextDouble();
        if (depositAmount > 0) {
            balance += depositAmount;
            System.out.printf("Deposited $%.2f successfully.\n\n", depositAmount);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.\n");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next();
            System.out.print("Enter amount to withdraw: ");
        }
        double withdrawalAmount = scanner.nextDouble();
        if (withdrawalAmount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.\n");
        } else if (withdrawalAmount > balance) {
            System.out.println("Insufficient funds. Transaction cancelled.\n");
        } else {
            balance -= withdrawalAmount;
            System.out.printf("Withdrawn $%.2f successfully.\n\n", withdrawalAmount);
        }
    }

    private void changePin() {
        System.out.print("Enter your current PIN: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter numeric PIN.");
            scanner.next();
            return;
        }
        int currentPin = scanner.nextInt();
        if (currentPin != pin) {
            System.out.println("Incorrect current PIN.\n");
            return;
        }
        System.out.print("Enter new PIN: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter numeric PIN.");
            scanner.next();
            System.out.print("Enter new PIN: ");
        }
        int newPin = scanner.nextInt();
        System.out.print("Confirm new PIN: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter numeric PIN.");
            scanner.next();
            System.out.print("Confirm new PIN: ");
        }
        int confirmPin = scanner.nextInt();
        if (newPin == confirmPin) {
            pin = newPin;
            System.out.println("PIN successfully changed.\n");
        } else {
            System.out.println("PIN mismatch. PIN not changed.\n");
        }
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM setup!");
        System.out.print("Enter initial balance: ");
        while (!inputScanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            inputScanner.next();
            System.out.print("Enter initial balance: ");
        }
        double initialBalance = inputScanner.nextDouble();
        System.out.print("Enter initial PIN: ");
        while (!inputScanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter numeric PIN.");
            inputScanner.next();
            System.out.print("Enter initial PIN: ");
        }
        int initialPin = inputScanner.nextInt();
        ATM atm = new ATM(initialBalance, initialPin, inputScanner);
        System.out.println("\nPlease insert your card (press Enter to continue)...");
        inputScanner.nextLine();
        inputScanner.nextLine();
        if (atm.authenticateUser()) {
            atm.showMenu();
        } else {
            System.out.println("Too many incorrect attempts. Your card has been blocked.");
        }
        inputScanner.close();
    }
}
