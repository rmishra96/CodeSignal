package com.streampracticejava;

import javax.swing.text.html.Option;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SecondNonRepeartedCharacter {
    public static void main(String[] args) {
        String str = "swiss";
        Optional<Character> secondNonRepeatedChar = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry :: getKey)
                .skip(1)
                .findFirst();

        if(secondNonRepeatedChar.isPresent()){
            System.out.println(secondNonRepeatedChar.get());
        }else {
            System.out.println("none");
        }
    }
}
