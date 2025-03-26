package ee.taltech.iti0202.bookscraper;


import ee.ttu.java.studenttester.annotations.TestContextConfiguration;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

@TestContextConfiguration(welcomeMessage = "This test will pass all the time, you should verify your code yourself!")
public class BookScraperTest {

    @Test
    public void testDummyAlwaysTrue() {
        assertTrue(true);
    }
}

