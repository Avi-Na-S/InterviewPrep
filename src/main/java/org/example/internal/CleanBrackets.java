package org.example.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class CleanBrackets {
    public static void main(String[] args) {
        String test = "((((test)))((rest)";
        burtforce(test);
        anotherWay(test); // not accurate

        checkIfValid(test);
    }

    private static void checkIfValid(String test) {

        char[] a = test.toCharArray();
        Stack<Character> s = new Stack<>();
        boolean breaked = false;

        for(int i =0;i<a.length;i++){
            if(a[i]=='('){
                s.push(a[i]);
            }else if(a[i]==')'){
                if(s.isEmpty()){
                    breaked=true;
                    break;
                }
                s.pop();
            }
        }

        if(breaked || !s.isEmpty()){
            System.out.println("In valid");
        }else {
            System.out.println("valid");
        }

    }

    private static void anotherWay(String input) {
        int counter=0;
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='('){
                counter++;
                if(counter>=0 && counter<2){
                    ans.append(input.charAt(i));
                }else{
                    continue;
                }
            }else if(input.charAt(i)==')'){
                counter--;
                if(counter>=0){
                    ans.append(input.charAt(i));
                }else{
                    counter=0;
                    continue;
                }
            }else{
                ans.append(input.charAt(i));
            }
        }
        System.out.println("The output=="+ans.toString());

    }

    private static void burtforce(String test){
        char[] a = test.toCharArray();
        Set<Integer> removeIndex = new HashSet<>();
        Stack<Integer> check = new Stack<>();
        for(int i =0;i<a.length;i++){
            if(a[i]=='('){
                check.push(i);
                removeIndex.add(i);
            }else if(a[i] == ')'){
                if(check.isEmpty()){
                    removeIndex.add(i);
                }else {
                    removeIndex.remove(check.pop());
                }
            }
        }

        StringBuilder newString  = new StringBuilder();
        for(int i =0;i<a.length;i++){
            if(!removeIndex.contains(i)){
                newString.append(a[i]);
            }
        }
        System.out.println(newString);
    }
}
