package org.example.dsa;

public class NextGreater {

  public static int[] nextGreater(int[] nums) {

    //{1, 2, 3, 9, 8, 7, 6};

    // Step 1: Find the first decreasing element from right
    int i = nums.length - 2;

    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }

    // Entire array is in descending order
    if (i < 0) {
      return new int[]{-1};
    }

    // Step 2: Find the smallest element greater than nums[i]
    int j = nums.length - 1;

    while (nums[j] <= nums[i]) {
      j--;
    }

    // Step 3: Swap
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;

    // Step 4: Reverse the suffix
    reverse(nums, i + 1, nums.length - 1);

    return nums;
  }

  private static void reverse(int[] nums, int left, int right) {
    while (left < right) {
      int temp = nums[left];
      nums[left] = nums[right];
      nums[right] = temp;

      left++;
      right--;
    }
  }

  public static void main(String[] args) {

    int[] arr1 = {1, 2, 3};
    int[] result1 = nextGreater(arr1);

    int[] arr2 = {2, 1};
    int[] result2 = nextGreater(arr2);

    int[] arr3 = {1, 2, 3, 9, 8, 7, 6};
    int[] result3 = nextGreater(arr3);

    System.out.println(java.util.Arrays.toString(result1));
    System.out.println(java.util.Arrays.toString(result2));
    System.out.println(java.util.Arrays.toString(result3));
  }
}
