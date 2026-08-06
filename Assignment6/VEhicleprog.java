class VehicleA {
    private String name = "Audi Q8";
    private int year = 2018;

    // Inner Class
    class Details {
        void display() {
            System.out.println("Vehicle: " + name + " | Year: " + year);
        }
    }
}

interface VehicleAction {
    void performAction();
}

public class VEhicleprog {
    public static void main(String[] args) {
        // Inner Class
        VehicleA car = new VehicleA();
        VehicleA.Details details = car.new Details();
        details.display();

        
        VehicleAction action = new VehicleAction() {
            public void performAction() {
                System.out.println("Vehicle is ready and driving!");
            }
        };
        action.performAction();
    }
}