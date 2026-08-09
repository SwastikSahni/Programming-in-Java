// File path: src/Main.java
import College.Student.Student;
import College.Faculty.Faculty;

public class SANDEP {
    public static void main(String[] args) {
        Student s = new Student("Swastik", 202);
        Faculty f = new Faculty("Dr. Raghav", "Computer Science");

        s.displayStudentDetails();
        f.displayFacultyDetails();
    }
}