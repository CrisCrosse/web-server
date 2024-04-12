import java.io.IOException;
import java.net.*;

public class main {

    public static void main(String[] args) {
        try {
            //InetAddress localHost = InetAddress.getLocalHost();
            //System.out.println(localHost);

            //SocketAddress localAddress = new InetSocketAddress("127.0.0.1", 80);
            //server.bind(localAddress);
            //Socket localSocket = new Socket("127.0.0.1", 80);
            ServerSocket server = new ServerSocket(80);
            Thread newThread = new Thread(() -> {
                try {
                    Socket acceptedSocket = server.accept();
                    System.out.println("Waiting for connection");
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("connection established");
            });
            newThread.start();

            //System.out.println(acceptedSocket);
            //System.out.println(server);
            Socket client = new Socket("localHost", 80);
            //System.out.println(client);
            //System.out.println("connection established");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
