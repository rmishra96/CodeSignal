package com.codingsignaltest.cloudDBStorage;

import java.util.*;

public class Simulation {

    public static List<String> simulateCodingFramework(List<List<String>> commands) {
        Map<String, FileEntry> files = new HashMap<>();
        List<String> output = new ArrayList<>();
        TreeMap<String, FileEntry> timeline = new TreeMap<>();
        boolean rollbackMode = false;
        String rollbackTime = null;

        for (List<String> cmd : commands) {
            String action = cmd.get(0);

            switch (action) {
                case "FILE_UPLOAD": {
                    String name = cmd.get(1);
                    String size = cmd.get(2);
                    files.put(name, new FileEntry(name, size));
                    output.add("uploaded " + name);
                    break;
                }
                case "FILE_GET": {
                    String name = cmd.get(1);
                    output.add(files.containsKey(name) ? "got " + name : "file not found");
                    break;
                }
                case "FILE_COPY": {
                    String src = cmd.get(1);
                    String dest = cmd.get(2);
                    if (files.containsKey(src)) {
                        files.put(dest, new FileEntry(dest, files.get(src).size));
                        output.add("copied " + src + " to " + dest);
                    } else {
                        output.add("file not found");
                    }
                    break;
                }
                case "FILE_SEARCH": {
                    String prefix = cmd.get(1);
                    List<String> found = new ArrayList<>();
                    for (String name : files.keySet()) {
                        if (name.startsWith(prefix)) found.add(name);
                    }
                    Collections.sort(found);
                    output.add("found [" + String.join(", ", found) + "]");
                    break;
                }
                case "FILE_UPLOAD_AT": {
                    String time = cmd.get(1);
                    String name = cmd.get(2);
                    String size = cmd.get(3);
                    int ttl = cmd.size() == 5 ? Integer.parseInt(cmd.get(4)) : -1;
                    FileEntry entry = new FileEntry(name, size, time, ttl);
                    timeline.put(time + "_" + name, entry);
                    files.put(name, entry);
                    output.add("uploaded at " + name);
                    break;
                }
                case "FILE_GET_AT": {
                    String time = cmd.get(1);
                    String name = cmd.get(2);
                    FileEntry entry = files.get(name);
                    if (entry != null && entry.isAliveAt(time)) {
                        output.add("got at " + name);
                    } else {
                        output.add("file not found");
                    }
                    break;
                }
                case "FILE_COPY_AT": {
                    String time = cmd.get(1);
                    String src = cmd.get(2);
                    String dest = cmd.get(3);
                    FileEntry entry = files.get(src);
                    if (entry != null && entry.isAliveAt(time)) {
                        FileEntry copy = new FileEntry(dest, entry.size, time, -1);
                        files.put(dest, copy);
                        timeline.put(time + "_" + dest, copy);
                        output.add("copied at " + src + " to " + dest);
                    } else {
                        output.add("file not found");
                    }
                    break;
                }
                case "FILE_SEARCH_AT": {
                    String time = cmd.get(1);
                    String prefix = cmd.get(2);
                    List<String> found = new ArrayList<>();
                    for (FileEntry entry : files.values()) {
                        if (entry.name.startsWith(prefix) && entry.isAliveAt(time)) {
                            found.add(entry.name);
                        }
                    }
                    Collections.sort(found);
                    output.add("found at [" + String.join(", ", found) + "]");
                    break;
                }
                case "ROLLBACK": {
                    rollbackTime = cmd.get(1);
                    rollbackMode = true;
                    files.clear();
                    for (Map.Entry<String, FileEntry> e : timeline.entrySet()) {
                        if (e.getValue().createdAt.compareTo(rollbackTime) <= 0) {
                            files.put(e.getValue().name, e.getValue());
                        }
                    }
                    output.add("rollback to " + rollbackTime);
                    break;
                }
            }
        }

        return output;
    }

    static class FileEntry {
        String name;
        String size;
        String createdAt;
        int ttl;

        FileEntry(String name, String size) {
            this.name = name;
            this.size = size;
            this.createdAt = null;
            this.ttl = -1;
        }

        FileEntry(String name, String size, String createdAt, int ttl) {
            this.name = name;
            this.size = size;
            this.createdAt = createdAt;
            this.ttl = ttl;
        }

        boolean isAliveAt(String time) {
            if (createdAt == null) return true;
            if (ttl == -1) return createdAt.compareTo(time) <= 0;
            long created = parseTime(createdAt);
            long current = parseTime(time);
            return current <= created + ttl;
        }

        long parseTime(String t) {
            return java.time.Instant.parse(t + "Z").getEpochSecond();
        }
    }
}
