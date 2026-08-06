
interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    private double radius = 7.0;

    public double calculateArea(){
        return 3.14 * radius * radius;
    }
}

class Rectangle implements Shape {
    private double length = 7.0;
    private double breath = 4.0;

    public double calculateArea(){
        return length * breath;
    }
}
public class ShapeApp {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Rectangle Area: " + rectangle.calculateArea());

    }
    
}
