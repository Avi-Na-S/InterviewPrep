package org.example.leet.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringRemoveSpace {

  public static void main(String[] args){
    String test = "asda sdas fa  sfa";
    String as = test.chars().filter(a -> a != ' ').mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining());
    System.out.println(as);
    List<String> list= Arrays.asList("as", "ada","eeee", "ada");
    Set<String> asd = list.stream()
        .max((a, b) -> b.compareTo(a)).stream().limit(1)
        .collect(Collectors.toSet());
//      list.sort(String::compareTo);
//      System.out.println(list.getFirst());
    System.out.println(asd);

    Map<Integer, List<String>> ans = Stream.of(test.split(" "))
        .collect(Collectors.groupingBy(l -> l.length()));
    ans.entrySet().forEach(e->System.out.println(e.getKey() + " -- "+ e.getValue()));

    List<List<String>> list2 = new ArrayList<>();

list2.add(Arrays.asList("sd","sddas","safas"));
    list2.add(Arrays.asList("aasd","aaas","aasafas"));
    List<String> ese = list2.stream().flatMap(ls -> ls.stream()).toList();

    List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 12, 14);

    IntSummaryStatistics stats = numbers.stream()
        .mapToInt(Integer::intValue)
        .summaryStatistics();

    System.out.println("Count: " + stats.getCount());
    System.out.println("Max: " + stats.getMax());
    System.out.println("Min: " + stats.getMin());
    System.out.println("Sum: " + stats.getSum());
    System.out.println("Average: " + stats.getAverage());
  }

}
