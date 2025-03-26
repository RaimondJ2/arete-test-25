package ee.taltech.iti0202.bookapi;

import ee.ttu.java.studenttester.annotations.TestContextConfiguration;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

@TestContextConfiguration(welcomeMessage = "This test will fail all the time, you should verify your code yourself!")
public class BookApiTest {

    @Test
    public void testDummyAlwaysFails() {
        assertTrue(false);
    }
}
