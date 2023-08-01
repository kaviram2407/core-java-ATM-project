import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.*;

public class Account {

    private int customerNumber;
    private int pinNumber;
    private double currentBalance = 0;
    private double savingBalance = 0;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'₹'###,##0.00");

    public Account(int customerNumber, int pinNumber) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
    }

    public Account(int customerNumber, int pinNumber, double currentBalance, double savingBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.currentBalance = currentBalance;
        this.savingBalance = savingBalance;
    }
    public int getCustomerNumber(){
        return customerNumber;
    }
    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }
    public int getPinNumber() {
        return pinNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public double calccurrentWithdraw(double amount) {
        currentBalance = (currentBalance - amount);
        return currentBalance;
    }

    public double calcSavingWithdraw(double amount) {
        savingBalance = (savingBalance - amount);
        return savingBalance;
    }

    public double calcCurrentDeposit(double amount) {
        currentBalance = (currentBalance + amount);
        return currentBalance;
    }

    public double calcSavingDeposit(double amount) {
        savingBalance = (savingBalance + amount);
        return savingBalance;
    }

    public void calccurrentTransfer(double amount) {
        currentBalance = currentBalance - amount;
        savingBalance = savingBalance + amount;
    }

    public void calcSavingTransfer(double amount) {
        savingBalance = savingBalance - amount;
        currentBalance = currentBalance + amount;
    }

    public void getCurrentWithdrawInput() {
        boolean end = false;
        while (!end) {
            try {
               System.out.println("\nCurrent Current Account Balance: " + moneyFormat.format(currentBalance));
                System.out.print("\nAmount you want to withdraw from Current Account: ");
                double amount = input.nextDouble();
                if ((currentBalance - amount) >= 0 && amount >= 0) {
                    calccurrentWithdraw(amount);
                    System.out.println("\n current Account Balance: " + moneyFormat.format(currentBalance));
                    end = true;
                } else {
                    System.out.println("\nInsufficient Amount In Your Account");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getsavingWithdrawInput() {
        boolean end = false;
        while (!end) {
            try {
               System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                System.out.print("\nAmount you want to withdraw from Savings Account: ");
                double amount = input.nextDouble();
                if ((savingBalance - amount) >= 0 && amount >= 0) {
                    calcSavingWithdraw(amount);
                    System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                    end = true;
                } else {
                    System.out.println("\nInsufficient Amount In Your Account");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getCurrentDepositInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCurrent current Account Balance: " + moneyFormat.format(currentBalance));
                System.out.print("\nAmount you want to deposit from Current Account: ");
                double amount = input.nextDouble();
                if ((currentBalance + amount) >= 0 && amount >= 0) {
                    calcCurrentDeposit(amount);
                    System.out.println("\nCurrent Current Account Balance: " + moneyFormat.format(currentBalance));
                    end = true;
                } else {
                    System.out.println("\nInsufficient Amount In Your Account");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getSavingDepositInput() {
        boolean end = false;
        while (!end) {
            try {
               System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                System.out.print("\nAmount you want to deposit into your Savings Account: ");
                double amount = input.nextDouble();

                if ((savingBalance + amount) >= 0 && amount >= 0) {
                    calcSavingDeposit(amount);
                    System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                    end = true;
                } else {
                    System.out.println("\nInsufficient Amount In Your Account");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getTransferInput(String accType) {
        boolean end = false;
        while (!end) {
            try {
                if (accType.equals("Current")) {
                    System.out.println("\nSelect an account you wish to tranfers funds to:");
                    System.out.println("1. Savings");
                    System.out.println("2. Exit");
                    System.out.print("\nChoice: ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\nCurrent Current Account Balance: " + moneyFormat.format(currentBalance));
                            System.out.print("\nAmount you want to deposit into your Savings Account: ");
                            double amount = input.nextDouble();
                            if ((savingBalance + amount) >= 0 && (currentBalance - amount) >= 0 && amount >= 0) {
                                calccurrentTransfer(amount);
                                System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                                System.out.println(
                                        "\nCurrent Current Account Balance: " + moneyFormat.format(currentBalance));
                                end = true;
                            } else {
                                System.out.println("\nInsufficient Amount In Your Account");
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\nInvalid Choice.");
                            break;
                    }
                } else if (accType.equals("Savings")) {
                    System.out.println("\nSelect an account you wish to tranfers funds to: ");
                    System.out.println("1. Current");
                    System.out.println("2. Exit");
                    System.out.print("\nChoice: ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                            System.out.print("\nAmount you want to deposit into your savings account: ");
                            double amount = input.nextDouble();
                            if ((currentBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0) {
                                calcSavingTransfer(amount);
                                System.out.println("\nCurrent current account balance: " + moneyFormat.format(currentBalance));
                                System.out.println("\nCurrent savings account balance: " + moneyFormat.format(savingBalance));
                                end = true;
                            } else {
                                System.out.println("\nInsufficient Amount In Your Account.");
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\nInvalid Choice.");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }
}