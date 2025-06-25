package com.streampracticejava;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SecondLargestNumber {
    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(1,45,67,23,69,78);
        Integer res = li.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(res);
        int inputArray[] = {1,25,34,45,26,26,37};
        List<String> output = Arrays.stream(inputArray).boxed().map(d -> d.toString()).filter(b -> b.startsWith("2")).collect(Collectors.toList());
        System.out.println(output);
    }
}
