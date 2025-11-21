package com.codingsignaltest.inMemoryDB;

import java.util.*;

public class InMemoryDB {

    private Map<String,Map<String,Value>> store;

    public InMemoryDB() {
        this.store = new HashMap<>();
    }

    static class Value {
        String value;
        int expiry;

        Value(String value, int expiry) {
            this.value = value;
            this.expiry = expiry;
        }
    }

    private boolean isKeyPresent(String key) {
        return store.containsKey(key);
    }

    private boolean isFieldPresent(String key,String field){
        return isKeyPresent(key) && store.get(key).containsKey(field);
    }

//    Level 1
// Set(key, field, value string) - Should insert a field-value pair to the record associated with key. If the field in the record already exists, replace the existing value with the specified value. If record doesn't exist, create a new one.
//
//    Get(key, field string) *string - Should return the value contained within field of record associated with key. If record or field doesn't exist, should return nil
//
//    Delete(key, field string) bool - Should remove the field from the record associated with key. Returns true if the field was successfully deleted, and false if the key or the field do not exist in the database

    public void set(String key,String field, String value){
        store.putIfAbsent(key,new HashMap<>());
        store.get(key).put(field,new Value(value,Integer.MAX_VALUE));
    }

    public String get(String key,String field){
        if(!isFieldPresent(key,field)){
            return null;
        }
        return store.get(key).get(field).value;
    }

    public boolean delete(String key, String field){
        if(!isFieldPresent(key,field)){
            return false;
        }
        store.get(key).remove(field);
        return true;
    }


//     Level 2
//    Scan(key string) []string - Should return a list of strings representing the fields of a record associated with the key. The returned list should be in the following format ["<field1>(<value1>)" , "<field2>(<value2>)", ...] where the fields are lexicographically sorted. If specified record doesn't exist, return empty list.
//
//ScanByPrefix(key, prefix string) []string - Should return a list of strings representing some fields of a records associated with the key. Specifically, only fields that starts with the prefix should be included. The returned list should be the same format as the Scan operation with the fields sorted in lexicographical order.

    public List<String> scan(String key) {
        if (!isKeyPresent(key)) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Value> entry : store.get(key).entrySet()) {
            res.add(entry.getKey() + "(" + entry.getValue().value + ")");
        }
        Collections.sort(res);
        return res;
    }

    public List<String> scanByPrefix(String key, String prefix) {
        if (!isKeyPresent(key)) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Value> entry : store.get(key).entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                res.add(entry.getKey() + "(" + entry.getValue().value + ")");
            }
        }
        Collections.sort(res);
        return res;
    }

//    Level 3
    /*SetAt(key, field, value string, timestamp int) []string - Should insert a field-value pair or update the value of the field in the record associated with key

SetAtWithTtl(key, field, value string, timestamp, ttl int) []string - Should insert a field-value pair or update the value of the field in the record associated with key. Also sets its Time-to-Live starting at timestamp to be ttl. The ttl is the amount of time that this field-value pair should exist in the database, meaning it will be avaialble during the interval: [timestamp, timestamp + ttl]

DeleteAt(key, field string, timestamp int) bool The same as Delete, but with timestamp of the operation specified. Should return true if the field existed and was successfully deleted and false if the key didn't exist.

GetAt(key, field string, timestamp int) *string The same as Get, but with timestamp of the operation specified

ScanAt(key string, timestamp int) []string The same Scan but with the timestamp of the operation specified

ScanPrefixAt(key, prefix string, timestamp int) []string The same as ScanPrefix but with the timestamp of the operation specified.*/

    public void setAt(String key, String field, String value, int timestamp) {
        store.putIfAbsent(key, new HashMap<>());
        store.get(key).put(field, new Value(value, Integer.MAX_VALUE));
    }

    public void setAtWithTtl(String key, String field, String value, int timestamp, int ttl) {
        store.putIfAbsent(key, new HashMap<>());
        store.get(key).put(field, new Value(value, timestamp + ttl));
    }

    public boolean deleteAt(String key, String field, int timestamp) {
        if (!isFieldPresent(key, field)) return false;
        Value val = store.get(key).get(field);
        store.get(key).remove(field);
        return timestamp <= val.expiry;
    }

    public String getAt(String key, String field, int timestamp) {
        if (!isFieldPresent(key, field)) return null;
        Value val = store.get(key).get(field);
        return (timestamp <= val.expiry) ? val.value : null;
    }

    public List<String> scanAt(String key, int timestamp) {
        if (!isKeyPresent(key)) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Value> entry : store.get(key).entrySet()) {
            if (timestamp <= entry.getValue().expiry) {
                res.add(entry.getKey() + "(" + entry.getValue().value + ")");
            }
        }
        Collections.sort(res);
        return res;
    }

    public List<String> scanPrefixAt(String key, String prefix, int timestamp) {
        if (!isKeyPresent(key)) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Value> entry : store.get(key).entrySet()) {
            if (entry.getKey().startsWith(prefix) && timestamp <= entry.getValue().expiry) {
                res.add(entry.getKey() + "(" + entry.getValue().value + ")");
            }
        }
        Collections.sort(res);
        return res;
    }
}
