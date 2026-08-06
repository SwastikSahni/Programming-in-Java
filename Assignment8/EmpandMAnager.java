class Employee {
    String name = "Swastik Sahni";
    double salary = 90000;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void display() {
        System.out.println("Name: " + name + " | Salary: $" + salary);
    }
}

class Manager extends Employee {
    double bonus;

    Manager(String name, double salary, double bonus) {
        super(name, salary); 
        this.bonus = bonus;
    }

    
    public void display() {
        super.display(); 
        System.out.println("Bonus: $" + bonus + " | Total: $" + (super.salary + bonus)); 
    }
}

public class EmpandMAnager {
    public static void main(String[] args) {
        Manager mgr = new Manager("Raghav Shimal", 85000, 15000);
        mgr.display();
    }
}