import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class WebServer extends ServerSocket {
    // this class is responsible for accepting connections from clients, and passing connection details to ServerIO
    // ? it will record how many successful requests it has handled and print this to the console after it shuts?
        private Socket client;
        final private int port;
        private int requestCount = 0;
        public int thread_count = 0;

    public WebServer(int port) throws IOException {
        super(port);
        this.port = port;
    }

    public void runServer() throws IOException, InterruptedException {
        // this function will run the server and accept connections
        System.out.printf("Listening for connections on port %d....", this.port);
        System.out.println();
        while (requestCount < 5) {
            client = this.listenForConnection();
            threadHandleRequest();
            requestCount++;
        }
        this.close();
    }
    public void threadHandleRequest(){
        // this function creates a thread and passes the client to it to handle the request
        thread_count++;
        int this_thread_id = thread_count;
        Runnable runServer = new RunServer(this_thread_id, client);

        new Thread(runServer).start();
    }

    Socket listenForConnection() throws IOException, InterruptedException {
            client = this.accept();

            System.out.println("Connection established, client socket: ");
            System.out.println(client);

            return client;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WebServer myServer = new WebServer(80);
        myServer.runServer();
    }
}
