package com.stringInterview;

import java.util.*;
import java.util.stream.Collectors;

public class NthSalary {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("vivek", 100);
        map.put("Rinku", 400);
        map.put("vishal", 600);
        map.put("shankar", 500);
        map.put("shyam", 500);
        map.put("tinku", 300);
        System.out.println(findNthSalary(map , 2));
    }

    private static Map.Entry<Integer , List<String>>  findNthSalary(Map<String, Integer> map, int i) {
        return
                map.entrySet().stream().
                        collect(Collectors.groupingBy( a-> a.getValue(),
                                Collectors.mapping(Map.Entry :: getKey, Collectors.toList()))).entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                        .collect(Collectors.toList()).get(i-1);
    }
}
