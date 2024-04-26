import java.io.*;
import java.net.*;

public class webServer {
    public static void main(String[] args) throws IOException {

        try {

            ServerSocket server = new ServerSocket(80);

            // listen for a connection, once received set up a socket with the remote address + port of the client
            Socket clientSocket = server.accept();
            System.out.println(clientSocket);

            // set up a reader of input from the connection
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // set up a writer to send data across the new connection
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // respond to connection and output to console the request
            out.println("HTTP/1.1 200 OK\r\n\r\n You have established a connection with Chris' Web Server");


            // parse the HTTP request for the desired endpoint
            String input = in.readLine();
            System.out.println(input);
            String[] splitInput = input.split(" ");

            out.println("Requested path: " + splitInput[1]);


            server.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
