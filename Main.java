import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Bank_account{
    private String accountName;
    private double accountID;
    private double balance;

    public String getAccountName(){
        return accountName;
    }
    
    public double getAccountID(){
        return accountID;
    }
    
    public double getBalance(){
        return balance;
    }

    public void setBalance(){ 
        //1 is for true, meaning you want to add
        System.out.println("Type 1 for deposit and 0 for withdrawl");
        int mode = Main.input_int_Validation();
        if(mode!=1 && mode!=0){
            System.out.println("You did not enter a valid value of 1 or 0.");
            return; // return is he so that the program will exit the method for invalid input
        }
        if(mode==1){
           deposit();
        }
        else if(mode==0){
          withdrawl();
        }
    }
    
    public void deposit(){
        System.out.println("How much do you wish to deposit");
        double amount = Main.input_doable_Validation();
        if(amount<0){
            System.out.println("Deposited amount can't be negative");
            return;
        }
        
        System.out.printf("Adding %.2f \n", amount);
        this.balance = getBalance() + amount;
        System.out.printf("The account's new balance is: %.2f \n", this.balance);
    }

   public void withdrawl(){
        System.out.println("How much do you wish to withdrawl");
        double amount = Main.input_doable_Validation();
        if(amount<0){
            System.out.println("Deposited amount can't be negative");
            return;
        }

        if(amount>this.balance){ // Cant' withdrawl more than what's in your account
            System.out.println("This is imposible Brochacho: Your'e trying to scam me");
        }
        else if(amount<=this.balance ){
            System.out.printf("withdrawing %.2f \n", amount);
            this.balance = getBalance() - amount;
            System.out.printf("The account's new balance is: %.2f \n", this.balance);
        }
    }

    public Bank_account(String accountName, double accountID, double balance){ // constructor
        this.accountName = accountName;
        this.accountID = accountID;
        this.balance = balance;
    }

    public static Bank_account accountCreation(){
    System.out.println("What's the name of the account");
    String name = Main.input_String_Validation();
    Main.input.nextLine();

    double id = 977 + Math.random();
    System.out.println("Your accounts password is: " + id);
    
    System.out.println("What's the amount you will be depositing for your initial balance");
    double amount = Main.input_doable_Validation();
    if(amount<0){
            System.out.println("Deposited amount can't be negative");
            System.out.println("The balance will be set to zero which can be adjusted in the future");
            amount = 0.0;
        }
    Bank_account newAccount = new Bank_account(name, id, amount); //automatically creates new objects. 
    //Objects haing the same variable name does not matter as the variable is just a pointer

    System.out.println("Will automatically add this new account to the system");
    return newAccount; // returns an object that's type is bank account
                    // The instance of the Bank class bank is not avaliable

    }

    @Override
    public String toString(){
       System.out.println();
        return "The name of this account is: " + accountName + "\nThe account's ID is: " +
                accountID + "\nThe accounts balance is: " + balance + "\n";
    }
}

class Bank{
ArrayList<Bank_account> list_ofBank_accounts = new ArrayList<Bank_account>();
Bank_account template = new Bank_account("Blank", 977.0, 0.00);

public void addAccount (Bank_account account ){
    System.out.println("Adding account to the list");
    list_ofBank_accounts.add(account);
}

public void removeAccount (Bank_account account ){
    list_ofBank_accounts.remove(account);
}

public Bank_account findAccount (double specific_accountID){
    
    for(Bank_account account : list_ofBank_accounts){ 
        if (account.getAccountID() == specific_accountID){
            System.out.println("Found the account it was:");
            System.out.println(account.toString());
            return account;
        }    
    }
    System.out.println("Could not find an account. Instead you will be accesing the template account");
    return template;
}

public void displayAll_accounts (){ 
    System.out.println();
    for(Bank_account account : list_ofBank_accounts){ 
    System.out.println(account.toString());
    }
}

}

public class Main {
    public static Scanner input = new Scanner(System.in);
    
    public static double input_doable_Validation(){
        
        while (true) { // thx chat for the true
                try{ 
                double amount = Main.input.nextDouble();
                return  amount; 
            } 
            catch(InputMismatchException e){
                System.out.println("Please enter a valid decimal number");
                System.err.println(e);
                Main.input.nextLine(); // thx for the rec
            }
        }
    
    }

    public static int input_int_Validation(){ 

        
        while (true) { // thx chat for the true
                try{ 
                int amount = Main.input.nextInt();
                return  amount; 
            } 
            catch(InputMismatchException e){
                System.out.println("Please enter a valid integer");
                System.err.println(e);
                Main.input.nextLine(); // thx for the rec
            }
        }
    
    }

        public static String input_String_Validation(){ 
        
        while (true) { // thx chat for the true
                try{ 
                String amount = Main.input.next();
                return  amount; 
            } 
            catch(InputMismatchException e){
                System.out.println("Please enter a valid String of letters or numbers");
                System.err.println(e);
                Main.input.nextLine(); // thx for the rec
            }
        }
    
    }

    public static void main (String[] args){
        Bank bank = new Bank();

        Bank_account selectorAccount = new Bank_account("Blank", 977.0, 0.00);
        
        boolean bool = true;
        while (bool) {
            System.out.println();
            if(bank.list_ofBank_accounts.size()==0){
            System.out.println("Please create an account before you can do anything");
            }
            else if(bank.list_ofBank_accounts.size()>0){
            System.out.println("Please enter the ID/password of your account so that you can access it");
             double ID = Main.input_doable_Validation();
            selectorAccount = bank.findAccount(ID);
            }
            
            System.out.println();
            System.out.println("Please enter a 0,1,2,3,4,5, or 6");
            System.out.println(
            "0: is for creating an account\n" +
            "1: is for removing an account\n" +
            "2: is for withdrawing/depositing \n" +
            "3: is for checking balance \n" +
            "4: is for your account details \n" +
            "5: is for finding if account is in our system \n" +
            "6: is for Displaying all accounts \n" +
            "7: is for exiting the program :( ");
            int mode = Main.input_int_Validation();

            switch (mode) {
                case(0) :
                    System.out.println("Creating an account");
                    Bank_account account = Bank_account.accountCreation();
                    bank.addAccount(account);
                    break;
                 case(1) :
                    if(bank.list_ofBank_accounts.size()>0){ // This line is needed so that none of the code runs without having an account
                    System.out.println("Removing an account");
                    bank.removeAccount(selectorAccount);
                    }
                    else{System.out.println("There are zero accounts in our system. Please create one");}
                    break;
                 case(2) :
                    if(bank.list_ofBank_accounts.size()>0){
                    System.out.println("Heading to the withdrawing/Depositing program");
                    selectorAccount.setBalance();
                    }
                    else{System.out.println("There are zero accounts in our system. Please create one");}
                    break;
                case(3) :
                    if(bank.list_ofBank_accounts.size()>0){
                    System.out.println("Here's your balance");
                    System.out.println(selectorAccount.getBalance());
                    }
                    else{System.out.println("There are zero accounts in our system. Please create one");}
                    break;
                case(4) :
                    if(bank.list_ofBank_accounts.size()>0){
                    System.out.println("Here's the details to your accounts");
                    selectorAccount.toString();
                    }
                    else{System.out.println("There are zero accounts in our system. Please create one");}
                    break;
                case(5) :
                    if(bank.list_ofBank_accounts.size()>0){
                    System.out.println("Finding account");
                    bank.findAccount(selectorAccount.getAccountID());
                    }
                    else{System.out.println("There are zero accounts in our system. Please create one");}
                    break;
                case(6) :
                    if(bank.list_ofBank_accounts.size()>0){
                    System.out.println("Displaying all accounts");
                    bank.displayAll_accounts();
                    }
                    else{System.out.println("There are zero accounts in our system. Please create one");}
                    break;
                case(7) :
                    System.out.println("Exiting");
                    bool = false;
                    break;
                default:
                    System.out.println("You did not enter a proper value for the menue");
                    break;
            }
        }
      

    }

}
