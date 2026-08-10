import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeFileHandler {
    public static void main(String[] args) {
        File file = new File("employee.txt");
        FileWriter writer = null;
        FileReader filereader = null;
        BufferedReader bufferReader = null;

        try {
            writer = new FileWriter(file);
            writer.write("EMP ID: Swastik Sahni , Dept: IT\n");
            writer.write("EMP ID: Raghav Mukherjee , Dept: Sales\n");
            System.out.println("Employye details Saved Successfully.\n");
        }
        catch (IOException e) {
            System.out.println("Error writing into the file");
        }
        finally {
            try{
                if(writer != null) writer.close();
            }
            catch (IOException e){
                System.out.println("Error closing the writer.");
            }
        }
        System.out.println("Reading Employee Details");
        try{
            filereader = new FileReader(file);
            bufferReader = new BufferedReader(filereader);
            String line;

            while ((line = bufferReader.readLine()) != null) {
                System.out.println(line);
        }
    }
    catch (IOException e){
        System.out.println("Error reading from file.");
        } finally {
            try {
                if (bufferReader != null) bufferReader.close();
                if (filereader != null) filereader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader.");
            }
        }
    }
}