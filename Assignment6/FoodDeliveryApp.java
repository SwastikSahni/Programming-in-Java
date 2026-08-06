class Order {
    private String foodItem = "Chicken Pizza";
    private double price = 12.99;

    class Details {
        void display() {
            System.out.println("Order: " + foodItem + " | Total: $" + price);
        }
    }
}

interface DeliveryStatus {
    void updateStatus();
}

public class FoodDeliveryApp {
    public static void main(String[] args) {
        Order order = new Order();
        Order.Details details = order.new Details();
        details.display();

        DeliveryStatus status = new DeliveryStatus() {
            public void updateStatus() {
                System.out.println("Delivery Status: Out for delivery with driver!");
            }
        };
        status.updateStatus();
    }
}
