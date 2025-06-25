package com.javapractice.java.Threading;

import java.util.Arrays;
import java.util.List;

public class UserThread {
    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

//        li.stream().forEach(i -> {
//            System.out.println(i + " " + Thread.currentThread().getName());
//        });

        li.parallelStream().forEach(i -> {
            System.out.println(i + " " + Thread.currentThread().getName());
        });


        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().reduce(5, Integer::sum);
        System.out.println(sum);
    }
}
