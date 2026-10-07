public class ATMApplication {

    public static void main(String[] args) {

        BankAccount account = new BankAccount("Harsh Kumar", "ACC10234", 5000.00);

        ATM atm = new ATM(account);

        atm.start();
    }
}
