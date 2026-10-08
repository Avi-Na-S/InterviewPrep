package org.example.internal;

public class ImmutableStringEx {

    public static void main(String[] args) {
        String value  = "Java";
        String copy  = value;

        System.out.println("value:- "+value);
        System.out.println("copy:- "+copy);

        value = value.concat(" Date");
        //copy  = value;
        System.out.println("after upadte value:- "+value);
        System.out.println("after upadte  copy:- "+copy);
    }
}
