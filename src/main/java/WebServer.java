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
        while (requestCount < 5){
            client = this.listenForConnection();
            threadHandleRequest();
            requestCount++;
        }
    }
    public void threadHandleRequest(){
        // a server socket queues connection requests,
        // so we need a way of listening and distributing the connection details to threads
        thread_count++;
        int this_thread_id = thread_count;
        Runnable runServer = new RunServer(this_thread_id, client);
        new Thread(runServer).start();
        // this will spin up a new thread which will execute the request
    }

//    public void listenHandleCloseConnection(int thread_id) throws InterruptedException {
//        try {
//            client = this.listenForConnection();
//        } catch (IOException | InterruptedException e){
//            System.out.printf("An error occurred in thread %d while listening for a connection to the web server on port %d.", thread_id, this.port);
//            System.out.println(e);
//        }
//        if (client == null){
//            System.out.println("Client socket is null, skipping request.");
//            requestCount ++;
//        }
//        TimeUnit.SECONDS.sleep(3);
//
//        try {
//            this.handleRequest(client);
//        } catch (IOException e){
//            System.out.println("An error occurred handling the request.");
//            System.out.println(e);
//        }
//        try {
//            client.close();
//        } catch (IOException e){
//            System.out.println("An error occurred closing the connection.");
//            System.out.println(e);
//        }
//        requestCount++;
//    }

    Socket listenForConnection() throws IOException, InterruptedException {
            System.out.println("Listening for connection on port 80....");
            System.out.println();
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // thread gets here and just evaporates
            client = this.accept();
            System.out.println("Connection established, client socket: ");
            System.out.println(client);
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            return client;
    }

//    void handleRequest(Socket client) throws IOException {
//        // this function will handle the request from the client by instantiating a ServerIO object and calling the respondToConnection method
//        ServerIO serverIO = new ServerIO(client);
//        serverIO.handleRequest();
//    }

    public static void main(String[] args) throws IOException, InterruptedException {

        WebServer myServer = new WebServer(80);
        myServer.runServer();

        // this will not work because this check will run only once
        while (true){
            if (myServer.requestCount >= 5) {
                myServer.close();
                break;
            }
        }
    }
}
