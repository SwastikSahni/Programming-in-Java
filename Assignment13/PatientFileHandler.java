import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PatientFileHandler {
    public static void main(String[] args) {
        File file = new File("patient.txt");
        FileWriter writer = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        
        try {
            writer = new FileWriter(file);
            writer.write("Patient ID: P101, Name: Parth Somrani, Age: 37, Diagnosis: Hypertension\n");
            writer.write("Patient ID: P102, Name: Siddharth Sisodhia, Age: 35, Diagnosis: Diabetes\n");
            System.out.println("Patient details saved successfully.\n");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error closing writer.");
            }
        }

        
        System.out.println("Reading Patient Details");
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader.");
            }
        }
    }
}