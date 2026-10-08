package org.example.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.processing.Generated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@AllArgsConstructor
class Empl {

  private String name;
  private int age;
  private int salary;
  private int id;
}

public class OwnTest {

  public static void main(String[] args) {
    int[] numArr = {1, 2, 4, 5,3};
    int[] numArr2 = {7, 9,3};
    // findMissingNums(numArr);
    // findDuplicateNum(numArr);

   //sumTwoNum(numArr);

    mergeSortedArray(numArr, numArr2);
    String test = "Avinass";

    // reverseString(test);

    //  findDuplicateString(test);

    // findFirstDupChar(test);

    List<Empl> emps = new ArrayList<>(List.of(
        new Empl("avi", 24, 3800234, 4325),
        new Empl("ravi", 24, 12121, 4321),
        new Empl("nandu", 24, 2800234, 4328),
        new Empl("pavi", 23, 43232, 4322),
        new Empl("pavi", 21, 23232, 4324)
    ));

    // sortEmpWithNameAge(emps);
 //   groupEmpNameWthAgeSalary(emps);

  }

  private static void mergeSortedArray(int[] numArr, int[] numArr2) {

    System.out.println( IntStream.concat(Arrays.stream(numArr),Arrays.stream(numArr2)).distinct().boxed().sorted().toList());
  }

  private static void sumTwoNum(int[] numArr) {
    int sum = 6;
    Set<Integer> known = new HashSet<>();
    List<List<Integer>> set = new ArrayList<>();
    for(int n : numArr){
      int needed = sum-n;
      if(known.contains(needed)){
        set.add(List.of(needed,n));
      }
      known.add(n);
    }

    System.out.println(set);
  }

  private static void groupEmpNameWthAgeSalary(List<Empl> emps) {
    System.out.println(emps.stream().collect(Collectors.groupingBy(
        Empl::getAge, Collectors.mapping(Empl::getName, Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
              Collections.sort(list);
              return list;
            }
        )))));
  }

  private static void sortEmpWithNameAge(List<Empl> emps) {

    emps.stream().sorted(Comparator.comparing(Empl::getName).thenComparing(Empl::getAge))
        .forEach(e -> System.out.println(e.getId()));

  }

  private static void findFirstDupChar(String test) {
    if (test == null || test.isEmpty()) {
      System.out.println("Invalid value, input is blank");
      return;
    }

    LinkedHashMap<Character, Long> map = test.chars().mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));
    map.entrySet().stream().filter(m -> m.getValue() > 1).findFirst()
        .ifPresentOrElse(m -> System.out.println(m.getKey()),
            () -> System.out.println("No value found"));
  }

  private static void findDuplicateString(String test) {
    var c = test.toLowerCase().toCharArray();
    Set<Character> charSetSeen = new HashSet<>();
    Set<Character> charSetDup = new HashSet<>();

    for (char v : c) {
      if (!charSetSeen.add(v)) {
        charSetDup.add(v);
      }
    }

    System.out.println(charSetDup);
  }


  private static void reverseString(String test) {
    var c = test.toCharArray();
    for (int i = c.length - 1; i >= 0; i--) {
      System.out.print(c[i]);
    }
  }


  private static void findDuplicateNum(int[] numArr) {
    Set<Integer> seen = new HashSet<>();
    Set<Integer> dup = new HashSet<>();

    for (int n : numArr) {
      if (!seen.add(n)) {
        dup.add(n);
      }
    }

    System.out.println(dup);
  }

  private static void findMissingNum(int[] numArr) {
    if (numArr == null || numArr.length == 0) {
      System.out.println("invalid input, No data found");
      return;
    }
    int n = numArr.length + 1;
    int exp = (n * (n + 1)) / 2;

    int numArrTotal = Arrays.stream(numArr).sum();
    System.out.printf("The missing number is %d", exp - numArrTotal);

  }

  private static void findMissingNums(int[] numArr) {
    if (numArr == null || numArr.length == 0) {
      System.out.println("invalid input, No data found");
      return;
    }
    int init = 1;
    int lastNum = findmax(numArr);
    Set<Integer> numbers = new HashSet<>();

    for (int num : numArr) {
      numbers.add(num);
    }

    for (int i = 1; i <= lastNum; i++) {

      if (!numbers.contains(i)) {
        System.out.println(i);
      }

    }

  }

  private static int findmax(int[] numArr) {

    int max = numArr[0];

    for (int n : numArr) {
      if (max < n) {
        max = n;
      }
    }
    return max;
  }


}
