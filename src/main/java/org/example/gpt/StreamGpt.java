package org.example.gpt;

import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.Getter;

@Data
@AllArgsConstructor
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private int age;
    private List<String> skills;
}

@Getter
@AllArgsConstructor
class Employ {

    String name;
    int age;
    int salary;
    String dep;

}


// https://chatgpt.com/share/6a68d798-3ff8-83ee-b29f-2c1c7eff7263


public class StreamGpt {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1,"John","IT",90000,29,List.of("Java","Spring","AWS")),
                new Employee(2,"David","HR",45000,40,List.of("Excel","Recruitment")),
                new Employee(3,"Alice","IT",120000,35,List.of("Java","Kafka","Docker")),
                new Employee(4,"Bob","Finance",75000,45,List.of("SAP","Excel")),
                new Employee(5,"Chris","IT",60000,26,List.of("Spring","SQL")),
                new Employee(6,"Emma","Finance",110000,31,List.of("Oracle","SQL")),
                new Employee(7,"Sophia","HR",52000,37,List.of("Recruitment","Payroll")),
                new Employee(8,"John","IT",120000,33,List.of("Java","Microservices"))
        );

        employees.sort(Comparator.comparing(Employee::getName));


        System.out.println("\nEmployees salary > 80000");

        employees.stream().filter(emp -> emp.getSalary()>80000).forEach(System.out::println);

        System.out.println("get Departments name");
        employees.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        System.out.println("\nHighest Salary third salary");

        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(2).limit(1).forEach(System.out::println);

        System.out.println("\nNo of  IT emp");
        System.out.println( employees.stream().filter(em->"IT".equals(em.getDepartment())).count());

        System.out.println("\nMin Age");
        System.out.println(employees.stream().min(Comparator.comparingInt(Employee::getAge)));

        System.out.println("\nMax Age");
        System.out.println(employees.stream().max(Comparator.comparingInt(Employee::getAge)));

        System.out.println("\n total salary");
        System.out.println(employees.stream().mapToDouble(Employee::getSalary).sum());
        System.out.println(employees.stream().map(Employee::getSalary).reduce(0d, Double::sum));
 //anyMatch(), allMatch(), noneMatch(), findFirst(), findAny()

        System.out.println("\n count by department");
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting())));

        System.out.println("\nAverage Salary by Department");
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))));

        System.out.println("\nHighest Paid Employee per Department");
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))));

        System.out.println("\npartitioningBy");
        System.out.println( employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary()>80000,Collectors.mapping(Employee::getName,Collectors.toList()))));

        System.out.println("\n to map");
        System.out.println(employees.stream().collect(Collectors.toMap(Employee::getId,Employee::getName)));

        System.out.println("\n Group by department, and get only names");
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList()))));

        // collectingAndThen()
        //====================================================

        List<Employee> top5 =
                employees.stream()
                        .sorted(Comparator.comparing(Employee::getSalary).reversed())
                        .limit(5)
                        .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        System.out.println(top5);

        //summarizingDouble()

        DoubleSummaryStatistics stats =
                employees.stream()
                        .collect(Collectors.summarizingDouble(Employee::getSalary));


        System.out.println(employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summarizingDouble(Employee::getSalary))));

        System.out.println(stats);


        // peek()

        employees.stream()
                .peek(e -> System.out.println("Processing : " + e.getName()))
                .map(Employee::getName)
                .toList();

        // IntStream

        IntStream.rangeClosed(1,10)
                .filter(i -> i%2==0)
                .forEach(System.out::println);


        System.out.println("\n Duplicate Emp name");
        Set<String> empName = new HashSet<>();
        System.out.println(employees.stream().map(Employee::getName).filter(name -> !empName.add(name)).collect(Collectors.toSet()));

        System.out.println("\n Group emp and sort by salary");
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(Collectors.toList(),list->list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).map(Employee::getName).toList()))));

        System.out.println("\n Dep with higher AVG");
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))).entrySet().stream().max(Comparator.comparingDouble(Map.Entry::getValue)).get());

        System.out.println("\n Emp with longest name");
        System.out.println(employees.stream().max(Comparator.comparingInt(e->e.getName().length())).map(Employee::getName).orElse(""));

        System.out.println("\n Merge two employee lists and remove duplicates");
        List<Employee> ssss = List.of(
                new Employee(1,"John","IT",90000,29,List.of("Java","Spring","AWS")),
                new Employee(9,"David","HR",45000,40,List.of("Excel","Recruitment"))
        );
        System.out.println(Stream.concat(employees.stream(),ssss.stream()).collect(Collectors.toMap(Employee::getId, Employee::getName,(e1, e2)-> e1)).values());


        List<Employ> employs = List.of(
            new Employ("Alice", 27, 100000, "IT"),
            new Employ("Raja", 27, 90000, "IT"),
            new Employ("Charlie", 26, 100000, "IT"),

            new Employ("David", 25, 70000, "HR"),
            new Employ("Eva", 27, 75000, "HR"),
            new Employ("Frank", 25, 75000, "HR"),

            new Employ("John", 26, 120000, "Finance"),
            new Employ("Mike", 25, 95000, "Finance"),
            new Employ("Sam", 25, 95000, "Finance")
        );
//    System.out.println(employs.stream().filter(e -> e.age > 25).collect(
//        Collectors.groupingBy(Employ::getDep, Collectors.collectingAndThen(Collectors.toList(),
//            l -> l.stream().max(Comparator.comparingInt((Employ e) -> e.salary).thenComparing( Employ::getAge,
//                    Comparator.reverseOrder())).map(e -> e.name)
//                .orElse("")))));

        System.out.println(employs.stream()
            .filter(e -> e.getAge() > 25)
            .collect(Collectors.groupingBy(
                Employ::getDep,
                Collectors.collectingAndThen(
                    Collectors.maxBy(
                        Comparator.comparingInt(Employ::getSalary)
                            .thenComparing(

                                Comparator.comparing(Employ::getAge).reversed()
                            )
                    ),
                    employee -> employee.get().getName()
                )
            )));


    }
}
