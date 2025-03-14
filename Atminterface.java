import java.util.Scanner;

class BankAccount {
    private String name;
    private String userName;
    private String password;
    private float balance = 100000f;
    private int transactions = 0;
    private String transactionHistory = "";

    public void register(Scanner sc) {
        System.out.print("\nEnter Your Name: ");
        this.name = sc.nextLine();
        System.out.print("Enter Your Username: ");
        this.userName = sc.nextLine();
        System.out.print("Enter Your Password: ");
        this.password = sc.nextLine();
        System.out.println("\nRegistration completed. Kindly login.");
    }

    public boolean login(Scanner sc) {
        System.out.print("\nEnter Your Username: ");
        String inputUsername = sc.nextLine();
        if (inputUsername.equals(userName)) {
            System.out.print("Enter Your Password: ");
            String inputPassword = sc.nextLine();
            if (inputPassword.equals(password)) {
                System.out.println("\nLogin successful!!");
                return true;
            } else {
                System.out.println("\nIncorrect Password");
            }
        } else {
            System.out.println("\nUsername not found");
        }
        return false;
    }

    public void withdraw(Scanner sc) {
        System.out.print("\nEnter amount to withdraw: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Consume newline
        if (amount > 0 && balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("\nWithdraw Successful");
            transactionHistory += amount + " Rs Withdrawn\n";
        } else {
            System.out.println("\nInsufficient Balance or Invalid Amount");
        }
    }

    public void deposit(Scanner sc) {
        System.out.print("\nEnter amount to deposit: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Consume newline
        if (amount > 0 && amount <= 100000f) {
            transactions++;
            balance += amount;
            System.out.println("\nSuccessfully Deposited");
            transactionHistory += amount + " Rs Deposited\n";
        } else {
            System.out.println("\nInvalid Amount or Deposit Limit Exceeded");
        }
    }

    public void transfer(Scanner sc) {
        System.out.print("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.print("Enter amount to transfer: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Consume newline
        if (amount > 0 && balance >= amount && amount <= 50000f) {
            transactions++;
            balance -= amount;
            System.out.println("\nSuccessfully Transferred to " + recipient);
            transactionHistory += amount + " Rs transferred to " + recipient + "\n";
        } else {
            System.out.println("\nInsufficient Balance or Transfer Limit Exceeded");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs");
    }

    public void transHistory() {
        System.out.println(transactions == 0 ? "\nNo Transactions Yet" : "\nTransaction History:\n" + transactionHistory);
    }

    public String getName() {
        return name;
    }
}

public class AtmInterface {
    public static int takeIntegerInput(Scanner sc, int limit) {
        int input = -1;
        while (input < 1 || input > limit) {
            System.out.print("\nEnter Your Choice: ");
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine(); // Consume newline
            } else {
                sc.next(); // Consume invalid input
                System.out.println("Invalid input, please enter a number.");
            }
            if (input < 1 || input > limit) {
                System.out.println("Invalid choice, please select a number between 1 and " + limit);
            }
        }
        return input;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n********** WELCOME TO SBI ATM SYSTEM **********\n");
            System.out.println("1. Register \n2. Exit");
            int choice = takeIntegerInput(sc, 2);

            if (choice == 1) {
                BankAccount b = new BankAccount();
                b.register(sc);
                while (true) {
                    System.out.println("\n1. Login \n2. Exit");
                    int ch = takeIntegerInput(sc, 2);
                    if (ch == 1 && b.login(sc)) {
                        System.out.println("\n********** WELCOME BACK, " + b.getName() + " **********\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            int c = takeIntegerInput(sc, 6);
                            switch (c) {
                                case 1 -> b.withdraw(sc);
                                case 2 -> b.deposit(sc);
                                case 3 -> b.transfer(sc);
                                case 4 -> b.checkBalance();
                                case 5 -> b.transHistory();
                                case 6 -> isFinished = true;
                            }
                        }
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                System.exit(0);
            }
        }
    }
}