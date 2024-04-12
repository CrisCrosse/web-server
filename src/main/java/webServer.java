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



            out.println("HTTP/1.1 200 OK\r\n\r\n You have established a connection with Chris' Web Server");
            System.out.println(in.readLine());

            while (in.readLine() != null){
                out.println("hi client");
                System.out.println(in.readLine());
            }

            server.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
