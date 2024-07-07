import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WebServer extends ServerSocket {
    // this class is responsible for accepting connections from clients, and passing connection details to ServerIO
    // ? it will record how many successful requests it has handled and print this to the console after it shuts?
        private Socket client;
        final private int port;
        private int requestCount = 0;

    public WebServer(int port) throws IOException {
        super(port);
        this.port = port;
    }

    public void acceptTenRequests(){
        // implement multi-threading to handle multiple requests at once and keep the server running until I send a close command

        while (requestCount < 10){

            // do i want all error handling to be handled here at the top level?
            // means that webServer knows if there was an exception --> useful for logging?
            // could log in lower level classes.
            // doing it here means it is all in one place, can throw errors elesewhere and catch here
            try {
                client = this.acceptConnection();
            } catch (IOException | InterruptedException e){
                System.out.printf("An error occurred accepting a connection to the web server on port %d.%n", this.port);
                System.out.println(e);
            }
            try {
                this.handleRequest(client);
            } catch (IOException e){
                System.out.println("An error occurred handling the request.");
                System.out.println(e);
            }
            try {
                client.close();
            } catch (IOException e){
                System.out.println("An error occurred closing the connection.");
                System.out.println(e);
            }
            requestCount++;
        }
    }

    Socket acceptConnection() throws IOException, InterruptedException {
            System.out.println("Listening for connection on port 80....");
            System.out.println();

            client = this.accept();
            System.out.println("Connection established, client socket: ");
            System.out.println(client);
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            return client;
    }

    void handleRequest(Socket client) throws IOException {
        // this function will handle the request from the client by instantiating a ServerIO object and calling the respondToConnection method
        ServerIO serverIO = new ServerIO(client);
        serverIO.handleRequest();
    }

    public static void main(String[] args) throws IOException {

        WebServer myServer = new WebServer(80);
        myServer.acceptTenRequests();
        myServer.close();


    }
}
