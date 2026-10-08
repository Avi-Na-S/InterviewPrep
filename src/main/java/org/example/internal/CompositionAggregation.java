package org.example.internal;


import java.util.Objects;

class Driver{
    String name;
}

class Engine{
    void start(){
        System.out.println("start engine");
    }
}

class Car{

    public final Engine engine= new Engine();
    public Driver driver;

    void start(){
        engine.start();
        System.out.println("car started");
    }
    void setDriver(Driver driver){
        this.driver = driver;
    }
    void hasDriver(){
        if(Objects.nonNull(driver)) {
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }
}

public class CompositionAggregation {

    public static void main(String[] args) {

        Driver driver  = new Driver();
        Car car = new Car();
        car.start();
        car.hasDriver();
        car.setDriver(driver);
        car.hasDriver();
    }
}
