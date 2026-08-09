abstract class Payment{
    double amount;


public Payment(double amount) {
    this.amount = amount;
}

public abstract void processPayment();
}

class CreditCardPayment extends Payment{
    String cardnumber;

    public CreditCardPayment(double amount , String cardnumber) {
        super(amount);
        this.cardnumber = cardnumber;
    }

    public void processPayment() {
        System.out.println("Paid $ " + amount + "\nVia Credit Card: " + cardnumber);

    }
}

class UPIPayment extends Payment{
    String upiID;

    public UPIPayment(double amount , String upiID) {
        super(amount);
        this.upiID = upiID;
    }

    public void processPayment() {
        System.out.println("Paid $" + amount + "Via UPI: " + upiID);
    }
}

public class AllPayments {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment(15000, "2398-46534");
        Payment p2 = new UPIPayment(200, "user@2341");

        p1.processPayment();
        p2.processPayment();
    }
}
