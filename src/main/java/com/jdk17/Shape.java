package com.jdk17;

public sealed class Shape permits  Shape.Circle, Shape.Rectangle, Shape.Square {
    // Common fields and methods of Shape...
    public final class Circle extends Shape {
        public void draw() { /* draw a Circle */ }
    }
    public sealed class Rectangle extends Shape permits FilledRectangle, Square1, TransparentRectangle {
        public void draw() { /* draw a Rectangle */ }
    }
    public non-sealed class Square extends Shape {
        public void draw() { /* draw a Square */ }
    }
    public final class TransparentRectangle extends Rectangle {
        // Specific to TransparentRectangle...
    }
    public final class FilledRectangle extends Rectangle {
        // Specific to FilledRectangle...
    }
    public final class Square1 extends Rectangle {
        public void draw() { /* draw a square */ }
        // Square-specific methods...
    }
}

