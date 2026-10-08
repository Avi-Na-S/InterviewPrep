package org.example.dsa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFrequentWords {

  public static List<String> getTopKWords(List<String> words, int k) {

    return words.stream()
        // Count frequency of each word
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet()
        .stream()

        // Sort by frequency descending,
        // and alphabetically when frequency is same
//        .sorted(
//            Comparator.<Map.Entry<String, Long>>comparingLong(
//                    Map.Entry::getValue
//                )
//                .reversed()
//                .thenComparing(Map.Entry::getKey)
//        )

        .sorted(
            Map.Entry.<String, Long>comparingByValue()
                .reversed()
                .thenComparing(Map.Entry::getKey)
        )

        // Take top K
        .limit(k)

        // Return only words
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public static int totalCandles(int candles, int k) {

    int total = candles;
    int leftover = candles;

    while (leftover >= k) {

      int newCandles = leftover / k;

      total += newCandles;

      // Wax used to make new candles + unused wax
      leftover = newCandles + (leftover % k);
    }

    return total;
  }
  public static List<List<Integer>> split(int[] arr, int k) {

    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < arr.length; i += k) {

      List<Integer> chunk = new ArrayList<>();

      for (int j = i; j < Math.min(i + k, arr.length); j++) {
        chunk.add(arr[j]);
      }

      result.add(chunk);
    }
    return result;
  }
}
