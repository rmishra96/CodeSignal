package com.stringInterview;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FindDuplicateCharacter {
    public static void main(String[] args) {
        String val = "vivekkadiyan";

        Map<String, Long> map = Arrays.stream(val.split("")).collect(Collectors.groupingBy(ch -> ch , LinkedHashMap :: new, Collectors.counting())).entrySet()
                .stream().filter(ch -> ch.getValue() >1).collect(Collectors.toMap(Map.Entry :: getKey , Map.Entry :: getValue));
        System.out.println(map);

        /// Unique Element

        Map<String, Long> map1 = Arrays.stream(val.split("")).collect(Collectors.groupingBy(ch -> ch, LinkedHashMap :: new , Collectors.counting()))
                .entrySet().stream().filter(ch -> ch.getValue() == 1).collect(Collectors.toMap(Map.Entry :: getKey , Map.Entry :: getValue));
        System.out.println(map1);

    }
}
