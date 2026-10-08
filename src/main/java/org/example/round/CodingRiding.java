package org.example.round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class CodingRiding {


  //java-task1-general-Reverse the array of characters
  public static void main(String[] args) {
    String s1 = "AvinasLingam";
    String s2 = "AvianasLinagam";
    int len = Math.max(s1.length(), s2.length());

    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        dp[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1))
            ? dp[i - 1][j - 1] + 1
            : Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }

    System.out.print(dp[m][n]);

    //Reverse an integer array
    reverseIntegerArray();
    //Reverse a String
    reverseString();
    // Reverse the order of words
    reverseStringWord();
    //Reverse each word in a String
    reverseEachWord();
    //Reverse an integer without converting it to a String
    reverseInteger();
    //Reverse only a portion of an array
    reversePortion();
    //Find the second highest length in a given sentence of words
    secondHighest();

    //Move all Zeros to right
    zeroRight();

    //filter palindrome
    palindromeCheck();

    // sort the sorted array
    sortArray();

    //Convert sentence to hashtag
    convertHashtag();

    //Filter out only the valid integers from a list of strings
    findInt();

    // Calculate sum of the current element and up to the previous k-1 elements
    calCurrentEle();

    //
    anagramSet();

  }

  private static void anagramSet() {
    String[] str = {
        "eat", "tea", "bat",
        "atb", "san", "saa", "asn"
    };

    Map<String, List<String>> map = new HashMap<>();

    for (String word : str) {

      char[] chars = word.toCharArray();

      Arrays.sort(chars);

      String key = new String(chars);

      map.computeIfAbsent(key, k -> new ArrayList<>())
          .add(word);
    }

    System.out.println(map.values());

    System.out.print(Arrays.stream(str).collect(Collectors.groupingBy(a -> {
      char[] c = a.toCharArray();
      Arrays.sort(c);
      return new String(c);
    })).values());
  }

  private static void calCurrentEle() {

    List<Integer> a = List.of(1, 2, 3, 4, 5, 6);
    int k = 3;
    int len = a.size() - k + 1;
    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < len; i++) {
      List<Integer> d = new ArrayList<>();
      for (int j = i; j < k + i; j++) {
        d.add(a.get(j));
      }
      res.add(d);
    }

    for (List<Integer> rs : res) {
      System.out.println(rs + " " + rs.stream().reduce(0, Integer::sum));
    }

    List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
    k = 3;

    if (k <= 0 || k > input.size()) {
      return;
    }

    List<Integer> result = new ArrayList<>();

    int windowSum = 0;

    // Calculate the first window
    for (int i = 0; i < k; i++) {
      windowSum += input.get(i);
    }

    result.add(windowSum);

    // Slide the window
    for (int i = k; i < input.size(); i++) {

      windowSum += input.get(i);       // add new element
      windowSum -= input.get(i - k);   // remove old element

      result.add(windowSum);
    }

    System.out.println(result);
  }

  private static void findInt() {

    List<String> input = List.of("10", "sfasf", "-23", "faf", "0", "@#");

    System.out.println(input.stream().filter(CodingRiding::isDigit).toList());

  }

  private static boolean isDigit(String a) {

    if (a.startsWith("-") || a.startsWith("+")) {
      a = a.substring(1);
    }
    for (char c : a.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;

  }

  private static void convertHashtag() {

    String input = "Java  is   @awesome!";
    StringBuilder st = new StringBuilder("#");

    for (String word : input.split("\\s+")) {

      word = word.replaceAll("[^A-Za-z0-9]", "");
      if (!word.isEmpty()) {
        st.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
      }

    }

    System.out.println("#" + Arrays.stream(input.split("\\s+")).filter(a -> !a.isEmpty())
        .map(a -> a.replaceAll("[^A-Za-z0-9]", "")).map(StringUtils::capitalize).collect(
            Collectors.joining()));

    System.out.println(st);
  }

  private static void sortArray() {

    int[] a = {1, 2, 3, 4};
    int[] b = {3, 4, 5, 6};

    System.out.println(
        Stream.concat(Arrays.stream(a).boxed(), Arrays.stream(b).boxed()).sorted().toList());

    int[] c = new int[a.length + b.length];

    int i = 0;
    int j = 0;
    int k = 0;

    while (i < a.length && j < b.length) {
      if (a[i] < b[j]) {
        c[k++] = a[i++];
      } else {
        c[k++] = b[j++];
      }
    }

    // Copy remaining elements from a
    while (i < a.length) {
      c[k++] = a[i++];
    }

    // Copy remaining elements from b
    while (j < b.length) {
      c[k++] = b[j++];
    }

    System.out.println(Arrays.toString(c));
  }

  private static void palindromeCheck() {

    String input = "madam is a level test for radar";

    first:
    for (String word : input.split(" ")) {

      for (int i = 0; i <= (word.length() - 1) / 2; i++) {
        if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
          continue first;
        }
      }
      System.out.println(word);
    }

    // way 2
    for (String word : input.split(" ")) {

      if (isPalindrome(word)) {
        System.out.println(word);
      }
    }

  }

  private static boolean isPalindrome(String word) {

    int left = 0;
    int right = word.length() - 1;

    while (left < right) {
      if (word.charAt(left) != word.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }

    return true;

  }

  private static void zeroRight() {

    // way 1

    int[] input = {0, 1, 0, 3, 12};

    for (int j = 0; j < input.length; j++) {
      for (int i = 0; i < input.length; i++) {
        if (input[i] == 0 && i < input.length - 1) {
          input[i] = input[i + 1];
          input[i + 1] = 0;
        }
      }
    }

    System.out.println(Arrays.toString(input));

    // way 2

    input = new int[]{0, 1, 0, 3, 12};

    int[] c = new int[input.length];
    int index = 0;

    for (int i = 0; i < input.length; i++) {
      if (input[i] != 0) {
        c[index++] = input[i];
      }
    }

    System.out.println(Arrays.toString(c));

    // way3

    input = new int[]{0, 1, 0, 3, 12};
    index = 0;

    for (int i = 0; i < input.length; i++) {
      if (input[i] != 0) {
        input[index++] = input[i];
      }
    }

    while (index < input.length) {
      input[index++] = 0;
    }

    System.out.println(Arrays.toString(input));
  }

  private static void secondHighest() {

    String input = "Java is a powerful language";

    List<Entry<String, Integer>> sd = Arrays.stream(input.split(" "))
        .collect(Collectors.toMap(a -> a, String::length)).entrySet().stream()
        .sorted((a, b) -> b.getValue().compareTo(a.getValue())).skip(1).limit(1).toList();

    System.out.println(sd.getFirst().getValue());

    System.out.println(Arrays.stream(input.split(" "))
        .collect(Collectors.toMap(a -> a, String::length)).values().stream().distinct()
        .sorted(Comparator.reverseOrder()).skip(1).limit(1).toList().getFirst());

    int max = -1;
    int secondMax = -1;
    String[] st = input.split(" ");
    for (String w : st) {

      if (w.length() > max) {
        secondMax = max;
        max = w.length();
      } else if (w.length() > secondMax && max != w.length()) {
        secondMax = w.length();
      }
    }
    System.out.println(secondMax);

    System.out.println(
        Arrays.stream(st).map(String::length).collect(() -> new int[]{-1, -1}, (r, len) -> {
          if (len > r[0]) {
            r[1] = r[0];
            r[0] = len;
          } else if (len > r[1] && len < r[0]) {
            r[1] = len;
          }
        }, (r1, r2) -> {
        })[1]);
  }

  private static void reversePortion() {
    int[] input = {1, 2, 3, 4, 5, 6};

    int startIndex = 1;
    int lastIndex = 4;

    for (int i = startIndex; i <= lastIndex / 2; i++) {
      int temp = input[i];
      input[i] = input[lastIndex - i + 1];
      input[lastIndex - i + 1] = temp;
    }

    System.out.println(Arrays.toString(input));


  }

  private static void reverseInteger() {

    int input = 12345;

    // no idea

  }

  private static void reverseEachWord() {
    String input = "Java is easy";

    String[] st = input.split(" ");

    String[] rs = new String[st.length];
    for (int i = 0; i <= st.length - 1; i++) {

      char[] a = st[i].toCharArray();

      for (int j = 0; j <= (a.length - 1) / 2; j++) {
        char temp = a[j];
        a[j] = a[a.length - 1 - j];
        a[a.length - 1 - j] = temp;
      }
      rs[i] = new String(a);
    }

    System.out.println(String.join(" ", rs));
  }

  private static void reverseStringWord() {

    String input = "Java is easy";

    // way 1
    String[] st = input.split(" ");

    for (int i = 0; i <= (st.length - 1) / 2; i++) {
      String temp = st[i];
      st[i] = st[st.length - 1 - i];
      st[st.length - 1 - i] = temp;
    }

    System.out.println(String.join(" ", st));

    // way 2
    String[] sts = input.split(" ");

    for (int i = sts.length - 1; i >= 0; i--) {
      System.out.print(sts[i] + (i == 0 ? "" : " "));
    }
    System.out.println();
  }

  private static void reverseString() {
    String a = "hello";

    // way 1
    char[] ac = new char[a.length()];

    for (int i = 0; i <= (a.length() - 1) / 2; i++) {
      ac[i] = a.charAt(a.length() - 1 - i);
      ac[a.length() - 1 - i] = a.charAt(i);
    }

    System.out.println(new String(ac));

    // way 2
    System.out.println(new StringBuilder(a).reverse());

  }

  private static void reverseIntegerArray() {
    int[] a = {1, 2, 3, 4, 5};
    int[] r = new int[a.length];

    // way 1 single pointer

    for (int i = a.length - 1; i >= 0; i--) {
      r[a.length - 1 - i] = a[i];
    }

    System.out.println(Arrays.toString(r));

    // way 2 two pointer

    int left = 0;
    int right = a.length - 1;
    while (left < right) {
      int temp = a[right];
      a[right] = a[left];
      a[left] = temp;
      left++;
      right--;
    }

    System.out.println(Arrays.toString(a));

    a = new int[]{1, 2, 3, 4, 5};
    // way 3 default method

    ArrayList<Integer> ls = Arrays.stream(a).boxed()
        .collect(Collectors.toCollection(ArrayList::new));

    Collections.reverse(ls);

    System.out.println(ls);

  }

}
