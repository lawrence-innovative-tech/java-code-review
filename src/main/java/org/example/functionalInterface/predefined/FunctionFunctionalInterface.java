package org.example.functionalInterface.predefined;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionFunctionalInterface {

    public static void main(String[] args) {

        predefineFunctionalInterface();
    }

    private static void predefineFunctionalInterface() {

        List<String> emailList = List.of("ab@gmail.com", "b@yahoo.com", "c@edge.com", "d@yahoo.com", "e@gmail.com");

        Function<String, String> checkEmails = email -> {
            if (email.split("@")[1].split("\\.")[0].equalsIgnoreCase("gmail")){
                return "Personal";
            }
            return "Work";
        };

        String result = checkEmails.apply("ab@gmail.com");
        System.out.println("Result: " + result);

        emailList.stream().map(checkEmails).forEach(System.out::println);


        List<Double> numberList = List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);


        Function<Double, String> checkNumber = number -> {
            String numberStr = String.valueOf(number);
            if (numberStr.split("\\.")[1].length() > 1){
                return numberStr.split("\\.")[0]+ "." + numberStr.split("\\.")[1].substring(0,2);
            }
            return numberStr + "0".repeat(2 - numberStr.split("\\.")[1].length());
        };

        numberList.stream().map(checkNumber.compose(number -> {
            return 1.1 * number;
        })).forEach(System.out::println);


        numberList.stream()
                .map(num -> {
                        // Convert to BigDecimal, multiply by 1.1 (exact), truncate to 2 decimals, format
        return BigDecimal.valueOf(num)
                .multiply(BigDecimal.valueOf(1.1))  // Or use new BigDecimal("1.1") for string literal
                .setScale(2, RoundingMode.DOWN)      // Truncate (DOWN for no rounding up)
                .toPlainString();                   // "1.10" (no sci notation, pads zeros)
        // Alternative for rounding: .setScale(2, RoundingMode.HALF_UP)
    })  // No need for compose—multiplication is now inside checkNumber
                .forEach(System.out::println);

        BiFunction<String, String, String> test;
        Map<String, String> map = new HashMap<>();
        map.entrySet().stream().mapMulti((key, value) -> {
            System.out.println("Key: " + key + " Value: " + value);
        }).findFirst().ifPresent(value -> System.out.println("Key: " + value));

    }
}
