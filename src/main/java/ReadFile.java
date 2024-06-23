import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadFile {

    final public static StringBuilder readFileAtPath(String path) throws FileNotFoundException {
        StringBuilder outputSB = new StringBuilder();

            //create new File object
            File fileToRead = new File(path);
            System.out.println("Reading file located at: ");
            System.out.println(fileToRead.getAbsolutePath());

            // create String from lines of file
            Scanner fileScanner = new Scanner(fileToRead);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                //System.out.println(data);
                outputSB.append(data);
            }
            fileScanner.close();
        return outputSB;
    };

    final public static void printFileAtPathToClient(String path, PrintWriter writerToClient) throws FileNotFoundException {
        //create new File object
        File fileToRead = new File(path);
        System.out.println("Reading file located at: ");
        System.out.println(fileToRead.getAbsolutePath());
        writerToClient.println("HTTP/1.1 200 OK\r\n\r\n");

        // create String from lines of file
        Scanner fileScanner = new Scanner(fileToRead);
        while (fileScanner.hasNextLine()) {
            String data = fileScanner.nextLine();
            //System.out.println(data);
            writerToClient.println(data);
        }
        fileScanner.close();
    };

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(ReadFile.readFileAtPath("./src/main/www/index.html"));
    }
}