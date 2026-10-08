package org.example.internal;

class Test{
    public void add(float a, float b){
        System.out.println(a+b);
    }

    public void add(int a, float b){
        System.out.println("int "+(a+b));
    }

    public void read(String a){
        System.out.println(a);
    }

//    public void read(StringBuilder a{
//        System.out.println(a);
//    }

    public void read(Object a){
        System.out.println(a);
    }

}

public class OverLoading {
    public static void main(String[] args) {
        Test t = new Test();
        t.add(1.2f,2.3f);
        t.add(1,2); // takes higher prefernce.
        t.read(null); //  error since both string and stringbuilder takes same level of high preference in null state
    }
}
