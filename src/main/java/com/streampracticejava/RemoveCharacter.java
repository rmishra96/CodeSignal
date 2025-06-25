package com.streampracticejava;

import java.sql.SQLOutput;
import java.util.stream.Collectors;

public class RemoveCharacter {

    public static void main(String[] args) {
        String str = "Hello World";
        String newStr = str.chars().filter(ch -> ch != 'l').collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append).toString();
        System.out.println(newStr);

        char charToRemove = 'l';

        String result = str.chars().filter(c -> c!= charToRemove).mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());

        System.out.println("Original String: " + str);
        System.out.println("String after removing '" + charToRemove + "': " + result);
    }
}
