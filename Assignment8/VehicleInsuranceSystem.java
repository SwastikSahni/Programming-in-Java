class Vehicle {
    String model = "Tesla Model 3";
    double baseValue = 40000;

    Vehicle(String model, double baseValue) {
        this.model = model;
        this.baseValue = baseValue;
    }

    void displayInfo() {
        System.out.println("Model: " + model + " | Base Value: $" + baseValue);
    }
}

class CarInsurance extends Vehicle {
    double premiumRate = 0.05;

    CarInsurance(String model, double baseValue) {
        super(model, baseValue); 
    }

    void calculateAndDisplay() {
        super.displayInfo(); 
        double totalPremium = super.baseValue * premiumRate; 
        System.out.println("Insurance Premium (5%): $" + totalPremium);
    }
}

public class VehicleInsuranceSystem {
    public static void main(String[] args) {
        CarInsurance policy = new CarInsurance("BMW X5", 60000);
        policy.calculateAndDisplay();
    }
}