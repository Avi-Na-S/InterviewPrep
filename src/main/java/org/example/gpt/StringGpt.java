package org.example.gpt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringGpt {
    public static void main(String[] args) {

      String pro = "programming";

      HashMap<Character, Integer> fer = new HashMap<>();

      for(char cc:pro.toCharArray()){
        fer.put(cc, fer.getOrDefault(cc,0)+1);
      }

      System.out.println(fer);

      System.out.println( lengthOfLongestSubstring("abcadeafgth"));

      String reve  = "avinas";

      char[] sarr =reve.toCharArray();
//
//      for(int i = 0; i<reve.length()/2;i++){
//        sarr[i] = reve.charAt(reve.length()-1-i);
//        sarr[reve.length()-1-i]=reve.charAt(i);
//      }
      int left  = 0;
      int right = reve.length()-1;

      while (left<right){
        sarr[left]=reve.charAt(right);
        sarr[right]=reve.charAt(left);
        left++;
        right--;
      }

      System.out.println(new String(sarr));

      String zz = "a12b3c5d2";

      StringBuilder sb = new StringBuilder();

      for(int i = 0; i<zz.length()-1;i++){
        sb.append(zz.charAt(i));
        char current = zz.charAt(i);

        StringBuilder sbx = new StringBuilder();
        while (i<zz.length()-1 && Character.isDigit(zz.charAt(i+1))){
          sbx.append(zz.charAt(++i));
        }
        IntStream.range(0,Integer.parseInt(sbx.toString())-1).forEach(
          bxc->sb.append(current)
        );
      }

      System.out.println(sb);



      //Encode
      String s = "aabbbccccddc";

      System.out.println("\n");
      s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()))
          .forEach((key, value) -> System.out.print(key +""+ value));
      System.out.println("\n");

      char current = s.charAt(0);
      int count = 1;

      for (int i = 1; i < s.length(); i++) {

        if (s.charAt(i) == current) {
          count++;
        } else {
          System.out.print(current);
          System.out.print(count);

          current = s.charAt(i);
          count = 1;
        }
      }

// Don't forget the last group
      System.out.print(current);
      System.out.print(count);

      //Decode
      String input = "a12b1c3";

      StringBuilder result = new StringBuilder();

      for (int i = 0; i < input.length(); ) {
        char ch = input.charAt(i++);

        int counted = 0;

        while (i < input.length() && Character.isDigit(input.charAt(i))) {
          counted = counted * 10 + (input.charAt(i) - '0');
          i++;
        }

        for (int j = 0; j < counted; j++) {
          result.append(ch);
        }
      }

      System.out.println(result);

        String name  = "Avinas Lingam";
//NonRepeatedChar ---------------------
//        String name1= name.toLowerCase();
//        name1.chars().mapToObj(c->(char)c).filter(c-> Character.isAlphabetic(c)&&name1.indexOf(c)==name1.lastIndexOf(c)).findFirst().ifPresentOrElse(
//                System.out::println,
//                () -> System.out.println("No unique character")
//        );

        //gpt


        // dulicate char in string -----------

        Map<Character, Long> frequency1 = name.toLowerCase().chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c->c, Collectors.counting()));

        frequency1.entrySet().stream().filter(f->f.getValue()>1).forEach(fe-> System.out.println(fe.getKey()+"-"+fe.getValue()));

        // max occurency of char
        Map<Character, Long> a = name.toLowerCase().chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(a.entrySet().stream().max(Comparator.comparingInt(c->c.getValue().intValue())).get().getKey());

        // reverse the word in sentence
        // not sure how to do with stream

        String se = "Java is powerful";

        var v = se.split(" ");
        var rev = new StringBuilder();
        for(int i = v.length-1;i>=0;i--){
            rev.append(v[i]).append(" ");
        }

        System.out.println(rev.toString().trim());

        String results =
                IntStream.range(0, v.length)
                        .mapToObj(i -> v[v.length-1-i])
                        .collect(Collectors.joining(" "));

        //Check if two strings are anagrams
        String q = "listen";
        String q1 ="silent";
        //Incorrect approach
        //System.out.println(q.chars().mapToObj(q2->(char)q2).allMatch(q2-> q1.contains(q2+"")));

      //q.chars().mapToObj(c->(int)c).collect(Collectors.groupingBy(c->c,Collectors.counting())).equals(q1.chars().mapToObj(c->(int)c).collect(Collectors.groupingBy(c->c,Collectors.counting())))

        boolean isAnagram =anagram(q,q1);


        System.out.println(isAnagram);
    }

  public static int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();

    int left = 0;
    int max = 0;

    for (int right = 0; right < s.length(); right++) {

      while (set.contains(s.charAt(right))) {
        set.remove(s.charAt(left));
        left++;
      }

      set.add(s.charAt(right));

      max = Math.max(max, right - left + 1);
    }

    return max;
  }

  public Character nonRepeatedChar(String name){
             Map<Character, Long> frequency =
                name.toLowerCase()
                        .chars()
                        .mapToObj(c -> (char)c)
                        .collect(Collectors.groupingBy(
                                c -> c,
                                LinkedHashMap::new,
                                Collectors.counting()
                        ));
        var opt=   frequency.entrySet()
                .stream()
                .filter(e -> e.getValue()==1)
                .findFirst();
        return opt.map(Map.Entry::getKey).orElse(null);
    }

    private static boolean anagram(String q,String q1){
        return  q.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ))
                .equals(
                        q1.chars()
                                .mapToObj(c -> (char)c)
                                .collect(Collectors.groupingBy(
                                        c -> c,
                                        Collectors.counting()
                                ))
                );
    }
}
