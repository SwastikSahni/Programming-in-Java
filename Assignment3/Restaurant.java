class Billing {
    public static int totalOrders = 0;

    public double calculateBill(double itemTotal) {
        totalOrders++;
        return itemTotal;
    }

    public double calculateBill(double itemTotal, double packagingFee) {
        totalOrders++;
        return itemTotal + packagingFee;
    }

    public double calculateBill(double itemTotal, double packagingFee, double deliveryCharge) {
        totalOrders++;
        return itemTotal + packagingFee + deliveryCharge;
    }

    public int getTotalOrders() {
        return totalOrders;
    }
}

public class Restaurant {
    public static void main(String[] args) {
        Billing r = new Billing();

        double dineIn = r.calculateBill(500.0);
        System.out.println("Dine-in Bill: " + dineIn);

        double takeaway = r.calculateBill(500.0, 30.0);
        System.out.println("Takeaway Bill: " + takeaway);

        double delivery = r.calculateBill(500.0, 30.0, 50.0);
        System.out.println("Delivery Bill: " + delivery);

        System.out.println("Total orders: " + r.getTotalOrders());
    }
}