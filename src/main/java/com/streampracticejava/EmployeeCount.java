package com.streampracticejava;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeCount {
    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("John Doe", "Male"),
                new Employee("Jane Smith", "Female"),
                new Employee("David Lee", "Male"),
                new Employee("Sarah Jones", "Female"),
                new Employee("Michael Brown", "Male"));


        // Group employee by gender and count them

        Map<String, Long> genderCounts = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        // Print the results

        System.out.println("Total Male Employees: " + genderCounts.getOrDefault("Male",0l));
        System.out.println("Total Male Employees: " + genderCounts.getOrDefault("Female",0l));
    }
}
