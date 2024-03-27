package ru.non.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTests {

    @Test
    void arrayTests() {
        String[] array = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);
        Assertions.assertEquals(3, array.length);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listests() {
        var list = new ArrayList<>(List.of("a", "b", "c"));

        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }

    @Test
    void setTests() {
        var set = new HashSet<>(List.of("a", "b", "c", "a"));
        set.add("a");
        Assertions.assertEquals(3, set.size());

    }

    @Test
    void testMap() {
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");
    }

}
