package org.example.generic;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class TinyUrl {
    String url = "url";
}

class StartWithNumber extends TinyUrl {
    String pattern = "number";

}

class StartWithString extends TinyUrl {
    String pattern = "String";
}

class SubTypeStartWithNumber extends StartWithNumber {
    String pattern = "Subtype of StartWithNumber";
}

public class GenericClass {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        GenericClass.BoundWildCardGeneric<StartWithNumber> wailCardVariable =
//                new BoundWildCardGeneric<>(new StartWithNumber(), StartWithNumber.class);
//        wailCardVariable.validateWildCard();
//
//        GenericClass.BoundWildCardGeneric<TinyUrl> wailCardVariable1 =
//                new BoundWildCardGeneric<>(new TinyUrl());
//        wailCardVariable1.validateWildCard();


//        checksWildCard();

        wildCardQuestion1();
        wildCardQuestion2();
    }

    private static void wildCardQuestion2() {
        System.out.println("WildCard Question 2");
    }

    private static void wildCardQuestion1() {

        List<Integer> integersList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Double> doubleList = List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0);
        printSumOfNumbers(integersList);
        printSumOfNumbers(doubleList);

        /**
         * It will throw exception, it isn't extends Number class. for extends class
         * */
        List<StartWithNumber> startWithNumberList =
                List.of(new StartWithNumber(), new SubTypeStartWithNumber());
//        printSumOfNumbers(startWithNumberList);

        printSumOfNumbersOfSuper(Collections.singletonList(startWithNumberList));
        printSumOfNumbersOfSuper(Collections.singletonList(integersList));
        printSumOfNumbersOfSuper(Collections.singletonList(doubleList));
    }

    private static void printSumOfNumbers(List<? extends Number> numbers) {
        for (Number number: numbers){
            System.out.println("Extends number " + number);
        }
    }

    private static void printSumOfNumbersOfSuper(List<? super Number> numbers) {
        for (Object number: numbers){
            System.out.println("super number " + number);
        }
    }

    /** <? extends TinyUrl> ? means class extends TinyUrl can read operation.
    * <? super TinyUrl> ? means class's super is TinyUrl so, under the TinyUrl we can add, write is possible.
    * */
    private static void checksWildCard() {

        List<? extends TinyUrl> wildCards = Arrays
                .asList(new StartWithNumber(),new StartWithString());
        System.out.println();
        for (TinyUrl url: wildCards) {
            System.out.println(url);
        }

        List<? super TinyUrl> list = new ArrayList<>();
        list.add(new StartWithNumber());
        list.add(new SubTypeStartWithNumber());
//        list.add(new TinyUrl());
        list.add(new StartWithString());
        Number num;
        Integer num2 = 9;
        Double num3 = 2.4d;

        Number num4  = new AtomicInteger();
        List<? super Number> superofNumbers = new ArrayList<>();
        superofNumbers.add(num2);
        superofNumbers.add(num3);

        superofNumbers.add(num4);

        List<? extends Number> extendsList = (List<? extends Number>) Arrays.asList(num2, num3);


//        list.add(new String());  compile-time error

        for (Object url: list){
            if (url instanceof StartWithNumber){
                System.out.println(((StartWithNumber) url).pattern);
            } else if (url instanceof StartWithString) {
                System.out.println(((StartWithString) url).pattern);
            } else if (url instanceof TinyUrl) {
                System.out.println(((TinyUrl) url).url);
            }
        }

    }

    static class BoundWildCardGeneric<T extends TinyUrl> {

        private final T wailCardVariable;

        private final Class<? super T> lowerBoundWildCard;

        public BoundWildCardGeneric(T upperBoundCase){
            this(upperBoundCase, Object.class);
        }

        /**
         * This class to StartWithNumber and StartWithString
         *
         */
        public BoundWildCardGeneric(T wailCardVariable,
                                    Class<? super T> lowerBoundWildCard) {
            this.lowerBoundWildCard = lowerBoundWildCard;
            this.wailCardVariable = wailCardVariable;
        }

        public void validateWildCard() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            var object = lowerBoundWildCard.getDeclaredConstructor().newInstance();
            System.out.println(object);

            System.out.println("--- : "+ ((StartWithNumber) object).pattern);
            if (wailCardVariable instanceof StartWithNumber){
                System.out.println("WildCard variable is a StartWithNumber");
                System.out.println(((StartWithNumber) wailCardVariable).pattern);
                System.out.println(lowerBoundWildCard. isAssignableFrom(StartWithString.class));

                System.out.println(lowerBoundWildCard.isAssignableFrom(List.class));
            } else if (wailCardVariable instanceof StartWithString){
                System.out.println("WildCard variable is a StartWithString");
                System.out.println(((StartWithString) wailCardVariable).pattern);
            } else if (wailCardVariable != null) {
                System.out.println("WildCard variable is a TinyUrl");
                System.out.println(wailCardVariable.url);
            } else
                System.out.println("Wrong wildcard variable");
        }

    }
}
