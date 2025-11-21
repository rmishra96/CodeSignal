package com.codingsignaltest.inMemoryDB;

public class Main {
    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDB();

        db.set("user1", "name", "Alice");
        db.set("user1", "age", "30");

        System.out.println(db.get("user1", "name")); // Alice
        System.out.println(db.scan("user1"));        // [age(30), name(Alice)]

        db.setAtWithTtl("user1", "session", "xyz", 100, 10);
        System.out.println(db.getAt("user1", "session", 105)); // xyz
        System.out.println(db.getAt("user1", "session", 120)); // null (expired)
    }
}
