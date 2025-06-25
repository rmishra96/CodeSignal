package com.javapractice.java.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class POC {

    public static void main(String[] args) {
        int reduce = IntStream.of(1,2,3,4,5).reduce(10,(x,y) -> x+y);
        System.out.println(reduce);

        String[] st = {"abv","a","abv","n","r","g"};
        List<String> stringList = Arrays.asList(st);
       stringList.stream().
               collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).
               entrySet().stream().filter(x -> x.getValue() >1).map(Map.Entry :: getKey)
               .collect(Collectors.toList());
        System.out.println(stringList);


    }
}
