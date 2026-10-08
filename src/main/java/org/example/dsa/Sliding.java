package org.example.dsa;

import java.util.*;

public class Sliding {

  public static void main(String[] args) {
findlargestNonRepeatingSentence();
  }

  private static void findlargestNonRepeatingSentence() {
    String s = "Start small. Ship something.";

    int left = 0;
    int max = 0;

    Set<Character> set = new HashSet<>();

    for(int right = 0; right<s.length();right++){

      while(set.contains(s.charAt(right))){
        set.remove(s.charAt(left));
        left++;
      }

      set.add(s.charAt(right));
      max = Math.max(max,right-left+1);

    }

    System.out.print(max);


    Map<Character, Integer> map = new HashMap<>();

    for (int right = 0; right < s.length(); right++) {

      char ch = s.charAt(right);

      if (map.containsKey(ch)) {
        left = Math.max(left, map.get(ch) + 1);
      }

      map.put(ch, right);

      max = Math.max(max, right - left + 1);
    }

    System.out.println(max);
  }

}
