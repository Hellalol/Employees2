package com.pillan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        //Functions
        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        String lastName = getLastName.apply(employees.get(1));
        System.out.println(lastName);

        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        Random random1 = new Random();
        for (Employee employee : employees) {
            if (random1.nextBoolean()) {
                System.out.println(getAName(getFirstName,employee));
            }else {
                System.out.println(getAName(getLastName,employee));
            }
        }

        //Chained function
        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        // BiFunction
        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) ->{
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        // IntUnaryOperator
        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10));

        //Chaining Consumer
        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello, World!");


        //Predicates String
        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "\n30 and under", employee -> employee.getAge() <= 30);
        printEmployeesByAge(employees, "\nEmployees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        // Int Predicates
        IntPredicate greaterThanFifteen = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThanFifteen.test(10));
        int a = 20;
        System.out.println(greaterThanFifteen.test(a + 5));

        System.out.println(greaterThanFifteen.and(lessThan100).test(50));
        System.out.println(greaterThanFifteen.and(lessThan100).test(15));


        //Supplier
        Random rand = new Random();
        Supplier<Integer> randomSupplier = () -> rand.nextInt(1000);
        for (int i = 0; i < 10 ; i++) {
            System.out.println(randomSupplier.get());
        }

        employees.forEach(employee -> {
            String lastName1 = employee.getName().substring(employee.getName().indexOf(' ') + 1);
            System.out.println("Last Name is: " + lastName1);
        });

    }

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText,
                                            Predicate<Employee> ageCondition) {
        System.out.println(ageText);
        System.out.println("=================");
        for (Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }
}
