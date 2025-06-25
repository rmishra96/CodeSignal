package com.streampracticejava;

import javax.swing.text.html.Option;
import java.util.*;

public class SecondDuplicateFinderWithStream {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5,3,8,3,2,1,8,7,2);

        Optional<Integer> secondDuplicate = findSecondDuplicate(numbers);
        secondDuplicate.ifPresentOrElse(
                num -> System.out.println("Second duplicate: " + num),
                () -> System.out.println("No second duplicate found")
        );
    }

    private static Optional<Integer> findSecondDuplicate(List<Integer> numbers) {

        Set<Integer> seen =  new HashSet<>();
        return numbers.stream()
                .filter(num -> !seen.add(num))
                .distinct()
                .skip(1)
                .findFirst();
    }
}
