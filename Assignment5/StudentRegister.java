import java.util.*;

public class StudentRegister {
    public static void main(String[] args) {
        
        int flag = 0;
        ArrayList<String> regis = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);

        while (flag != 1) {
            System.out.println("Press 1 to add a course. Press 2 to remove a course. Press 3 to view course's Press 4 to exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.println("Name of Course you want to add");
                String newc = sc.next();
                regis.add(newc);

                System.out.println("Updated Courselist is:");
                for (int i = 0; i < regis.size(); i++) {
                    StringBuffer s = new StringBuffer();
                    s.append(regis.get(i));
                    System.out.println("course " + (i + 1) + " :" + s);
                }
            } else if (ch == 2) {
                System.out.println("Name of Course you want to remove");
                String remc = sc.next();
                regis.remove(remc);

                System.out.println("Updated Courselist is:");
                for (int i = 0; i < regis.size(); i++) {
                    StringBuffer s = new StringBuffer();
                    s.append(regis.get(i));
                    System.out.println("course " + (i + 1) + " :" + s);
                }
            } else if (ch == 3) {
                System.out.println("Final Courselist is:");
                for (int i = 0; i < regis.size(); i++) {
                    StringBuffer s = new StringBuffer();
                    s.append(regis.get(i));
                    System.out.println("course " + (i + 1) + " :" + s);
                }
            } else if (ch == 4) {
                flag = 1;
            }
        }
        sc.close();
    }
}