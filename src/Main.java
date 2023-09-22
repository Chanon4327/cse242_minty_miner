import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user input for file path
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();
        
        try {
            // Create a FileReader and BufferedReader
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line;
            
            // Read and print from the file line by line
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Close the readers
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
