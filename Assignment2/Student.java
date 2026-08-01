public class Student {
    int rollno;
    String name;
    Student(){
        rollno = 22;

    }
    Student(int p , String n){
        rollno = p;
        name = n;

    }
    Student(String n){
        name = n;
    }
    Student(Student a){
        rollno = a.rollno;
        name = a.name;
    }
    void print(){
        System.out.println("Name:" + name);
        System.out.println("PRN:" + rollno);

    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Student obj = new Student();
        obj.print();
        Student obj1 = new Student(1 , "Swastik");
        obj1.print();
        Student obj2 = new Student( "Swastik");
        obj2.print();
        Student obj3 = new Student(obj1);
        obj3.print();
        Student obj4 = new Student(obj);
        obj4.print();

    }
}
