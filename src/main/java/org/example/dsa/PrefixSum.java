package org.example.dsa;

import java.util.*;
import java.util.stream.Collectors;

public class PrefixSum {

  public static void main(String[] args) {
     easyPrefix();
     mediumPrefix();
     easyPrefix2();
    // hardPrefix();
  }

  private static void easyPrefix2() {
    // find contigous subarrat with largest sum
    int[] a = {1,2,3,4,-100,6,8,9};
    int sum = a[0];
    int max = sum;
    for(int i =1;i<a.length;i++){
      sum = Math.max(a[i],sum+a[i]);
      max= Math.max(max,sum);
    }

    System.out.print(max);
  }

  private static void mediumPrefix() {
//Subarray Sum Equals K
    //Time  : O(n)
    //Space : O(n)

    // here we use prefix sum via map, the prefix sum values are stored in map key and
    // the occurance are stored in value to add the count. We start the map with 0 because this will handle the case
    // if the k value is present in nums array then we will get the req value i-k will be zero so we have already set 0 in key
    // with one occurance by default

    int[] nums = {3, 4, -2, 5, -3, 2, 7, -4, 6};
    int k = 5;

    Map<Integer, Integer> map = new LinkedHashMap<>();

    // Prefix sum 0 exists once before starting
    map.put(0, 1);
    int count = 0;
    int sum = 0;

    for (int i : nums) {
      sum = sum + i;
      int needed = sum - k;

      if (map.containsKey(needed)) {
        count = count + map.get(needed);
      }
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    System.out.println(count);
  }

  private static void easyPrefix() {
    //Running Sum

    //Time  : O(n)
    //Space : O(n)

    // real time use case

    int[] a = {10, 20, 30, 80, 120, 140};

    int[] p = new int[a.length];

    p[0] = a[0];

    for (int i = 1; i < a.length; i++) {
      p[i] = p[i - 1] + a[i];
    }

    System.out.println(Arrays.toString(p));


  }

  private static void hardPrefix() {
    //Count of Range Sum
    //Time  : O(n log n)
    //Space : O(n)
    int[] nums = {-2, 5, -1};
    int lower = -2;
    int upper = 2;

    long[] prefix = new long[nums.length + 1];

    for (int i = 0; i < nums.length; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }

    System.out.println(mergeSort(
        prefix,
        0,
        prefix.length,
        lower,
        upper
    ));
  }

  private static int mergeSort(
      long[] prefix,
      int left,
      int right,
      int lower,
      int upper) {

    if (right - left <= 1) {
      return 0;
    }

    int mid = left + (right - left) / 2;

    int count = 0;

    count += mergeSort(
        prefix,
        left,
        mid,
        lower,
        upper
    );

    count += mergeSort(
        prefix,
        mid,
        right,
        lower,
        upper
    );

    int low = mid;
    int high = mid;

    for (int i = left; i < mid; i++) {

      while (low < right &&
          prefix[low] - prefix[i] < lower) {
        low++;
      }

      while (high < right &&
          prefix[high] - prefix[i] <= upper) {
        high++;
      }

      count += high - low;
    }

    long[] temp = new long[right - left];

    int i = left;
    int j = mid;
    int index = 0;

    while (i < mid && j < right) {

      if (prefix[i] <= prefix[j]) {
        temp[index++] = prefix[i++];
      } else {
        temp[index++] = prefix[j++];
      }
    }

    while (i < mid) {
      temp[index++] = prefix[i++];
    }

    while (j < right) {
      temp[index++] = prefix[j++];
    }

    System.arraycopy(
        temp,
        0,
        prefix,
        left,
        temp.length
    );

    return count;
  }

}
