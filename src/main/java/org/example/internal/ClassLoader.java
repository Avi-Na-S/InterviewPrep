package org.example.internal;


class Emp{
    static {
        System.out.println("Hi there");
    }
}

public class ClassLoader {

    public static void main(String[] args) {
        var e1 = new Emp();
        var e2 = new Emp();
        var e3 = new Emp();
    }
}
