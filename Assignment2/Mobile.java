public class Mobile {
    int mobprice;
    String color;
    String model;
    int battery;
    Mobile(){
        mobprice = 20000;
        color = "White" ;
        model = "A36";
        battery = 6000;
    }
    Mobile(int p , String col , String mod , int bat){
        mobprice = p;
        color = col;
        model = mod;
        battery= bat;
    }
    Mobile(Mobile a){
        mobprice = a.mobprice;
        color= a.color;
        model=a.model;
        battery=a.battery;

    }
    void print(){
        System.out.println("Mobile Price:" + mobprice);
        System.out.println("Color:" + color);
        System.out.println("Model:" + model);
        System.out.println("BAttery:" + battery);

    }
    public static void main(String[] args) throws Exception {
        Mobile obj = new Mobile();
        obj.print();
        Mobile obj1 = new Mobile(30000, "Red", "M35" , 5000);
        obj1.print();
        

    }








    
}
