interface Taxable {
    double getTax();
}

class Product {
    String name;
    double price;

    Product(String name , double price)
    {
        this.name = name;
        this.price = price;
    }
}

class Electronic extends Product implements Taxable {
    Electronic(String name , double price){
        super(name , price);
    }

    public double getTax(){
        return price * 0.18;
    }
}

class Clothing extends Product implements Taxable {
    Clothing(String name , double price){
        super(name , price);
    }

    public double getTax(){
        return price * 0.05;
    }
}

class Grocery extends Product implements Taxable {
    Grocery(String name , double price){
        super(name , price);
    }

    public double getTax(){
        return price * 0.03;
    }
}


public class Ecommercestore {
    public static void main(String[] args) {
        Electronic lap = new Electronic("Laptop", 60000);
        Clothing cloth = new Clothing("Shirt", 1200);
        Grocery item = new Grocery("Orange", 2);

        System.out.println(lap.name + " Tax: $" + lap.getTax());
        System.out.println(cloth.name + " Tax: $" + cloth.getTax());
        System.out.println(item.name + " Tax: $" + item.getTax());
    }
    
}
