package com.codingsignaltest.inMemoryDB;

import java.util.*;

public class InMemoryDB {
    Map<String, Record> records = new HashMap<>();
    TreeMap<Long, Map<String, Record>> backups = new TreeMap<>();

    void set(long timestamp, String key, String field, int value) {
        records.computeIfAbsent(key, k -> new Record())
                .data.put(field, new Value(value, timestamp, null));
    }

    void setAt(String key, String field, int value, long timestampToSet) {
        records.computeIfAbsent(key, k -> new Record())
                .data.put(field, new Value(value, timestampToSet, null));
    }

    void setWithTTL(long timestamp, String key, String field, int value, int ttl) {
        records.computeIfAbsent(key, k -> new Record())
                .data.put(field, new Value(value, timestamp, ttl));
    }

    void setAtWithTTL(String key, String field, int value, long timestampToSet, int ttl) {
        records.computeIfAbsent(key, k -> new Record())
                .data.put(field, new Value(value, timestampToSet, ttl));
    }

    boolean compareAndSet(long timestamp, String key, String field, int expected, int newValue) {
        Record record = records.get(key);
        if (record != null) {
            Value val = record.data.get(field);
            if (val != null && val.value == expected) {
                record.data.put(field, new Value(newValue, timestamp, null));
                return true;
            }
        }
        return false;
    }

    boolean compareAndSetWithTTL(long timestamp, String key, String field, int expected, int newValue, int ttl) {
        Record record = records.get(key);
        if (record != null) {
            Value val = record.data.get(field);
            if (val != null && val.value == expected) {
                record.data.put(field, new Value(newValue, timestamp, ttl));
                return true;
            }
        }
        return false;
    }

    boolean compareAndDelete(long timestamp, String key, String field, int expected) {
        Record record = records.get(key);
        if (record != null) {
            Value val = record.data.get(field);
            if (val != null && val.value == expected) {
                record.data.put(field, new Value(expected, timestamp, 0));
                return true;
            }
        }
        return false;
    }

    boolean deleteAt(String key, String field, long timestampToDelete) {
        Record record = records.get(key);
        if (record != null && record.data.containsKey(field)) {
            Value val = record.data.get(field);
            val.lastSetAt = timestampToDelete;
            val.ttl = 0;
            return true;
        }
        return false;
    }

    Integer get(long timestamp, String key, String field) {
        Record record = records.get(key);
        if (record == null) return null;
        Value val = record.data.get(field);
        if (val == null || val.isExpired(timestamp)) return null;
        return val.value;
    }

    List<String> scan(long timestamp, String key) {
        Record record = records.get(key);
        if (record == null) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Value> e : record.data.entrySet()) {
            if (!e.getValue().isExpired(timestamp)) {
                res.add(e.getKey() + "(" + e.getValue().value + ")");
            }
        }
        return res;
    }

    List<String> scanAt(String key, long timestamp) {
        return scan(timestamp, key);
    }

    List<String> scanByPrefix(long timestamp, String key, String prefix) {
        List<String> res = new ArrayList<>();
        Record record = records.get(key);
        if (record == null) return res;
        for (Map.Entry<String, Value> e : record.data.entrySet()) {
            if (e.getKey().startsWith(prefix)) {
                if (!e.getValue().isExpired(timestamp)) {
                    res.add(e.getKey() + "(" + e.getValue().value + ")");
                }
            } else if (e.getKey().compareTo(prefix) > 0) {
                break;
            }
        }
        return res;
    }

    int backup(long timestamp) {
        int count = 0;
        Map<String, Record> deepCopy = new HashMap<>();
        for (Map.Entry<String, Record> e : records.entrySet()) {
            Record newRecord = new Record();
            for (Map.Entry<String, Value> fv : e.getValue().data.entrySet()) {
                Value v = fv.getValue();
                if (!v.isExpired(timestamp)) {
                    Integer newTTL = v.ttl == null ? null : v.ttl - (int)(timestamp - v.lastSetAt);
                    newRecord.data.put(fv.getKey(), new Value(v.value, timestamp, newTTL));
                }
            }
            if (!newRecord.data.isEmpty()) {
                deepCopy.put(e.getKey(), newRecord);
                count++;
            }
        }
        backups.put(timestamp, deepCopy);
        return count;
    }

    void restore(long timestamp, long timestampToRestore) {
        Map.Entry<Long, Map<String, Record>> entry = backups.floorEntry(timestampToRestore);
        if (entry != null) {
            Map<String, Record> snapshot = deepCopyRecords(entry.getValue(), timestamp);
            records = snapshot;
        }
    }

    private Map<String, Record> deepCopyRecords(Map<String, Record> original, long newTimestamp) {
        Map<String, Record> copy = new HashMap<>();
        for (Map.Entry<String, Record> e : original.entrySet()) {
            Record newRecord = new Record();
            for (Map.Entry<String, Value> fv : e.getValue().data.entrySet()) {
                Value v = fv.getValue();
                newRecord.data.put(fv.getKey(), new Value(v.value, newTimestamp, v.ttl));
            }
            copy.put(e.getKey(), newRecord);
        }
        return copy;
    }
}
