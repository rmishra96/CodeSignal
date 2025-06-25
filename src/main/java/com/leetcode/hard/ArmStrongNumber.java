package com.leetcode.hard;

public class ArmStrongNumber {
    public static void main(String[] args) {
        Armstrong ob = new Armstrong();
        int x = 153;
        System.out.println(ob.isArmstrong(x));
        x = 1253;
        System.out.println(ob.isArmstrong(x));
    }
}
