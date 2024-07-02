import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class webServer extends ServerSocket {
        private Socket client;
        final private int port;

    public webServer(int port) throws IOException {
        super(port);
        this.port = port;
    }

    public void run(){
        try {
            this.acceptConnection();
            client.close();
        } catch (Exception e){
            System.out.printf("An error occurred accepting a connection to the web server on port %d.%n", this.port);
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

            BufferedReader takeInputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writeOutToClient = new PrintWriter(client.getOutputStream(), true);
        ServerIO serverIO = new ServerIO(takeInputFromClient, writeOutToClient);

            serverIO.respondToConnection();
    }

    public static void main(String[] args) throws IOException {

        webServer myServer = new webServer(80);
        myServer.run();
        myServer.close();


    }
}
