import java.util.*;
public class Todolist {
    public static void main(String args[]){
        ArrayList<String> tasks=new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of tasks:");
        int size= sc.nextInt();
        sc.nextLine();
        for (int i=1;i<=size;i++){
            System.out.println("Enter task:");
            String task= sc.nextLine();
            tasks.add(task);
        }
        
        System.out.println("Tasks:");
        for (int i=0;i<tasks.size();i++){
            StringBuffer s= new StringBuffer();
            s.append(tasks.get(i));
            System.out.println("Task "+(i+1)+": "+s);
            
        }
       sc.close();
    }
    
}