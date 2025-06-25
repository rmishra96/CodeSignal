package com.streampracticejava;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SumOfEvenOdd {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sumOfEvens = numbers.stream().filter(num -> num % 2 == 0).mapToInt(Integer::intValue).sum();

        int sumOfOdd = numbers.stream().filter(num -> num % 2 != 0).mapToInt(Integer::intValue).sum();

        System.out.println("Sum of even numbers: " + sumOfEvens);
        System.out.println("Sum of odd numbers: " + sumOfOdd);

        List<Integer> nums = Arrays.asList(1, 2, 3, 3, 4, 4, 5);

        Set<Integer> duplicates = nums.stream().distinct().collect(Collectors.toSet());
        System.out.println(duplicates);
    }
}
