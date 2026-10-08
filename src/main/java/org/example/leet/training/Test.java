package org.example.leet.training;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

//  public static void main(String[] args){
//    int[] i = {2, 6, 3, 9, 11,7, 0};
//    int sumNum = 9;
//    List<Integer> store = new ArrayList<>();
//    List<List<Integer>> numPair = new ArrayList<>();
//
//    for (int j =0; j< i.length; j++){
//      if(i[j]<=sumNum){
//        int finalJ = i[j];
//        Optional<List<Integer>> pair = store.stream()
//            .filter(num -> (num + finalJ) == 9).findFirst()
//            .map(num -> List.of(num, finalJ));
//        store.add(i[j]);
//        pair.ifPresent(numPair::add);
//      }
//    }
//    System.out.println(numPair);
//  }


    public static void main(String[] args) {
        Integer[] arr = {2, 6, 3, 9, 11, 7, 0,-2};
        int sum = 9;
        Set<Integer> seen = new HashSet<>();
        List<List<Integer>> pair = new ArrayList<>();

        for(Integer num : arr){
            Integer needNum = sum - num;
            if(seen.contains(needNum)){
                pair.add(List.of(needNum,num));
            }

            seen.add(num);
        }

        System.out.println(pair);
    }

}
