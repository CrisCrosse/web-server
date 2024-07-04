import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class webServer extends ServerSocket {
    // this class is responsible for accepting connections from clients, and passing connection details to ServerIO
    // ? it will record how many successful requests it has handled and print this to the console after it shuts?
        private Socket client;
        final private int port;
        private int requestCount = 0;

    public webServer(int port) throws IOException {
        super(port);
        this.port = port;
    }

    public void acceptTenRequests(){
        // implement multi-threading to handle multiple requests at once and keep the server running until I send a close command

        while (requestCount < 10){
            try {
                client = this.acceptConnection();
            } catch (Exception e){
                // this block gets hit no matter which part of the code fails --> so this error message is not too helpful
                System.out.printf("An error occurred accepting a connection to the web server on port %d.%n", this.port);
                System.out.println(e);
            }
                // handle request? one function
            try {
                this.handleRequest(client);
            } catch (Exception e){
                System.out.println("An error occurred handling the request.");
                System.out.println(e);
            }
            try {
                client.close();
            } catch (Exception e){
                System.out.println("An error occurred closing the connection.");
                System.out.println(e);
            }
            requestCount++;
        }
    }

    private Socket acceptConnection() throws IOException, InterruptedException {
            System.out.println("Listening for connection on port 80....");
            System.out.println();

            client = this.accept();
            System.out.println("Connection established, client socket: ");
            System.out.println(client);
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            return client;
    }

    private void handleRequest(Socket client) throws IOException {
        // this function will handle the request from the client by instantiating a ServerIO object and calling the respondToConnection method
        BufferedReader takeInputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter writeOutToClient = new PrintWriter(client.getOutputStream(), true);

        ServerIO serverIO = new ServerIO(takeInputFromClient, writeOutToClient);
        serverIO.respondToConnection();
    }

    public static void main(String[] args) throws IOException {

        webServer myServer = new webServer(80);
        myServer.acceptTenRequests();
        myServer.close();


    }
}
