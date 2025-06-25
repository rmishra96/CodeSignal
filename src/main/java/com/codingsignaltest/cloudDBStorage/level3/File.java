package com.codingsignaltest.cloudDBStorage.level3;

import java.util.Objects;

public class File {
    String name;
    int size;
    String userId;

    public File(String name, int size, String userId) {
        this.name = name;
        this.size = size;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof File)) return false;
        File other = (File) o;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
