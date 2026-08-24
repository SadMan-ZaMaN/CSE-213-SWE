package Behavioral;

// --- Interfaces ---
interface FlyBehavior {
    public void fly();
}

interface QuackBehavior {
    public void quack();
}

// --- Fly Implementations ---
class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("I'm flying!!");
    }
}

class FlyNoWay implements FlyBehavior {
    public void fly() {
        System.out.println("I can't fly");
    }
}

// --- Quack Implementations ---
class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack");
    }
}

class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< Silence >>");
    }
}

// --- Abstract Duck Superclass ---
abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}

// --- Concrete Duck Subclass ---
class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}

// --- Execution Entry Point ---
public class StrategyPattern {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        
        mallard.display();
        mallard.performQuack();
        mallard.performFly();
        mallard.swim();
    }
}