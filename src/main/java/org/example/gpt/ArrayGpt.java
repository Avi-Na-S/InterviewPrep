package org.example.gpt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayGpt {

    public static void main(String[] args) {

      // profit

      int[] prices = {2,4,1};
      int min = Integer.MAX_VALUE;
      int max = 0;
      for (int p : prices){
        if(max<p){
          max = p;
        }
        if(min>p){
          min =p;
          max =p;
        }
      }

      System.out.printf("Buy at %d%n",min);
      System.out.printf("Sell at %d%n",max);
      System.out.printf("profit at %d%n",max-min);

        // Two Sum Problem
        int[] nums = {2,7,11,15};
        int target = 9;
        Map<Integer, Integer> seen = new HashMap<>();
        List<List<Integer>> pair = new ArrayList<>();
        for(int i = 0; i<nums.length;i++){
            int current = nums[i];
            int needNum = target-current;
            if(seen.containsKey(needNum)){
                pair.add(List.of(seen.get(needNum), i));
            }

            seen.put(current,i);
        }

        System.out.println(pair);

        //Find missing number
        int[] arr = {1,2,4,6};
//        int sum = 1;
//        for(int i = 0; i<arr.length;i++){
//            if(arr[i] != sum){
//                System.out.println(sum+" - is missing");
//                i--;
//            }
//            ++sum;
//        }
       //  use n*(n+1)/2 to find total of the numbers where n =6

        int n = 6;

        int expected = n*(n+1)/2;

        int actual = Arrays.stream(arr).sum();

        System.out.println(expected-actual);


        //Find duplicate number

        int[] arr1 = {1,3,4,2,2};
        Set<Integer> seened = new HashSet<>();
        for(int i = 0; i<arr1.length;i++){
            if(seened.contains(arr1[i])){
                System.out.println("duplicate:- "+arr1[i]);
            }

            seened.add(arr1[i]);
        }

        //Merge two sorted arrays
        int[] a = {1,3,5};
        int[] b = {2,4,6};

        System.out.println(Arrays.toString(IntStream.concat(Arrays.stream(a), Arrays.stream(b)).toArray()));

        // find first two highest repeated number.

      List<Integer> numList = List.of(1, 1, 1, 2, 2, 3, 4, 4, 4, 4);

      Map<Integer, Long> numMap = numList.stream()
          .collect(Collectors.groupingBy(na -> na, Collectors.counting()));
      System.out.println(
          numMap.entrySet().stream().sorted((aa,ba)->ba.getValue().compareTo(aa.getValue()))
              .limit(2).toList());
    }
}
