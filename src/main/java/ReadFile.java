import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    final public static StringBuilder readFileAtPath(String path) throws FileNotFoundException {
        StringBuilder outputSB = new StringBuilder();
//        try{

            //create new File object
            File fileToRead = new File(path);
            System.out.println("Reading file located at: ");
            System.out.println(fileToRead.getAbsolutePath());

            // create String from lines of file
            Scanner fileScanner = new Scanner(fileToRead);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                System.out.println(data);
                outputSB.append(data);
            }
            fileScanner.close();
//        } catch (Exception e){
//            System.out.println(e);
//            System.out.println("An error occurred within ReadFile.");
//            e.printStackTrace();
//        }
        return outputSB;
    };

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(ReadFile.readFileAtPath("./src/main/www/index.html"));
    }
}