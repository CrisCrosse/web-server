import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class webServer extends ServerSocket {
        private Socket client;
        private BufferedReader takeInputFromClient;
        private PrintWriter writeOutToClient;
        private int port;

    public webServer(int port) throws IOException {
        super(port);
        this.port = port;
    }

    public void run(){
        try {
            this.acceptConnection();
        } catch (Exception e){
            System.out.println(
                    String.format("An error occurred accepting a connection to the web server on port %d.", this.port)
            );
            System.out.println(e);
            System.out.println("Closing connection ...");
        }
        try {
            this.handleRequest();
        } catch (Exception e){
            System.out.println(
                    String.format("An error occurred handling a request to the web server on port %d.", this.port)
            );
            System.out.println(e);
            System.out.println("Closing connection ...");
        }
    }

    private void acceptConnection() throws IOException, InterruptedException {
            System.out.println("Listening for connection on port 80....");
            System.out.println();

            client = this.accept();
            System.out.println("Connection established, client socket: ");
            System.out.println(client);
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            this.takeInputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.writeOutToClient = new PrintWriter(client.getOutputStream(), true);
    }

    private void handleRequest() throws IOException {
        // pull out the reading of the request into a separate function, pass endpoint in to handle request
        // in top level function have conditional if, only if GET call ReadFile

        String request = this.takeInputFromClient.readLine();
        System.out.println("Request received: ");
        String[] splitInput = request.split(" ");
        String retrievalPath = splitInput[1];
        String requestType = splitInput[0];
        String httpVersion = splitInput[2];
        System.out.println("Request type: " + requestType);
        System.out.println("Requested path: " + retrievalPath);
        System.out.println("HTTP version: " + httpVersion);
        System.out.println();

        // make a function that only handles the errors for if a file is found or not -->
        // better to defensively program and not throw the errors?
        if (requestType.equals("GET")){
            try {
                ReadFile.printFileAtPathToClient("./src/main/www" + retrievalPath, this.writeOutToClient);
//                StringBuilder fileResponse = ReadFile.readFileAtPath("./src/main/www" + retrievalPath);
//                this.writeOutToClient.println("HTTP/1.1 200 OK\r\n\r\n " + fileResponse);
                client.close();

            } catch (Exception e){
                this.writeOutToClient.println("HTTP/1.1 404 Not Found\r\n\r\n");
                System.out.println("An error occurred.");
                this.writeOutToClient.println("Invalid file path requested. Try /index.html");
                System.out.println(e);
                this.writeOutToClient.println("Closing connection ...");
                System.out.println("Closing client connection ...");
                client.close();
            }
        }
    }






    public static void main(String[] args) throws IOException {

        webServer myServer = new webServer(80);
        myServer.run();
        myServer.close();


//
//        try {
//
//            ServerSocket server = new ServerSocket(80);
//
//            while (true){
//
//                // listen for a connection, once received set up a socket with the remote address + port of the client
//                System.out.println("Listening for connection on port 80....");
//                System.out.println();
//
//                Socket clientSocket = server.accept();
//                System.out.println("Connection established, client socket: ");
//                System.out.println(clientSocket);
//                System.out.println();
//                TimeUnit.SECONDS.sleep(1);
//
//                // set up a reader of input from the connection
//                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                // set up a writer to send data across the new connection
//                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//                // respond to connection and output to console the request --> remove when I have logic to handle requests
//                out.println("HTTP/1.1 200 OK\r\n\r\n You have established a connection with Chris' Web Server");
//
//                // parse the HTTP request for the desired endpoint
//                String input = in.readLine();
//                System.out.println("Request received: ");
//                String[] splitInput = input.split(" ");
//                String retrievalPath = splitInput[1];
//                String requestType = splitInput[0];
//                String httpVersion = splitInput[2];
//                System.out.println("Request type: " + requestType);
//                System.out.println("Requested path: " + retrievalPath);
//                System.out.println("HTTP version: " + httpVersion);
//                System.out.println();
//                TimeUnit.SECONDS.sleep(1);
//
//
//                // call file reader func on requested path
//                if (requestType.equals("GET")){
//                    out.println("Requested path: " + splitInput[1] + "\nRequest type: " + requestType);
//                    out.println();
//                    out.println("File contents: ");
//                    out.println();
//
//                    try {
//                        StringBuilder fileResponse = ReadFile.readFileAtPath("./src/main/www" + retrievalPath);
//                        out.println(fileResponse);
//                        clientSocket.close();
//                    } catch (Exception e){
//                        out.println("An error occurred.");
//                        System.out.println("An error occurred.");
//                        out.println(e);
//                        System.out.println(e);
//                        out.println("Closing connection ...");
//                        System.out.println("Closing connection ...");
//                        server.close();
//                        break;
//                    }
//                }
//            }
//
//
//        } catch (Exception e){
//            System.out.println("An error occurred in the top level try/catch block. ");
//            System.out.println(e);
//        }
    }
}
