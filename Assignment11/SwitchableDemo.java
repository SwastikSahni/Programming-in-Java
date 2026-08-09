interface Switchable {
    void turnOn();
}

class Light implements Switchable {
    public void turnOn() {
        System.out.println("Light is ON");
    }
}

class Fan implements Switchable {
    public void turnOn() {
        System.out.println("Fan is ON");
    }
}

public class SwitchableDemo {
    public static void main(String[] args) {
        Switchable light = new Light();
        Switchable fan = new Fan();

        light.turnOn();
        fan.turnOn();
    }
}
