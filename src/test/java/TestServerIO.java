import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.*;

class TestServerIO {
    @Test
    void testHandleRequestHappyPath() throws IOException {
        // when respond to connection is called with a valid request, it should return the file at the endpoint

        // this function actually tests the parsing of the input from the client, the logic of handling "/" requests,
        // that readfile is called with the correct path, and client output is called with the correct arguements

        Socket mockClient = mock(Socket.class);
        BufferedReader stubbedBufferedReader = mock(BufferedReader.class);
        PrintWriter stubbedPrintWriter = mock(PrintWriter.class);
        ReadFile stubbedReadFile = mock(ReadFile.class);

        String validRequest = "GET / HTTP/1.1";
        StringBuilder fileContents = new StringBuilder("File contents");
        ServerIO serverIOspy = spy(new ServerIO(mockClient));

        doReturn(stubbedBufferedReader).when(serverIOspy).getTakeInputFromClient();
        doReturn(stubbedPrintWriter).when(serverIOspy).getWriteOutToClient();
        doReturn(stubbedReadFile).when(serverIOspy).getFileReader();
        doReturn(validRequest).when(stubbedBufferedReader).readLine();
        doReturn(fileContents).when(stubbedReadFile).readFileAtPath("./src/main/www/index.html");

        serverIOspy.handleRequest();

        verify(stubbedPrintWriter).println("HTTP/1.1 200 OK\r\n\r\n");
        verify(stubbedPrintWriter).println(fileContents);

    }
    @Test
    void testRespondToConnectionParseFailure(){

    }
    @Test
    void testRespondToConnectionFileFailure(){

    }
}