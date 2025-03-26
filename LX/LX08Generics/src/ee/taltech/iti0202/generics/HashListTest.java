package ee.taltech.iti0202.generics;

import ee.ttu.java.studenttester.annotations.TestContextConfiguration;
import ee.ttu.java.studenttester.enums.ReportMode;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.testng.Assert.*;

@TestContextConfiguration(mode = ReportMode.VERBOSE, identifier = 2)
public class HashListTest {
    class StrangeInteger {
        private int number;
        public StrangeInteger(int number) {
            this.number = number;
        }
        @Override
        public int hashCode() {
            return number % 100;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StrangeInteger that = (StrangeInteger) o;
            return number == that.number;
        }
    }
    @Test(timeOut = 1000)
    public void testPrivateFieldsAndArrayUsage() {
        var fields = HashList.class.getDeclaredFields();
        for (var f : fields) {
            assertFalse(f.getType().isAssignableFrom(ArrayList.class));
            assertFalse(f.getType().isAssignableFrom(List.class));
            assertFalse(f.getType().isAssignableFrom(LinkedList.class));
            if (Modifier.isStatic(f.getModifiers())) {
                // static final - constant
                assertTrue(Modifier.isFinal(f.getModifiers()));
            } else {
                // non static has to be private
                assertTrue(Modifier.isPrivate(f.getModifiers()));
            }
        }
    }

    @Test(timeOut = 1000)
    public void testPrivateFieldsAndMapUsage() {
        var fields = HashList.class.getDeclaredFields();
        for (var f : fields) {
            assertFalse(f.getType().isAssignableFrom(HashMap.class));
            assertFalse(f.getType().isAssignableFrom(Map.class));
            if (Modifier.isStatic(f.getModifiers())) {
                // static final - constant
                assertTrue(Modifier.isFinal(f.getModifiers()));
            } else {
                // non static has to be private
                assertTrue(Modifier.isPrivate(f.getModifiers()));
            }
        }
    }

    @Test(timeOut = 1000)
    public void testArraySizeIncreases() throws IllegalAccessException {
        HashList<Integer> hashList = new HashList<>();

        var fields = hashList.getClass().getDeclaredFields();
        Map<String, Integer> arraySizes = new HashMap<>();
        for (var f : fields) {
            if (f.getType().isArray()) {
                f.trySetAccessible();
                //System.out.println(f.getName());
                //System.out.println(f.trySetAccessible());
                //System.out.println(Array.getLength(f.get(hashList)));
                arraySizes.put(f.getName(), Array.getLength(f.get(hashList)));
            }
        }
        for (int i = 0; i < 100; i++) {
            hashList.add(i);
        }
        fields = hashList.getClass().getDeclaredFields();
        for (var f : fields) {
            if (f.getType().isArray()) {
                f.trySetAccessible();
                int size = Array.getLength(f.get(hashList));
                if (arraySizes.get(f.getName()) < size) {
                    // success
                    System.out.println(f.getName() + " new size: " + size);
                    return;
                }
            }
        }
        fail("Array size should increase when new elements are added beyond starting size.");
    }

    @Test(timeOut = 1000)
    public void testHashCodeCollision() {
        HashList<StrangeInteger> hashList = new HashList<>();
        for (int i = 50; i < 150; i++) {
            hashList.add(new StrangeInteger(i));
        }
        for (int i = 0; i < 200; i++) {
            if (i >= 50 && i < 150) {
                assertTrue(hashList.contains(new StrangeInteger(i)));
            } else {
                assertFalse(hashList.contains(new StrangeInteger(i)));
            }
        }
    }

    @Test(timeOut = 1000)
    public void testSize() {
        HashList<Integer> numbers = new HashList<>();
        assertEquals(numbers.size(), 0);
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        assertEquals(numbers.size(), 10);
    }

    @Test(timeOut = 1000)
    public void testGet() {
        HashList<Integer> numbers = new HashList<>();
        for (int i = 0; i < 3; i++) {
            numbers.add(i);
        }
        assertEquals(numbers.get(0).intValue(), 0);
        assertEquals(numbers.get(1).intValue(), 1);
        assertEquals(numbers.get(2).intValue(), 2);
        try {
            numbers.get(3);
            fail("Getting element out of bounds should throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            numbers.get(-1);
            fail("Getting element out of bounds should throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }
    @Test(timeOut = 1000)
    public void testAddAllSubClass() {
        class Animal {

        }

        class Dog extends Animal {

        }
        class Cat extends Animal {

        }
        HashList<Animal> animals = new HashList<>();
        Animal animal = new Animal();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        List<Dog> dogs = List.of(dog1, dog2);
        List<Cat> cats = List.of(cat1, cat2);
        animals.add(animal);
        animals.addAll(dogs);
        animals.addAll(cats);
        assertEquals(animals.get(0), animal);
        assertEquals(animals.get(1), dog1);
        assertEquals(animals.get(2), dog2);
        assertEquals(animals.get(3), cat1);
        assertEquals(animals.get(4), cat2);

    }

    @Test(timeOut = 1000)
    public void testRemove() {
        HashList<Integer> numbers = new HashList<>();
        for (int i = 0; i < 4; i++) {
            numbers.add(i);
        }
        assertEquals(numbers.remove(1).intValue(), 1);
        assertEquals(numbers.size(), 3);
        assertEquals(numbers.get(0).intValue(), 0);
        assertEquals(numbers.get(1).intValue(), 2);
        assertEquals(numbers.get(2).intValue(), 3);
        try {
            numbers.get(3);
            fail("Getting element out of bounds should throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test(timeOut = 1000)
    public void testContainsReturnsFalseAfterRemove() {
        HashList<Integer> numbers = new HashList<>();
        for (int i = 0; i < 4; i++) {
            numbers.add(i);
        }
        assertEquals(numbers.remove(1).intValue(), 1);
        assertFalse(numbers.contains(1));

        HashList<StrangeInteger> hashList = new HashList<>();
        for (int i = 0; i < 50; i++) {
            hashList.add(new StrangeInteger(i));
        }
        for (int i = 0; i < 50; i++) {
            assertTrue(hashList.contains(new StrangeInteger(i)));
            assertEquals(hashList.remove(0), new StrangeInteger(i));
            assertFalse(hashList.contains(new StrangeInteger(i)));
        }
    }

    @Test(timeOut = 10000)
    public void testHashListContainsIsFasterThanArrayListContains() {
        HashList<Integer> hashList = new HashList<>();
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            hashList.add(i);
            arrayList.add(i);
        }
        long time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.contains(9999);
        }
        long arrayListTime = System.nanoTime() - time;
        time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            hashList.contains(9999);
        }
        long hashListTime = System.nanoTime() - time;
        System.out.println("ArrayList time: " + arrayListTime);
        System.out.println("HashList time: " + hashListTime);
        assertTrue(arrayListTime > hashListTime * 2, "HashList contains should be at least twice as fast as ArrayList.contains()");
    }
}

