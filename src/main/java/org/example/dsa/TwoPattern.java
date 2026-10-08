package org.example.dsa;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TwoPattern {

  public static void main(String[] args) {

   // easyPointer();

    int[] nums = {-1, 0, 1, 2, -1, -4};
    int traget = 0;

    nums = Arrays.stream(nums).sorted().toArray();

  }

  private static void easyPointer() {

    //Time  : O(n)
    //Space : O(1)
    //Find two numbers whose sum equals target in a sorted array.

    int[] numbers = {-2,2, 7, 11, 15};
    int target = 9;

    int left = 0;
    int right =numbers.length-1;

    while (left<right){
      int sum =numbers[left]+numbers[right];
      if(sum>target){
        right--;
      }else if(sum<target){
        left++;
      }else {
        System.out.println(numbers[left]+" "+ numbers[right]);
        left++;
        right--;
      }
    }

  }

}
