import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerIO {

    final private BufferedReader takeInputFromClient;
    final private PrintWriter writeOutToClient;

    public ServerIO(BufferedReader takeInputFromClient, PrintWriter writeOutToClient) {
        this.takeInputFromClient = takeInputFromClient;
        this.writeOutToClient = writeOutToClient;
    }

    public void respondToConnection() throws IOException {
        String endpoint = parseRequest();
        try {
            StringBuilder file = ReadFile.readFileAtPath("./src/main/www" + endpoint);
            writeOutToClient.println("HTTP/1.1 200 OK\r\n\r\n");
            writeOutToClient.println(file);
        } catch (IOException e) {
            writeOutToClient.println("HTTP/1.1 404 Not Found\r\n\r\n");
            writeOutToClient.println("File not found \r\n\r\n");
        }
    }

    private String parseRequest() throws IOException {
        String request = this.takeInputFromClient.readLine();
        String[] splitInput = request.split(" ");
        String retrievalPath = splitInput[1];
        String requestType = splitInput[0];

        if (requestType.equals("GET")) {
            return retrievalPath;
        } else {
            writeOutToClient.println("HTTP/1.1 404 Not Found\r\n\r\n");
            writeOutToClient.println("Invalid request type \r\n\r\n");
            throw new IOException("Invalid request type");
        }
    }
    public static void main(String[] args) {
    }
}
