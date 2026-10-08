package org.example.internal;


import lombok.Data;

@Data
class Student{
    String name;
    int age;

    Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Hi %s, so your age is %d",name,age);
    }
}

public class Internaltraining {
    public static void main(String[] args) {
        Student avi = new Student("avi",27);
        System.out.println(avi);
    }
}
