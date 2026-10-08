package org.example.test;


//Hey, happy friday and weekend

import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Test {

  public static void main(String[] args){
    String test = "Hey, happy friday and weekend";

    String lowerTest = test.toLowerCase();
    Map<Character, Long> result = lowerTest.toLowerCase().chars().mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(character -> character, Collectors.counting()));
    OptionalInt first = lowerTest.chars()
        .filter(c -> Character.isAlphabetic(c) && lowerTest.indexOf(c) == lowerTest.lastIndexOf(c)).findFirst();

    first.ifPresent(f-> System.out.println((char)f));

    for(Entry<Character, Long>set:result.entrySet()){
      System.out.println(set.getKey() + " count is - " + set.getValue());
    }
  }
}
