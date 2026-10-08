package org.example.leet.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonStringToJsonNode {

  public static void main(String[] args){

    long startTime = System.currentTimeMillis();

    // Your code here
    ArrayList<String> a = new ArrayList<>();
    a.add("Avi");
    a.add("nas");
    a.add("bsd");
    a.sort(String::compareTo);
    System.out.println(a);

    String string1= "sdsaf asf, adad";
    //String[] as = string1.split("[\\s,;.!:?\\-]+");
    String[] as = string1.split(" ");
    System.out.println(Arrays.asList(as));
    Arrays.asList(as);
    // End time
    long endTime = System.currentTimeMillis();

    // Calculate and print execution time
    long executionTime = endTime - startTime;
    System.out.println("Execution Time: " + executionTime + " milliseconds");
  }

}
