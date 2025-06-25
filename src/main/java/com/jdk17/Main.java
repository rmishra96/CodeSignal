package com.jdk17;

public class Main {
    public static void main(String[] args) {
        BodyMassIndex bmil = new BodyMassIndex(5.8, 70, 25);
        BodyMassIndex bmil2 = new BodyMassIndex(5.8, 70, 25);
//        System.out.println(bmil);
        System.out.println(bmil.equals(bmil2));
    }
}
