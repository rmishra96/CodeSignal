package com.streampracticejava;

import java.text.CollationElementIterator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DetectSpecialCharacter {
    public static void main(String[] args) {
        String str = "Hello@World";

        String specialChars  = str.chars().filter(ch -> !Character.isLetterOrDigit(ch)).mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());

        System.out.println("Special Characters: " + specialChars);


        // Extracting numbers starting with 1

        List<Integer>  numbers = Arrays.asList(1, 12, 23, 14, 25, 36, 17, 38, 19, 10);

        List<Integer> numstart1s =  numbers.stream().filter(num -> String.valueOf(num).startsWith("1")).collect(Collectors.toList());

        System.out.println("Numbers starting with 1: " + numstart1s);

    }
}
