public class wrapper {
    public static void main(String[] args) {
        String marks1 = "87";
        String marks2 = "92";
        String marks3 = "99";

        int mark1 = Integer.parseInt(marks1);
        int mark2 = Integer.parseInt(marks2);
        int mark3 = Integer.parseInt(marks3);

        int total = (mark1 + mark2 + mark3);

        System.out.println("Marks 1: " + mark1);
        System.out.println("Marks 2: " + mark2);
        System.out.println("Marks 1: " + mark3);
        System.out.println("Total Marks: " + total);
    }
    
}
