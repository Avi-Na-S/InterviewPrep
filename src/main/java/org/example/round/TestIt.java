package org.example.round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class TestIt {

  public static void main(String[] args) {
    String a  = "Start small. Ship something.";
    System.out.print(a.chars().mapToObj(s->(char)s).collect(Collectors.groupingBy(s->s,LinkedHashMap::new,Collectors.counting())).entrySet().stream().filter(s->s.getValue()==1).map(s->s.getKey()).findFirst().orElse(' '));

  }

}
