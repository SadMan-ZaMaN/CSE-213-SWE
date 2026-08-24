package Behavioral;

// Main.java (or BeverageTestDrive.java)

// --- Abstract Class (Template) ---
abstract class CaffeineBeverage {

    // Template Method (marked final so subclasses cannot override the algorithm sequence)
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()){
            addCondiments();
        }
        
    }

    // Primitive operations to be implemented by concrete subclasses
    abstract void brew();
    abstract void addCondiments();

    // Concrete operations shared across all subclasses
    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    boolean customerWantsCondiments() {
        return true; // Default implementation, can be overridden by subclasses
    }
}

// --- Concrete Subclasses ---
class Tea extends CaffeineBeverage {
    public void brew() {
        System.out.println("Steeping the tea");
    }

    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}

class Coffee extends CaffeineBeverage {
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}

// --- Execution Entry Point ---

public class TemplatePattern {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println("--- Making Tea ---");
        tea.prepareRecipe();

        System.out.println("\n--- Making Coffee ---");
        coffee.prepareRecipe();
    }
}
