package org.example.streams;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorClass {

    public static void main(String[] args) {
//        customCollectorExample();
        customCollectorSecondLargestNumber();
    }

    public static void customCollectorExample() {

        Collector<CharSequence, StringBuilder, String> customCollector =
                Collector.of(
                        StringBuilder::new, // Supplier
                        StringBuilder::append, // Accumulator
                        StringBuilder::append, // Combiner
                        StringBuilder::toString // Finisher
                );

                Collectors.filtering(null, null);

    }

    static class Employee {
        private String name;
        private long salary;

        Employee(String name, long salary) {
            this.name = name;
            this.salary = salary;
        }

        public long getSalary() {
            return salary;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name + "  " + salary;
        }
    }

    public static void customCollectorSecondLargestNumber() {

        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 7);

        PriorityQueue<Integer> secondLargestNumber = numbers.stream().collect(() -> new PriorityQueue<Integer>(Integer::compareTo),
                (queue, number) -> {
                    queue.offer(number);
                    if (queue.size() > 2){
                        queue.poll();
                    }
                },
                (queue1, queue2) -> {

                });
        secondLargestNumber.forEach(System.out::println);
//        if (!secondLargestNumber.isEmpty()) {
//            System.out.println(secondLargestNumber.peek());
//        }

//          CTS Interview question
        List<Employee> employees = Arrays.asList(
                new Employee("raja", 50000),
                new Employee("ebi", 10000),
                new Employee("ezekiel", 100000),
                new Employee("nish", 70000),
                new Employee("rajan", 500000));

        PriorityQueue<Employee> largestEmployeeList = employees.stream().collect(() ->
                        new PriorityQueue<>(Comparator.comparingLong(Employee::getSalary)),
                (queue, number) -> {
                    queue.offer(number);
//                    if (queue.size() > 2){
//                        queue.poll();
//                    }
                },
                (queue1, queue2) -> {});
//        largestEmployeeList.stream().peek(System.out::println);

//        System.out.println(largestEmployeeList);
        while (!largestEmployeeList.isEmpty()) {
            System.out.println(largestEmployeeList.poll());
        }
//        Using reduce function

//        employees.stream().reduce(new PriorityQueue<Employee>(),
//                (queue, employee) -> {
//                    queue.
//                })

        System.out.println();

        Employee highestEmployee = employees.stream().min(Comparator.comparingLong(Employee::getSalary)).orElse(null);
        System.out.println(highestEmployee);


        Collector<Employee, ?, List<Employee>> test = Collectors.toCollection(ArrayList::new);
        Supplier<?> supplier = test.supplier();

    }

}
