package org.example.streams;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PracticeQuestion {

    public static void main(String[] args) {

        firstQuestion();

    }

    /*
    * Department Salary Stats: From a list of Employees,
    * group by department and compute min/max/average salary per group.
    * Output a map of department to stats (e.g., "Avg: X").
    * Filter out departments with <2 employees. (Medium: Basic grouping.)*/
    private static void firstQuestion() {

        // input
        LocalDate now = LocalDate.of(2025, 12, 13);
        List<Employee> employees = Arrays.asList(
                new Employee("E1", "Alice", "Eng", 50000, now.minusYears(3)),
                new Employee("E2", "Bob", "Eng", 80000, now.minusYears(1)),
                new Employee("E3", "Charlie", "Sales", 40000, now.minusYears(4)),
                new Employee("E4", "Diana", "Sales", 45000, now.minusYears(2)),
                new Employee("E5", "Eve", "Sales", 60000, now.minusYears(1))
        );
        employees.forEach(System.out::println);
    // Output: Alice (E1, Eng, $50000), Bob (E2, Eng, $80000), Charlie (E3, Sales, $40000), etc.

        Map<String, DoubleSummaryStatistics> listMap  = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.summarizingDouble(Employee::getSalary)));

//        listMap.forEach(System.out::println);
    }

}
