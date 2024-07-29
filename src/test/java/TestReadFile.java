import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TestReadFile {
    @Test
    void testReadFileAtPath() throws IOException {
        // when readFileAtPath is called with a valid path
        ReadFile readFileSpy = spy(new ReadFile());
        StringBuilder outputSB;
        StringBuilder testFileContents = new StringBuilder("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Test Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>This is a test page</h1>\n" +
                "    <p>It is being served by a Java web server.</p>\n" +
                "</body>\n" +
                "</html>");

        String folderPath = "./src/test/www";
        String endpoint = "/testIndex.html";

        outputSB = readFileSpy.readFileAtPath(folderPath + endpoint);

        assertEquals(outputSB.toString(), testFileContents.toString());
    }
    @Test
    void testTryingToAccessSomethingOutsideWWW() throws IOException {

        ReadFile readFileSpy = spy(new ReadFile());
        StringBuilder outputSB;

        String folderPath = "./src/test/www";
        String endpoint = "/../testNaughtyIndex.html";

        try {
            outputSB = readFileSpy.readFileAtPath(folderPath + endpoint);
        } catch (FileNotFoundException e) {
            assertEquals("./src/test/www/../testNaughtyIndex.html (No such file or directory)", e.getMessage());
        }
    }
}