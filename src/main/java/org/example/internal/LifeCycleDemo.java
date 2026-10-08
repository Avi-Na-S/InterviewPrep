package org.example.internal;

public class LifeCycleDemo {

    static class Box {
        private String size;
        private static int count = 0;

        static {
            System.out.println("static");
        }

        {
            if(size != null){
                size = "1X1";
            }
            System.out.println("Instance:- " + size);
        }

        public Box(String size) {
            this.size = size;
            System.out.println("Constructor:- " + size);
            System.out.println("totalCount:- " + ++count);
        }
    }

    static {
        System.out.println("Static out");
    }

    public static void main(String[] args) {
        System.out.println("Main");

        Box b1 = new Box("1X1");
        {
            Box b2 = new Box("2X2");
            b2 = new Box("22X22");
        }

        Box b3 = createBox();
        new Box("4X4");
    }

    static Box createBox() {
        Box temp = new Box("3X3");
        return temp;
    }
}

