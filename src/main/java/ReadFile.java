import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

     public static StringBuilder readFileAtPath(String path) throws FileNotFoundException {
        StringBuilder outputSB = new StringBuilder();

        try {
            File fileToRead = new File(path);
            Scanner fileScanner = new Scanner(fileToRead);

            System.out.println("Reading file located at: ");
            System.out.println(fileToRead.getAbsolutePath());

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                outputSB.append(data);
                outputSB.append("\n");
            }

            fileScanner.close();
        } catch (Exception e) {
            throw new FileNotFoundException("Filepath requested is not valid");
        }
        return outputSB;
    }
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(ReadFile.readFileAtPath("./src/main/www/index.html"));
    }
}