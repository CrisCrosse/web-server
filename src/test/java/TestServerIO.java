import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.*;

class TestServerIO {
    @Test
    void testHandleRequestHappyPath() throws IOException {
        // when respond to connection is called with a valid request, it should:
        // 1. read the request from the client
        // 2. parse the request
        // 3. Call ReadFile with the correct path
        // 4. Write out to the client with a 200 OK status
        // 5. Write out the file contents to the client

        BufferedReader stubbedBufferedReader = mock(BufferedReader.class);
        PrintWriter stubbedPrintWriter = mock(PrintWriter.class);
        ReadFile stubbedReadFile = mock(ReadFile.class);
        ServerIO serverIOspy = spy(new ServerIO(new Socket()));

        String validRequest = "GET / HTTP/1.1";
        StringBuilder fileContents = new StringBuilder("File contents");

        doReturn(stubbedBufferedReader).when(serverIOspy).getTakeInputFromClient();
        doReturn(stubbedPrintWriter).when(serverIOspy).getWriteOutToClient();
        doReturn(stubbedReadFile).when(serverIOspy).getFileReader();

        doReturn(validRequest).when(stubbedBufferedReader).readLine();
        doReturn(fileContents).when(stubbedReadFile).readFileAtPath("./src/main/www/index.html");

        serverIOspy.readAndRespondToRequest();

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