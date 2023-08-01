import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class OptionMenu {
    Scanner sc = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'₹'###,##0.00");
    HashMap<Integer, Account> data = new HashMap<Integer, Account>();

    public void getLogin() throws IOException {
        boolean end = false;
        int customerNumber = 0;
        int pinNumber = 0;
        while (!end) {
            try {
                System.out.print("\nEnter your customer number: ");
                customerNumber = sc.nextInt();
                System.out.print("\nEnter your PIN number: ");
                pinNumber = sc.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Account acc = (Account) pair.getValue();
                    if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
                        getAccountType(acc);
                        end = true;
                        break;
                    }
                }
                if (!end) {
                    System.out.println("\nWrong Customer Number or Pin Number");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Character(s). Only Numbers.");
            }
        }
    }

    public void getAccountType(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSelect the account you want to access: ");
                System.out.println(" Type 1 - Current Account");
                System.out.println(" Type 2 - Savings Account");
                System.out.println(" Type 3 - Exit");
                System.out.print("\nChoice: ");

                int selection = sc.nextInt();

                switch (selection) {
                    case 1:
                        getCurrent(acc);
                        break;
                    case 2:
                        getSaving(acc);
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }

    public void getCurrent(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCurrent Account: ");
                System.out.println(" Type 1 - View Balance");
                System.out.println(" Type 2 - Withdraw Funds");
                System.out.println(" Type 3 - Deposit Funds");
                System.out.println(" Type 4 - Transfer Funds");
                System.out.println(" Type 5 - Exit");
                System.out.print("\nChoice: ");

                int selection = sc.nextInt();

                switch (selection) {
                    case 1:
                        System.out.println("\nCurrent Account Balance: " + moneyFormat.format(acc.getCurrentBalance()));
                        break;
                    case 2:
                        acc.getCurrentWithdrawInput();
                        break;
                    case 3:
                        acc.getCurrentDepositInput();
                        break;

                    case 4:
                        acc.getTransferInput("Current");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }

    public void getSaving(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSavings Account: ");
                System.out.println(" Type 1 - View Balance");
                System.out.println(" Type 2 - Withdraw Funds");
                System.out.println(" Type 3 - Deposit Funds");
                System.out.println(" Type 4 - Transfer Funds");
                System.out.println(" Type 5 - Exit");
                System.out.print("Choice: ");
                int selection = sc.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("\nSavings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
                        break;
                    case 2:
                        acc.getsavingWithdrawInput();
                        break;
                    case 3:
                        acc.getSavingDepositInput();
                        break;
                    case 4:
                        acc.getTransferInput("Savings");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }

    public void createAccount() throws IOException {
        int cst_no = 0;
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nEnter your customer number ");
                cst_no = sc.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (!data.containsKey(cst_no)) {
                        end = true;
                    }
                }
                if (!end) {
                    System.out.println("\nThis customer number is already registered");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
        System.out.println("\nEnter PIN to be registered");
        int pin = sc.nextInt();
        data.put(cst_no, new Account(cst_no, pin));
        System.out.println("\n!!!Your new account has been successfuly registered!!!");
        System.out.println("\nRedirecting to login.............");
        getLogin();
    }

    public void mainMenu() throws IOException {
        data.put(016, new Account(016, 2407, 100000, 500000));
        data.put(123, new Account(123, 123, 20000, 50000));
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\n Type 1 - Login");
                System.out.println(" Type 2 - Create Account");
                System.out.print("\nChoice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        getLogin();
                        end = true;
                        break;
                    case 2:
                        createAccount();
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
        System.out.println("\n!!!!!!!!!!!!!!!!!Thank You for using this ATM!!!!!!!!!!!!!!!!!!!\n");
        sc.close();
        System.exit(0);
    }
}