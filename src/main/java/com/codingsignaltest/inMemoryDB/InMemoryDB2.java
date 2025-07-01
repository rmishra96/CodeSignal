package com.codingsignaltest.inMemoryDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

class Item {
    String field;
    String value;
    int timestamp;
    int ttl;

    Item(String field, String value) {
        this(field, value, 0, 0);
    }

    Item(String field, String value, int timestamp, int ttl) {
        this.field = field;
        this.value = value;
        this.timestamp = timestamp;
        this.ttl = ttl;
    }
}


public class InMemoryDB2 {
    private final Map<String, List<Item>> itemList = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    /*lEVE 1*/

    public void set(String key, String field, String value) {
        lock.lock();
        try {
            itemList.computeIfAbsent(key, k -> new ArrayList<>()).add(new Item(field, value));
        } finally {
            lock.unlock();
        }
    }

    public String get(String key, String field) {
        lock.lock();
        try {
            List<Item> items = itemList.get(key);
            if (items != null) {
                for (Item item : items) {
                    if (item.field.equals(field)) {
                        return item.value;
                    }
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void delete(String key, String field) {
        lock.lock();
        try {
            List<Item> items = itemList.get(key);
            if (items != null) {
                items.removeIf(item -> item.field.equals(field));
            }
        } finally {
            lock.unlock();
        }
    }

    /* lEVEl 1 enDING*/

    /*Level 2
    *  Starting */

    public List<String> scanAt(String key, int timestamp) {
        lock.lock();
        try {
            List<String> results = new ArrayList<>();
            List<Item> items = itemList.get(key);
            if (items != null) {
                for (Item item : items) {
                    if (item.ttl == 0 || timestamp <= item.timestamp + item.ttl) {
                        results.add(String.format("%s(%s)", item.field, item.value));
                    }
                }
            }
            return results;
        } finally {
            lock.unlock();
        }
    }

    public List<String> scanByPrefixAt(String key, String prefix, int timestamp) {
        lock.lock();
        try {
            List<String> results = new ArrayList<>();
            List<Item> items = itemList.get(key);
            if (items != null) {
                for (Item item : items) {
                    if (item.field.startsWith(prefix) && (item.ttl == 0 || timestamp <= item.timestamp + item.ttl)) {
                        results.add(String.format("%s(%s)", item.field, item.value));
                    }
                }
            }
            return results;
        } finally {
            lock.unlock();
        }
    }
    /*          Level 2 ending */



    public void setAt(String key, String field, String value, int timestamp) {
        setAtWithTtl(key, field, value, timestamp, 0);
    }

    public void setAtWithTtl(String key, String field, String value, int timestamp, int ttl) {
        lock.lock();
        try {
            List<Item> items = itemList.computeIfAbsent(key, k -> new ArrayList<>());
            for (Item item : items) {
                if (item.field.equals(field)) {
                    item.value = value;
                    item.timestamp = timestamp;
                    item.ttl = ttl;
                    return;
                }
            }
            items.add(new Item(field, value, timestamp, ttl));
        } finally {
            lock.unlock();
        }
    }

    public String getAt(String key, String field, int timestamp) {
        lock.lock();
        try {
            List<Item> items = itemList.get(key);
            if (items != null) {
                for (Item item : items) {
                    if (item.field.equals(field)) {
                        if (item.ttl == 0 || timestamp <= item.timestamp + item.ttl) {
                            return item.value;
                        }
                    }
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public boolean deleteAt(String key, String field, int timestamp) {
        lock.lock();
        try {
            List<Item> items = itemList.get(key);
            if (items != null) {
                return items.removeIf(item -> item.field.equals(field));
            }
            return false;
        } finally {
            lock.unlock();
        }
    }


}

