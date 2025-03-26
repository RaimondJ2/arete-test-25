package ee.taltech.iti0202.computerbuilder;

import ee.ttu.java.studenttester.annotations.TestContextConfiguration;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

@TestContextConfiguration(welcomeMessage = "This test will pass all the time, you should verify your code yourself!")
public class ComputerBuilderTest {

    @Test
    public void testDummyAlwaysTrue() {
        assertTrue(true);
    }
}

