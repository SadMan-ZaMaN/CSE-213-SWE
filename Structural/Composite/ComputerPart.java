package Structural.Composite;

import java.util.*;

interface component{
    void showPrice();
}

class Leaf implements component{
    int price;
    String name;

    public Leaf(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    @Override
    public void showPrice() {
        System.out.println("Leaf : " + name + " Price: " + price);
    }
}

class Composite implements component{
    String name;

    List<component> components = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    public void addComponent(component component) {
        components.add(component);
    }

    @Override
    public void showPrice() {
        System.out.println(name);
        for (component c : components) {
            c.showPrice();
        }
    }
}

public class ComputerPart {
    private String name;
}
