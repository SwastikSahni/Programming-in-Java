class Calc {
    public static int calcount = 0;

    public int add(int a , int b)
    {
        calcount++;
        return a + b;
    }
    
    public int add(int a , int b , int c)
    {
        calcount++;
        return a + b + c;
    }

    public double add(double a , double b)
    {
        calcount++;
        return a + b;
    }

    public double add(double a , double b , double c)
    {
        calcount++;
        return a + b + c;
    }

    public int getcalcount(){
        return calcount;
    }
}

public class Calculator {
    public static void main(String[] args) {
        Calc c = new Calc();

        int sumint2 = c.add(10, 20);
        System.out.println("Addition of two integers (10 + 20): " + sumint2);

        int sumint3 = c.add(10 , 15 , 20);
        System.out.println("Addition of three integers (10 + 15 + 20): " + sumint3);

        double sumdoub2 = c.add(23.2, 12.7);
        System.out.println("Addition of 2 decimal values (23.2 + 12.7): " + sumdoub2);

        double sumdoub3 = c.add(15.3 , 20.2 , 40.6);
        System.out.println("Addition of 3 decimal values (15.3 + 20.2 + 40.6): " + sumdoub3);

        System.out.println("Total calculations performed: " + c.getcalcount());
    }
}