package org.example.internal;

import static java.lang.Thread.sleep;

public class DeadLockTread {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args){
        Thread t1 = new Thread(()->{
            synchronized (lockA){
                System.out.println("t1 lock the A");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("t1 waiting for lock B");
                synchronized (lockB){
                    System.out.println("t1 lock the B");
                }
            }
        });

    Thread t2= new Thread(()->{
        synchronized (lockB){
            System.out.println("t2 lock the B");
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2 waiting for lock A");
            synchronized (lockA){
                System.out.println("t2 lock the B");
            }
        }
    });

    t1.start();
    t2.start();
    }
}
