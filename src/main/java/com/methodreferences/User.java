package com.methodreferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public User(User user) {
        name = null;
    }

    public String getName() {
        return name;
    }

    public static String toUpperCase(String name) {
        return name.toUpperCase();
    }

    public static void main(String[] args) {

        // 1. Reference to static Method :
        List<User> list = Arrays.asList(new User("Sumit") , new User("Ravi") , new User("Suman"));

        List<String> username = list.stream().map( u -> User.toUpperCase(u.getName())).collect(Collectors.toList());
        // Using Method References can be done in this way:

        List<String> user2 = list.stream().map(u -> u.getName()).map(User::toUpperCase).toList();


        // 2 . Reference to a Instance Method :

        List<String> user3 = list.stream().map(User::getName).collect(Collectors.toList());

        // 3. Reference to an Instance Method of a Particular Type

//        long count = list.stream().filter(User:: ).count();

        // 4. Reference to a Constructor
        Stream<User> stream = list.stream().map((Function<? super User, ? extends User>) User::new);

        Stream<User> stream2= list.stream().map(s -> new User(s));
    }

}
