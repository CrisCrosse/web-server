import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
public class testWebServer {

    @Test
    public void testWebServerCreation() throws IOException {
        // web server creation is currently doing too much, creating socket, accepting and closing connection

        webServer testServer = new webServer(80);
        assertNotNull(testServer);
    }

    @Test
    public void testWebServerAcceptsConnection() throws IOException {
        webServer testServer = new webServer(80);
        //HTTP request
        //assertNotNull(testServer.client);
    }

    public void testWebServerHandlesValidRequest() throws IOException {
        webServer testServer = new webServer(80);
        //HTTP request to correct endpoint

        // assert that the 200 response is sent along with the correct HTML file
        //assertNotNull(testServer.takeInputFromClient);
    }

    public void testWebServerHandlesInvalidRequest() throws IOException {
        webServer testServer = new webServer(80);
        //HTTP request to incorrect endpoint

        // assert that the 404 response is sent along
        //assertNotNull(testServer.takeInputFromClient);
    }


    @Test
    public void testReadFileReturnsWholeHTMLFile() throws IOException {
        StringBuilder testFile = ReadFile.readFileAtPath("./src/test/www/testIndex.html");
        //System.out.println(testFile.toString());
        // readfile as testFile is not formatted nicely? can this be passed through as a string?
        assertEquals("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <title>Test Page</title>" +
                "</head>" +
                "<body>" +
                "    <h1>This is a test page</h1>" +
                "    <p>It is being served by a Java web server.</p>" +
                "</body>" +
                "</html>", testFile.toString());
    }
}
