package Structural.Composite;

import java.util.*;

interface component{
    void showPrice();
    int getPrice();
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

    @Override
    public int getPrice() {
        return price;
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

    @Override
    public int getPrice() {             // This method calculates the total price of all components in the composite. It iterates through each component in the list and sums their prices to return the total price of the composite.
        int totalPrice = 0;
        for (component c : components) {
            totalPrice += c.getPrice();
        }
        return totalPrice;
    }
}

public class ComputerPart {
    private String name;
}
