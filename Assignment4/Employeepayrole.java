import java.util.Scanner;

public class Employeepayrole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        String idStr = sc.nextLine();

        System.out.print("Enter Basic Salary: ");
        String salaryStr = sc.nextLine();

        System.out.print("Enter Bonus Amount: ");
        String bonusStr = sc.nextLine();

        Integer empId = Integer.valueOf(idStr);
        Double basicSalary = Double.valueOf(salaryStr);
        Double bonus = Double.valueOf(bonusStr);

        if (basicSalary <= 0 || bonus < 0) {
            System.out.println("Invalid salary or bonus amount.");
        } else {
            Double netSalary = Double.valueOf(basicSalary.doubleValue() + bonus.doubleValue());

            System.out.println("Employee ID: " + empId);
            System.out.println("Basic Salary: " + basicSalary);
            System.out.println("Bonus: " + bonus);
            System.out.println("Net Salary: " + netSalary);
        }

        sc.close();
    }
}