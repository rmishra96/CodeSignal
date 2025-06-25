package com.javapractice.java.Practice;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapFlatMap {
    public static void main(String[] args) {

            String[] arrayOfWords = {"STACK", "OOOVVVER"};
            Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
            streamOfwords.map(word -> word.split(""))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .forEach(System.out::print);

        System.out.println("************" );


        String[] arrayOfWordsq = {"STACK", "OOOVVVER"};
        Stream<String> arrayOfWordsq1 = Arrays.stream(arrayOfWordsq);
        arrayOfWordsq1.map(s->s.split("")) //Converting word in to array of letters
                .flatMap(Arrays::stream).distinct() //flattens each generated stream in to a single stream
                .collect(Collectors.toList()).forEach(System.out :: print);


    }
}
