package com.codingsignaltest.inMemoryDB;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InMemoryDBTest {

    @Test
    public void testLevel1() {
        InMemoryDB db = new InMemoryDB();

        db.set("user1", "name", "Alice");
        db.set("user1", "age", "30");

        assertEquals("Alice", db.get("user1", "name"));
        assertEquals("30", db.get("user1", "age"));

        db.set("user1", "age", "31");
        assertEquals("31", db.get("user1", "age"));

        assertNull(db.get("user1", "address"));

        assertTrue(db.delete("user1", "age"));
        assertNull(db.get("user1", "age"));

        assertFalse(db.delete("user1", "age"));
        assertFalse(db.delete("user2", "name"));
    }

    @Test
    public void testLevel2() {
        InMemoryDB db = new InMemoryDB();

        db.set("user1", "name", "Alice");
        db.set("user1", "age", "30");
        db.set("user1", "address", "Wonderland");

        List<String> expected = Arrays.asList("address(Wonderland)", "age(30)", "name(Alice)");
        assertEquals(expected, db.scan("user1"));

        expected = Arrays.asList("address(Wonderland)");
        assertEquals(expected, db.scanByPrefix("user1", "add"));

        assertEquals(Arrays.asList(), db.scan("user2"));
        assertEquals(Arrays.asList(), db.scanByPrefix("user2", "name"));
        assertEquals(Arrays.asList(), db.scanByPrefix("user1", "xyz"));
    }

    @Test
    public void testLevel3() {
        InMemoryDB db = new InMemoryDB();

        db.setAt("user1", "name", "Alice", 1);
        db.setAt("user1", "age", "30", 2);
        db.setAt("user1", "address", "Wonderland", 3);
        db.setAtWithTtl("user1", "tempField", "tempValue", 4, 2);

        assertEquals("Alice", db.getAt("user1", "name", 1));
        assertEquals("30", db.getAt("user1", "age", 2));

        assertNull(db.getAt("user1", "tempField", 7));

        assertTrue(db.deleteAt("user1", "age", 2));
        assertNull(db.getAt("user1", "age", 3));

        assertFalse(db.deleteAt("user1", "tempField", 8));

        List<String> expected = Arrays.asList("address(Wonderland)", "name(Alice)");
        assertEquals(expected, db.scanAt("user1", 2));

        expected = Arrays.asList("address(Wonderland)");
        assertEquals(expected, db.scanPrefixAt("user1", "add", 3));
    }
}
