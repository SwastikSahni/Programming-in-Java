abstract class FoodOrder {
    double itemPrice;

    public FoodOrder(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public abstract double calculateBill();
}

class DineInOrder extends FoodOrder {
    double serviceCharge = 50.0;

    public DineInOrder(double itemPrice) {
        super(itemPrice);
    }

    
    public double calculateBill() {
        return itemPrice + serviceCharge;
    }
}

class TakeAwayOrder extends FoodOrder {
    double packagingFee = 20.0;

    public TakeAwayOrder(double itemPrice) {
        super(itemPrice);
    }

    
    public double calculateBill() {
        return itemPrice + packagingFee;
    }
}

public class Allorders {
    public static void main(String[] args) {
        FoodOrder order1 = new DineInOrder(250.0);
        FoodOrder order2 = new TakeAwayOrder(250.0);

        System.out.println("Dine-In Total Bill: $" + order1.calculateBill());
        System.out.println("Take-Away Total Bill: $" + order2.calculateBill());
    }
}