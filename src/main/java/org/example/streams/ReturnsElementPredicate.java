package org.example.streams;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ReturnsElementPredicate {

    public static void main(String[] args) {
        takeWhilePredicate();
    }

    private static void takeWhilePredicate() {

        List<Integer> integerList = List.of( 3, 4, 5, 6, 7, 8, 9, 10);

        integerList.stream().takeWhile(x -> x % 2 == 0).forEach(System.out::println);

        List<String> stringList = List.of("apple", "app", "apr", "d");

        stringList.stream().takeWhile(x -> x.startsWith("app")).forEach(System.out::println);

//        down while opposite of take while.

        integerList.stream().mapToDouble(value -> value).forEach(System.out::println);

        integerList.stream().filter(x -> x % 2 == 0).limit(2).distinct().forEachOrdered(System.out::print);


    }



}
