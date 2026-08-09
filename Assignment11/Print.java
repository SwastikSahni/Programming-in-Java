interface Printable{
    void print();
}

class Student implements Printable {
    String name;

    Student(String name){
        this.name = name;
    }

    public void print(){
        System.out.println("Student Name: " + name);
    }
}

class Employee implements Printable {
    String name;

    Employee(String name){
        this.name = name;
    }

    public void print(){
        System.out.println("Employee Name: " + name);
    }
}

public class Print{
    public static void main(String[] args) {
        Printable stu = new Student("Swastik");
        Printable emp = new Employee("Parth");

        stu.print();
        emp.print();
    }
}
