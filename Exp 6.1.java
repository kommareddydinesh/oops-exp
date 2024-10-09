<<< Exp-6 >>> 

BANK PROGRAM

package ex6;
import java.util.Scanner;

//Transaction interface defining basic banking operations
interface Transaction {
 void deposit(double amount);
 void withdraw(double amount);
 double viewBalance();
}

// Displayable interface to display account details
interface Displayable {
    void display();
}

//Abstract class Account holding account details
abstract class Account {
protected String accName;
protected String accNo;
protected double balance;

// Constructor
public Account(String accName, String accNo, double balance) {
   this.accName = accName;
   this.accNo = accNo;
   this.balance = balance;
}
}

class Bank extends Account implements Transaction, Displayable {
  // Constructor
  public Bank(String accName, String accNo, double balance) {
      super(accName, accNo, balance);
  }

  @Override
  public void deposit(double amount) {
      if (amount > 0) {
          balance += amount;
          System.out.println("Deposit successful! Current balance: " + balance);
      } else {
          System.out.println("Invalid deposit amount!");
      }
  }

  @Override
  public void withdraw(double amount) {
      if (amount > 0 && amount <= balance) {
          balance -= amount;
          System.out.println("Withdrawal successful! Current balance: " + balance);
      } else {
          System.out.println("Invalid withdrawal amount or insufficient funds!");
      }
  }

  @Override
  public double viewBalance() {
      return balance;
  }

  @Override
  public void display() {
      System.out.println("Account Name: " + accName);
      System.out.println("Account Number: " + accNo);
      System.out.println("Balance: " + balance);
  }
}




// Main class to perform menu-driven operations
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Creating an instance of the Bank class with initial values
        Bank bankAccount = new Bank("John Doe", "123456789", 1000.00);

        int choice;
        do {
            System.out.println("\n*** Banking Operations ***");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    bankAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    bankAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Current Balance: " + bankAccount.viewBalance());
                    break;
                case 4:
                    bankAccount.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
