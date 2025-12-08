package org.example.functionalInterface.predefined;

import java.util.List;
import java.util.function.Function;

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

        emailList.stream().map(checkEmails).forEach(System.out::println);
    }
}
