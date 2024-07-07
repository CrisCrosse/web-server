import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.*;

class WebServerTest {

    @Test
    void testAcceptTenRequests() throws IOException, InterruptedException {
        // ideally would not have to make these private methods package private just for the sake of testing?

        WebServer mockServer = spy(new WebServer(80));
        doNothing().when(mockServer).handleRequest(any());
        doReturn(new Socket()).when(mockServer).acceptConnection();

        mockServer.acceptTenRequests();

        verify(mockServer, times(10)).acceptConnection();
        verify(mockServer, times(10)).handleRequest(any());
    }
}