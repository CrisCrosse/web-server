import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class webServer {
    public static void main(String[] args) throws IOException {

        try {

            ServerSocket server = new ServerSocket(80);

            // listen for a connection, once received set up a socket with the remote address + port of the client
            System.out.println("Listening for connection on port 80....");
            System.out.println();

            Socket clientSocket = server.accept();
            System.out.println("Connection established, client socket: ");
            System.out.println(clientSocket);
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            // set up a reader of input from the connection
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // set up a writer to send data across the new connection
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // respond to connection and output to console the request
            out.println("HTTP/1.1 200 OK\r\n\r\n You have established a connection with Chris' Web Server");

            // parse the HTTP request for the desired endpoint
            String input = in.readLine();
            System.out.println("Request received: ");
            String[] splitInput = input.split(" ");
            String retrievalPath = splitInput[1];
            String requestType = splitInput[0];
            String httpVersion = splitInput[2];
            System.out.println("Request type: " + requestType);
            System.out.println("Requested path: " + retrievalPath);
            System.out.println("HTTP version: " + httpVersion);

            // call file reader func on requested path
            if (requestType.equals("GET")){
                out.println("Requested path: " + splitInput[1] + "\nRequest type: " + requestType);
            }


            server.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
