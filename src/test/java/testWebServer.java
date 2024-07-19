import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.*;

class testWebServer {
    @Test
    void testRunServer() throws IOException, InterruptedException {
        WebServer mockServer = spy(new WebServer(80));
        doNothing().when(mockServer).createThreadToHandleRequest();
        doReturn(new Socket()).when(mockServer).listenForConnection();

        mockServer.runServer();

        verify(mockServer, times(5)).listenForConnection();
        verify(mockServer, times(5)).createThreadToHandleRequest();
    }
}