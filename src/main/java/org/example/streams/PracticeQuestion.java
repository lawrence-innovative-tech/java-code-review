package org.example.streams;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PracticeQuestion {

    public static void main(String[] args) {

//        firstQuestion();
//        secondQuestion();
//        thirdQuestion();
        fourthQuestion();
    }

    /*
    * For each customer, count orders and total spend;
    * output sorted by total spend descending.
    * Handle customers with no orders (0)*/

//    private static class OrderSummary{
//
//        private long totalCount;
//        private double total;
//
//        OrderSummary(long totalCount, double total){
//            this.totalCount = totalCount;
//            this.total = total;
//        }
//
//        public long getTotalCount() {
//            return totalCount;
//        }
//
//        public double getTotal(){
//            return total;
//        }
//
//        @Override
//        public String toString() {
//            return total + " " + totalCount;
//        }
//    }

    private static void fourthQuestion() {
        System.out.println("Question 4");

        List<Order> orders = getOrderDetails();
        List result = orders.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(Order::getCustomerId),
               stringListMap -> stringListMap.entrySet().stream()

                       .flatMap(stringListEntry -> stringListEntry.getValue().stream())
//                       .flatMap(order -> order.getItems().stream())
                       .map(orderItem -> {
                           long totalCount = orderItem.getItems().size();
                           double totalSum = orderItem.getItems().stream().mapToDouble(OrderItem::getTotal).sum();
                           return List.of(totalSum, totalCount);
                       }).collect(Collectors.toList())));
        System.out.println(result);
    }

    /*
    * Top Products by Revenue: From Orders, group by productId,
    * sum total revenue (quantity*price), and get top-3 products by revenue*/
    private static void thirdQuestion() {

        System.out.println("Question 3");

        List<Order> orders = getOrderDetails();
        Map<String, Double> revenue = orders.stream()
                .flatMap(item -> item.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductId, Collectors.summingDouble(OrderItem::getTotal)));
//                .collect(Collectors.groupingBy(OrderItem::getProductId,
//                        Collectors.toMap(OrderItem::getProductId, Collectors.summarizingDouble(OrderItem::getTotal))));

//          Approach 1
        Map<String, Double> topRevenue = revenue.entrySet().stream()
//                .sorted((o1, o2) -> {
//                    return o1.getValue().compareTo(o2.getValue());
//                })
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//       Check single group by function
        Map<String, List<OrderItem>> testRevenue = orders.stream().flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductId));


//        Approach 1.a for combine two stream into one stream pipeline.

        Map<String, Double> combinedRevenueStream = orders.stream().flatMap(order -> order.getItems().stream())
                        .collect(Collectors.collectingAndThen(
                                Collectors.groupingBy(OrderItem::getProductId, Collectors.summingDouble(OrderItem::getTotal)),
                                map -> map.entrySet().stream()
                                        .sorted(Map.Entry.comparingByValue())
                                        .limit(3)
                                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));


        combinedRevenueStream.entrySet().stream().forEach(System.out::println);

        System.out.println("Above combined");

        topRevenue.entrySet().forEach(System.out::println);

        System.out.println();

//        Approach 2
        Map<String, Double> top3Revenue = revenue.entrySet().stream()
                .collect(
                        () -> new PriorityQueue<Map.Entry<String, Double>>(Map.Entry.comparingByValue((Double::compareTo))),
                        (queue, entry) -> {
                            queue.offer(entry);
                            if (queue.size() > 3) {
                                queue.poll();  // remove smallest
                            }
                        },
                        (q1, q2) -> {
                            q2.forEach(entry -> {
                                q1.offer(entry);
                                if (q1.size() > 3) {
                                    q1.poll();
                                }
                            });
                        }
                )
                .stream()
                .peek(System.out::println)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1
//                        LinkedHashMap::new
                ));

        System.out.println();
        top3Revenue.entrySet().forEach(System.out::println);

//        Approach 3
        Map<String, Double> app3revenue = revenue.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println();
        app3revenue.entrySet().forEach(System.out::println);

    }

    /*
    * Recent Hires Bonus: Find employees hired in last 2 years earning <60k;
    * increase their salary by 10% and collect updated list.*/
    private static void secondQuestion() {

        System.out.println("Question 2");
        LocalDate now = LocalDate.now();
        double fixedSalary = 60000;

        List<Employee> employees = Arrays.asList(
                new Employee("E1", "Alice", "Eng", 50000, now.minusYears(3)), // 2022-12-15
                new Employee("E2", "Bob", "Eng", 55000, now.minusYears(1)),
                new Employee("E3", "Charlie", "Sales", 40000, now.minusYears(4)),
                new Employee("E4", "Diana", "Sales", 45000, now.minusYears(2).plusDays(10)),
                new Employee("E5", "Eve", "Sales", 65000, now.minusYears(1)));



        List<Employee> incrementList = employees.stream().filter(employee -> now.minusYears(2).isBefore(employee.getHireDate())
                        && employee.getSalary() <= fixedSalary)
                        .peek(employee -> {
                            double increment = employee.getSalary() + employee.getSalary() * 0.1 ;
                            employee.setSalary(increment);
                        })
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList();

        incrementList.forEach(employee -> {
            System.out.println(employee.getName() +"  "+ employee.getSalary());
        });

        System.out.println();

    }

    /*
    * Department Salary Stats: From a list of Employees,
    * group by department and compute min/max/average salary per group.
    * Output a map of department to stats (e.g., "Avg: X").
    * Filter out departments with <2 employees. (Medium: Basic grouping.)*/
    private static void firstQuestion() {
        System.out.println("Question 1");
        // input
        LocalDate now = LocalDate.of(2025, 12, 13);
        List<Employee> employees = Arrays.asList(
                new Employee("E1", "Alice", "Eng", 50000, now.minusYears(3)),
                new Employee("E2", "Bob", "Eng", 80000, now.minusYears(1)),
                new Employee("E3", "Charlie", "Sales", 40000, now.minusYears(4)),
                new Employee("E4", "Diana", "Sales", 45000, now.minusYears(2)),
                new Employee("E5", "Eve", "Sales", 60000, now.minusYears(1))
        );
//        employees.forEach(System.out::println);
    // Output: Alice (E1, Eng, $50000), Bob (E2, Eng, $80000), Charlie (E3, Sales, $40000), etc.

        Map<String, DoubleSummaryStatistics> listMap  = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.summarizingDouble(Employee::getSalary)));

        listMap.entrySet().forEach(System.out::println);
    }

    private static List<Order> getOrderDetails() {
        LocalDate now = LocalDate.now();

        List<OrderItem> items1 = Arrays.asList(new OrderItem("P1", 2, 50.0),
                new OrderItem("P2", 1, 100.0),
                new OrderItem("P4", 5, 50.0),
                new OrderItem("P6", 7, 80.0),
                new OrderItem("P5", 10, 100.0));
        // Total: 200<br>
        List<OrderItem> items2 = Arrays.asList(new OrderItem("P1", 3, 50.0),
                new OrderItem("P3", 2, 30.0)); // Total: 210<br>
        List<OrderItem> items3 = Arrays.asList(new OrderItem("P2", 1, 100.0),
                new OrderItem("P3", 4, 30.0)); // Total: 220<br>
        List<Order> orders = Arrays.asList(  new Order("O1", "C1", items1, now.minusDays(10)),
                new Order("O2", "C2", items2, now.minusDays(5)),
                new Order("O3", "C1", items3, now));

        return orders;
    }

}
