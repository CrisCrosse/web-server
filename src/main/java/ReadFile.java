import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class ReadFile {

    final public static StringBuilder readFileAtPath(String path){
        StringBuilder outputSB = new StringBuilder();
        try{

            //create new File object
            File fileToRead = new File(path);
            System.out.println(fileToRead.getAbsolutePath());

            // create String from lines of file
            Scanner fileScanner = new Scanner(fileToRead);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                System.out.println(data);
                outputSB.append(data);
            }
            fileScanner.close();
        } catch (Exception e){
            System.out.println(e);
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return outputSB;
    };

    public static void main(String[] args) {
//        try {
//            File myObj = new File("filename.txt");
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                System.out.println(data);
//            }
//           v
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
        System.out.println(ReadFile.readFileAtPath("./src/main/www/index.html"));
    }
}