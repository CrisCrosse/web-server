import java.io.IOException;
import java.net.Socket;

public class RunServer implements Runnable {
    int thread_id;
    Socket client;

    public RunServer(int thread_id, Socket client){
        this.thread_id = thread_id;
        this.client = client;
        System.out.println("Thread " + thread_id + " is starting");

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            System.out.printf("Thread %d was interrupted", thread_id);
            System.out.println(e);
        }

        try {
            this.handleRequest(client);
        } catch (IOException e) {
            System.out.printf("An error occurred handling the request in thread %d", thread_id);
            System.out.println(e);
        }

        try {
            System.out.printf("Closing the client connection in thread %d \n", thread_id);
            this.client.close();
        } catch (IOException e) {
            System.out.println("An error occurred closing the client connections");
            System.out.println(e);
        }

        System.out.println("Thread " + thread_id + " has finished");
    };

    void handleRequest(Socket client) throws IOException {
        // this function will handle the request from the client by instantiating a ServerIO object and calling the respondToConnection method
        ServerIO serverIO = new ServerIO(client);
        serverIO.handleRequest();
    }

    @Override
    public void run() {

    }
}
