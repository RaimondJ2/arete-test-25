package ee.taltech.iti0202.hashcode;


import ee.ttu.java.studenttester.annotations.Gradable;
import ee.ttu.java.studenttester.annotations.TestContextConfiguration;
import ee.ttu.java.studenttester.enums.ReportMode;
import org.junit.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@TestContextConfiguration(mode = ReportMode.VERBOSE)
public class HashCodeTest {
    @Test(timeOut = 1000)
    public void testPointEquals() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        Point p3 = new Point(1, 3);
        assertNotEquals(p1, p3);
        assertNotEquals(p2, p3);
    }

    @Test(timeOut = 1000)
    public void testPersonEquals_SameValue() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("Ago", "von", "Luberg", 20);
        assertEquals(person1, person2);
    }

    @Test(timeOut = 1000)
    public void testPersonEquals_EmptyMiddleName() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("Ago", "", "Luberg", 20);
        assertEquals(person1, person2);
        Person person3 = new Person("Ago", "der", "Luberg", 20);
        assertNotEquals(person1, person3);
        assertEquals(person2, person3);
    }

    @Test(timeOut = 1000)
    public void testPersonEquals_FirstNameLetter() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("A", "von", "Luberg", 20);
        assertEquals(person1, person2);
    }

    @Test(timeOut = 1000)
    public void testPersonEquals_SameDecade() {
        Person person1 = new Person("Ago", "von", "Luberg", 21);
        Person person2 = new Person("Ago", "von", "Luberg", 25);
        Person person3 = new Person("Ago", "von", "Luberg", 29);
        Person person4 = new Person("Ago", "von", "Luberg", 30);
        assertEquals(person1, person2);
        assertEquals(person1, person3);
        assertEquals(person2, person3);
        assertNotEquals(person2, person4);
        assertNotEquals(person1, person4);
        assertNotEquals(person3, person4);
    }

    @Test(timeOut = 1000)
    public void testPersonEquals_Combinations() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("A", "", "Luberg", 20);
        Person person3 = new Person("A", "von", "Luberg", 29);
        Person person4 = new Person("A", "", "Luberg", 29);
        assertEquals(person1, person2);
        assertEquals(person1, person3);
        assertEquals(person1, person4);
        assertEquals(person2, person3);
        assertEquals(person2, person4);
        assertEquals(person3, person4);
    }

    @Test(timeOut = 1000)
    public void testPersonHashCode_SameValue() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("Ago", "von", "Luberg", 20);
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test(timeOut = 1000)
    public void testPersonHashCode_EmptyMiddleName() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("Ago", "", "Luberg", 20);
        assertEquals(person1.hashCode(), person2.hashCode());
        Person person3 = new Person("Ago", "der", "Luberg", 20);
        assertEquals(person2.hashCode(), person3.hashCode());
    }

    @Test(timeOut = 1000)
    public void testPersonHashCode_FirstNameLetter() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("A", "von", "Luberg", 20);
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test(timeOut = 1000)
    public void testPersonHashCode_SameDecade() {
        Person person1 = new Person("Ago", "von", "Luberg", 21);
        Person person2 = new Person("Ago", "von", "Luberg", 25);
        Person person3 = new Person("Ago", "von", "Luberg", 29);
        assertEquals(person1.hashCode(), person2.hashCode());
        assertEquals(person1.hashCode(), person3.hashCode());
        assertEquals(person2.hashCode(), person3.hashCode());
    }

    @Test(timeOut = 1000)
    public void testPersonHashCode_Combinations() {
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("A", "", "Luberg", 20);
        Person person3 = new Person("A", "von", "Luberg", 29);
        Person person4 = new Person("A", "", "Luberg", 29);
        assertEquals(person1.hashCode(), person2.hashCode());
        assertEquals(person1.hashCode(), person3.hashCode());
        assertEquals(person1.hashCode(), person4.hashCode());
        assertEquals(person2.hashCode(), person3.hashCode());
        assertEquals(person2.hashCode(), person4.hashCode());
        assertEquals(person3.hashCode(), person4.hashCode());
    }
    @Test(timeOut = 1000)
    @Gradable(weight = 4)
    public void testPersonHashCode_DifferentDecadeShouldGiveDifferentHash_ForPerformance() {
        Person person1 = new Person("Ago", "von", "Luberg", 21);
        Person person2 = new Person("Ago", "von", "Luberg", 25);
        Person person3 = new Person("Ago", "von", "Luberg", 29);
        Person person4 = new Person("Ago", "von", "Luberg", 30);
        assertNotEquals(person2.hashCode(), person4.hashCode());
        assertNotEquals(person1.hashCode(), person4.hashCode());
        assertNotEquals(person3.hashCode(), person4.hashCode());
    }
}
