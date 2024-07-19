import java.io.*;
import java.net.Socket;

public class ServerIO {
    final private Socket client;
    private BufferedReader takeInputFromClient;
    private PrintWriter writeOutToClient;
    private ReadFile fileReader;

    public ServerIO(Socket client) throws IOException {
        this.client = client;
    }

    // a function which creates the input and output streams with the client, parses the request, then responds to the connection
    public void readAndRespondToRequest() throws IOException {
        createInputAndOutputStreamsWithClient();
        createFileReader();
        String endpoint = parseRequest();
        respondToConnection(endpoint);
    }
    // seems the below 2 functions should be in the constructor
    // but that makes it hard to test because I want to instantiate a spy initially and then stub these
    // methods to set mocks as the things that take the input and output of logic from the class
    // if they are in the constructor then it automatically creates the input and output streams before i can mock it
    public void createInputAndOutputStreamsWithClient() throws IOException {
        this.takeInputFromClient = getTakeInputFromClient();
        this.writeOutToClient = getWriteOutToClient();
    }
    public void createFileReader() throws IOException {
        this.fileReader = getFileReader();
    }
    public BufferedReader getTakeInputFromClient() throws IOException {
        return new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
    public PrintWriter getWriteOutToClient() throws IOException {
        return new PrintWriter(client.getOutputStream(), true);
    }
    public ReadFile getFileReader() {
        return new ReadFile();
    }

    public void respondToConnection(String endpoint) throws IOException {
        // error handling transmission to the client must be done in this class, but errors are passed up to WebServer to be handled for the main server
        try {
            StringBuilder file = fileReader.readFileAtPath("./src/main/www" + endpoint);
            writeOutToClient.println("HTTP/1.1 200 OK\r\n\r\n");
            writeOutToClient.println(file);
        } catch (FileNotFoundException e) {
            writeOutToClient.println("HTTP/1.1 404 Not Found\r\n\r\n");
            writeOutToClient.println("File not found");
            throw new FileNotFoundException("File not found at requested endpoint");
        }
    }

    private String parseRequest() throws IOException {
        String request = this.takeInputFromClient.readLine();
        String[] splitInput = request.split(" ");
        String requestType = splitInput[0];
        String retrievalPath = splitInput[1];

        if (requestType.equals("GET")) {
            if (retrievalPath.equals("/")) {
                retrievalPath = "/index.html";
            }
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
