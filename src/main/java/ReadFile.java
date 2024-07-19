import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

     public StringBuilder readFileAtPath(String path) throws FileNotFoundException {
        StringBuilder outputSB = new StringBuilder();
        File fileToRead = new File(path);
        Scanner fileScanner = new Scanner(fileToRead);

        while (fileScanner.hasNextLine()) {
            String data = fileScanner.nextLine();
            outputSB.append(data);
            outputSB.append("\n");
        }

        fileScanner.close();
        return outputSB;
    }
    public static void main(String[] args) throws FileNotFoundException {
         ReadFile readFile = new ReadFile();
        System.out.println(readFile.readFileAtPath("./src/main/www/index.html"));
    }
}