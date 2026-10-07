import java.util.Scanner;

public class ATM {

    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=================================================");
        System.out.println("             WELCOME TO JAVA ATM");
        System.out.println("=================================================");
        System.out.println("Hello, " + account.getAccountHolderName() + "!");

        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = readMenuChoice();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdraw();
                    break;
                case 4:
                    exit = true;
                    System.out.println("\nThank you for using Java ATM. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid option. Please choose a number between 1 and 4.");
            }
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println("-------------------------------------------------");
        System.out.print("Choose an option (1-4): ");
    }

    private int readMenuChoice() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.next();
            return -1;
        }
    }

    public void checkBalance() {
        System.out.printf("%nYour current balance is: $%.2f%n", account.getBalance());
    }

    private void handleDeposit() {
        System.out.print("\nEnter amount to deposit: $");
        double amount = readAmount();
        deposit(amount);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit failed: amount must be greater than $0.");
            return;
        }
        account.credit(amount);
        System.out.printf("Deposit successful! $%.2f has been added to your account.%n", amount);
        System.out.printf("New balance: $%.2f%n", account.getBalance());
    }

    private void handleWithdraw() {
        System.out.print("\nEnter amount to withdraw: $");
        double amount = readAmount();
        withdraw(amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal failed: amount must be greater than $0.");
            return;
        }
        if (amount > account.getBalance()) {
            System.out.println("Withdrawal failed: insufficient balance.");
            System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
            return;
        }
        account.debit(amount);
        System.out.printf("Withdrawal successful! $%.2f has been dispensed.%n", amount);
        System.out.printf("New balance: $%.2f%n", account.getBalance());
    }

    private double readAmount() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid amount. Please enter a valid number: $");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
