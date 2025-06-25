package com.jdk17;

public record BodyMassIndex(double height, double weight, int age) {

    // The record implicitly declares final feilds x and y and a constructor with parameters x and y.
    // a canonical constructor and public accessor methods for them.
}
