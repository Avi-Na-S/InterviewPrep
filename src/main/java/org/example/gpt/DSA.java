package org.example.gpt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Exp{
  ArrayList<String> names = new ArrayList<>();

  public void setNames(String name){
    names.add(name);
  }

  public List<String> getNames(){
    return Collections.unmodifiableList(names);
  }
}

public class DSA {

  public static void main(String[] args) {

    int[] nums = {1,4,3,2,5,6};

    boubleSort(nums);
    selectionsort(nums);
    quickSort(nums, 0, nums.length - 1);
    joinInterval();



  }

  private static void joinInterval() {

    int[][] a = {{-3,-1}, {1,3}, {4,6}, {12,15}};
    int[] m = {2,10};

    List<int[]> result = new ArrayList<>();

    int i = 0;

    // Add intervals that come before m
    while (i < a.length && a[i][1] < m[0]) {
      result.add(a[i]);
      i++;
    }

    // Merge overlapping intervals
    while (i < a.length && a[i][0] <= m[1]) {
      m[0] = Math.min(m[0], a[i][0]);
      m[1] = Math.max(m[1], a[i][1]);
      i++;
    }

    // Add merged interval
    result.add(m);

    // Add remaining intervals
    while (i < a.length) {
      result.add(a[i]);
      i++;
    }

    // Print result
    for (int[] interval : result) {
      System.out.println(Arrays.toString(interval));
    }
  }

  static void quickSort(int[] nums, int low, int high) {

    if (low >= high) {
      return;
    }

    int pivotIndex = partition(nums, low, high);

    quickSort(nums, low, pivotIndex - 1);
    quickSort(nums, pivotIndex + 1, high);

    System.out.println(Arrays.toString(nums));
  }

  static int partition(int[] nums, int low, int high) {

    int pivot = nums[high];

    int i = low;

    for (int j = low; j < high; j++) {

      if (nums[j] < pivot) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

        i++;
      }
    }

    int temp = nums[i];
    nums[i] = nums[high];
    nums[high] = temp;

    return i;
  }


  private static void selectionsort(int[] nums) {

    for(int i =0;i<nums.length-1;i++){
      int minValue = i;

      for(int j = i+1;j<nums.length;j++){
        if(nums[j]<nums[minValue]){
          minValue = j;
        }
      }

      int temp = nums[i];
      nums[i]=nums[minValue];
      nums[minValue]=temp;

    }
    System.out.println(Arrays.toString(nums));
  }

  private static void boubleSort( int[] nums ) {
    for(int i =0;i<nums.length-1;i++){

      for(int j = 0; j<nums.length-1-i;j++){
        int first = nums[j];
        int second = nums[j+1];
        if(first>second){
          nums[j]= second;
          nums[j+1]=first;
        }
      }

    }

    System.out.println(Arrays.toString(nums));
  }
}
