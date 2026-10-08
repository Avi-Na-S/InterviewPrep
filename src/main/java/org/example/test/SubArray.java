package org.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SubArray {

  public static void main(String[] args){
    int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };

    ArrayList<Integer> subList = new ArrayList<>();

    int max = 0;
    int index =0;
    for(int i =0; i< a.length; i++){
      subList.add(a[i]);
      if(i == 0){
        max = a[i];
      } else {
        if(max<(max + a[i])){
          index = i;
        }
        max = max + a[i];
        System.out.println(i + "......" + max + " ......" + index);
      }
    }

    System.out.println(index);
    System.out.println(subList.subList(0,index));
  }
}
