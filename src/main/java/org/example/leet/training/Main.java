package org.example.leet.training;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {

    String title = "hello Avinas, Good Morning";
    char[] charArray = title.toCharArray();
    List<Character> v = List.of('e', 'o', 'a', 'i', 'u');
    Map<Character, Integer> vCount = new HashMap();
    for (char a : charArray) {
      Integer count = vCount.getOrDefault(a, 0);
      if (v.contains(a)) {
        vCount.put(a, ++count);
      }
    }
    Set<Entry<Character, Integer>> ent = vCount.entrySet();
    int c = 0;
    Character higherChar = null;
    for (Entry<Character, Integer> set : ent) {
      if (c < set.getValue()) {
        c = set.getValue();
        higherChar = set.getKey();
      }
    }
    for (int i = 0; i < charArray.length; i++) {
      if (v.contains(charArray[i])) {
        charArray[i] = higherChar;
      }
    }

    System.out.println(new String(charArray));
  }
}


