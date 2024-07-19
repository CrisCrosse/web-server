import java.io.IOException;
import java.net.Socket;

public class RunServer implements Runnable {
    // this class manages error handling within the thread and instantiates
    // the serverIO object to respond to the client connection passed down to the thread
    int thread_id;
    Socket client;

    public RunServer(int thread_id, Socket client){
        this.thread_id = thread_id;
        this.client = client;
    };

    void handleRequest(Socket client) throws IOException {
        ServerIO serverIO = new ServerIO(client);
        serverIO.readAndRespondToRequest();
    }

    @Override
    public void run() {
        System.out.println("Thread " + thread_id + " is starting");
        System.out.println();

        try {
            Thread.sleep(3 * 1000);
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

        System.out.println("Thread " + thread_id + " has finished \n");
    }
}
