package com.StupidQuestion;

public class ThreadSafeSingeleton {

    private static volatile ThreadSafeSingeleton instance;
    private ThreadSafeSingeleton() {
        // private constructor to prevent instantiation
    }

    public static ThreadSafeSingeleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingeleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingeleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ThreadSafeSingeleton singleton1 = ThreadSafeSingeleton.getInstance();
        ThreadSafeSingeleton singleton2 = ThreadSafeSingeleton.getInstance();

        System.out.println("Singleton 1 hashcode: " + singleton1.hashCode());
        System.out.println("Singleton 2 hashcode: " + singleton2.hashCode());

        if (singleton1 == singleton2) {
            System.out.println("Both instances are the same.");
        } else {
            System.out.println("Instances are different.");
        }
    }
}
