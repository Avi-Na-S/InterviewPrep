package org.example.leet.training;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestOne implements TestInterface, TestInterfaceTwo {
public static void main(String[] args){
  List<Integer> a = List.of(2,4,5,6);
  System.out.println(a.stream().collect(Collectors.averagingInt(value -> value)));
  System.out.println(a.stream().sorted(Comparator.comparingInt(value -> value)).skip(1).limit(1).toList().get(0));
  System.out.println(a.stream().max(Comparator.comparingInt(value -> value)).get());
  String v = "avi";
  System.out.println((char)v.chars().max().getAsInt());
}

  @Override
  public int sub(int a) {
    return 0;
  }
}
