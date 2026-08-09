public class BankAccount {
    private final String accnumber;
    private double balance;

public BankAccount(String accnumber , double initalbalance) {
    this.accnumber = accnumber;
    this.balance = initalbalance;
}

public String getAccountNumber() {
    return accnumber;
}

public double getBalance() {
    return balance;
}

public void deposit(double amount) {
    balance += amount;
}

public void withdraw(double amount){
    if(amount <= balance)
    {
        balance -= amount;
    }
    else {
        System.out.println("Insufficient Balance");
    }
}

public static void main(String[] args) {
    BankAccount acc = new BankAccount("BC23413", 20000);

    acc.deposit(700.0);
    acc.withdraw(200.0);

    System.out.println(("Account Number: " + acc.getAccountNumber()));
    System.out.println(("Final Number: " + acc.getBalance()));
    
}
}


