package ee.taltech.iti0202.generics;

import ee.ttu.java.studenttester.annotations.TestContextConfiguration;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.*;

@TestContextConfiguration(identifier = 1)
public class Hash2MapTest {
    @Test(timeOut = 1000)
    public void testGet() {
        Hash2Map<String, String, String> hash2Map = new Hash2Map<>();
        hash2Map.put("a", "b", "c");
        hash2Map.put("d", "e", "f");
        hash2Map.put("a", "c", "f");
        hash2Map.put("d", "e", "g");
        assertEquals(hash2Map.get("a", "b"), "c");
        assertEquals(hash2Map.get("d", "e"), "g");
        assertEquals(hash2Map.get("a", "c"), "f");
        assertNull(hash2Map.get("h", "j"));
        assertNull(hash2Map.get("a", "d"));
        assertNull(hash2Map.get("d", "d"));
    }

    @Test(timeOut = 1000)
    public void testGetKeys() {
        Hash2Map<String, String, String> hash2Map = new Hash2Map<>();
        assertTrue(hash2Map.getKeys().isEmpty());
        hash2Map.put("a", "b", "c");
        hash2Map.put("a", "f", "g");
        assertEquals(hash2Map.getKeys(), Set.of("a"));
        hash2Map.put("d", "f", "g");
        assertEquals(hash2Map.getKeys(), Set.of("a", "d"));
    }

    @Test(timeOut = 1000)
    public void testGetKeysForKey() {
        Hash2Map<String, String, String> hash2Map = new Hash2Map<>();
        assertTrue(hash2Map.getKeys("e").isEmpty());
        hash2Map.put("a", "b", "c");
        hash2Map.put("a", "f", "g");
        assertEquals(hash2Map.getKeys("a"), Set.of("b", "f"));
        hash2Map.put("d", "f", "g");
        assertEquals(hash2Map.getKeys("d"), Set.of("f"));
    }

    @Test(timeOut = 1000)
    public void testGetMap() {
        Hash2Map<String, String, String> hash2Map = new Hash2Map<>();
        assertTrue(hash2Map.getMap("e").isEmpty());
        hash2Map.put("a", "b", "c");
        assertEquals(hash2Map.getMap("a"), Map.of("b", "c"));
        hash2Map.put("a", "f", "g");
        assertEquals(hash2Map.getMap("a"), Map.of("b", "c", "f", "g"));
    }

    @Test(timeOut = 1000)
    public void testGetAllValues() {
        Hash2Map<Integer, Integer, Integer> hash2Map = new Hash2Map<>();
        assertTrue(hash2Map.getAllValues().isEmpty());
        int nr = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                hash2Map.put(i, j, nr++);
            }
        }
        assertEquals(hash2Map.getAllValues().size(), 5 * 5);
        assertEquals(new HashSet<>(hash2Map.getAllValues()), new HashSet<>(IntStream.range(0, 25).boxed().collect(Collectors.toList())));
    }
}

