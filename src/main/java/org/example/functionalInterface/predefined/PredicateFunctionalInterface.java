package org.example.functionalInterface.predefined;

import org.example.functionalInterface.MyOwnPredicateFunctionalInterface;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PredicateFunctionalInterface {

    public static void main(String[] args) {
//        Predicate<Integer> predicate = i -> i > 5;
//
//        System.out.println(predicate.test(5));
//        System.out.println(predicate.test(6));
//        System.out.println(predicate.test(7));

        questionCheckStringLengthEven();
    }

    private static void questionCheckStringLengthEven() {

        // Question 1
        Predicate<String> predicate = s -> s.length() % 2 == 0;
        Predicate<String> predicate2 = s -> s.length() % 2 == 1;
        Predicate<String> orPredicate = Predicate.isEqual(predicate);
        Predicate<String> andPredicate = predicate.and(predicate2);

        System.out.println(orPredicate.test("abc"));
        System.out.println(orPredicate.test("hello"));
        System.out.println(orPredicate.test("world!"));


        // Question 2
        List<Integer> predicateIntergerList = List.of(1, 10, 15, 20);
        Predicate<Integer> predicateInteger = i -> i > 5 ;
        System.out.println(predicateIntergerList.stream()
                .filter(predicateInteger.and(integer -> integer < 20)).collect(Collectors.toList()));


        // Question 3
        List<Integer> predicateIntegerList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        Predicate<Integer> isPrime = number -> {
            for (int i = 2; i < number/2; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        };

        predicateIntegerList.stream()
                .filter(isPrime.and(num -> !(num % 2 == 0)))
//                .dropWhile(num -> num % 2 == 0)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
        // Lambda expression
        Predicate<Character> characterPredicate = Character::isAlphabetic;

        // My own Functional interface testing
        MyOwnPredicateFunctionalInterface myOwnPredicateFunctionalInterface = (String::equalsIgnoreCase);
        System.out.println("My ownPredicateFunction : "+myOwnPredicateFunctionalInterface.test("test", "string"));

        BiPredicate<String, String> biPredicate = String::equalsIgnoreCase;

        System.out.println(biPredicate.test("a", "a"));

    }

}
