package org.example.streams;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamsOfClass {

    public static void main(String[] args) {
        // StreamsOfClass.mountStreams();
        StreamsOfClass.emptyStreams();

        Gatherer<Integer, Integer, Integer> evenCounter = Gatherer.ofSequential(
                (Supplier<Integer>) () -> 0, // Initial state: count = 0
                (Integer state, Integer elem, BiConsumer<Integer> downstream) -> { // Integrator
                    if (elem % 2 == 0) {
                        state++; // Update state
                        downstream.accept(state); // Emit running count of evens
                    }
                });

    }

    public static void mountStreams() {
        List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        list.add("four"); // modCount=4

        Set<String> result = list.stream()
                .filter(s -> {
                    if ("two".equals(s)) {
                        list.add(0, "intruder"); // Insert at head—shifts everything
                        // modCount=5 now, but this iterator's check is pending
                    }
                    return !s.equals("three"); // Try to exclude
                })
                .collect(Collectors.toSet());

        System.out.println("Original: " + list); // [intruder, one, two, three, four]
        System.out.println("Stream: " + result); // Often {intruder, one, two, four}—three ghosts away!
        // Why? Insert shifts cursor; "three" gets skipped as indices remap.
    }

    public static void emptyStreams() {
        Stream<Integer> stream = Stream.empty();
        for (Integer integer : stream.toList()) {
            System.out.println("integer: " + integer);
        }

        System.out.println("Check stream(-- stream object list ).toList(): ");
        Stream<Double> streamDouble = Stream.of(1.0, 2.0, 3.0);
        for (Double doubleValue : streamDouble.collect(Collectors.toUnmodifiableList())) {
            System.out.println("double: " + doubleValue);
        }

        Stream<Object> test = Stream.builder()

                .add("string")
                .add(3)
                .add('c')
                .build();

        test.forEach(System.out::println);
        // test.collect(Collectors.toUnmodifiableList());
        List<String> list = Arrays.asList("a", "b", "c", null, "test");
        Stream<String> stream1 = list.stream();
        stream1.forEach(System.out::println);
        Stream stream2 = Stream.ofNullable(list);
        stream2.forEach(System.out::println);

        List<String> test2 = null;
        String testNull = null;
        int limitCount = 10;
        // limitCount = 5;
        Stream<Double> stream3 = Stream.<Double>generate(() -> (double) (Math.random())).limit(limitCount);
        limitCount = 15;
        stream3.forEach(System.out::println);

        ArrayList arrayList = new ArrayList();
        arrayList.stream();

    }

}
