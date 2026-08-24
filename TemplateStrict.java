/*

In the Template Method Pattern, you enforce the strict execution order of an algorithm by defining
 a final method (often called the template method) in the abstract base class.

Because the method is marked final, subclasses are strictly forbidden from overriding the sequence. 
They can only customize how individual steps are executed, but never when or in what order they happen.

*/








// Abstract Base Class defining the algorithm skeleton
abstract class BeverageTemplate {

                                                    // 1. The TEMPLATE METHOD marked as final.

    // This locks the sequence of steps in stone. Subclasses CANNOT change this order.
    public final void prepareRecipe() {
        addWater();
        addSugar();
        addTeaLeaf();
        boil();
        customFlavoring(); // Optional hook/custom step for subclasses
    }

    // Step 1: Fixed step or customized step
    private void addWater() {
        System.out.println("Step 1: Adding 250ml water to pot.");
    }

    // Step 2: Fixed step
    private void addSugar() {
        System.out.println("Step 2: Adding 1 spoon of sugar.");
    }

    // Step 3: Fixed step
    private void addTeaLeaf() {
        System.out.println("Step 3: Adding tea leaves.");
    }

    // Step 4: Fixed step
    private void boil() {
        System.out.println("Step 4: Boiling the mixture for 5 minutes.");
    }

    // Abstract method or hook for custom sub-steps
    protected abstract void customFlavoring();
}

// Subclass 1: Customizes only its allowed step, but must follow the 1 -> 2 -> 3 -> 4 sequence
class MilkTea extends BeverageTemplate {
    @Override
    protected void customFlavoring() {
        System.out.println("Step 5 [Custom]: Adding condensed milk.");
    }
}

// Subclass 2: Another variation following the exact same step order
class LemonTea extends BeverageTemplate {
    @Override
    protected void customFlavoring() {
        System.out.println("Step 5 [Custom]: Squeezing fresh lemon juice.");
    }
}

// Execution Demo
public class TemplateStrict {
    public static void main(String[] args) {
        System.out.println("=== Preparing Milk Tea ===");
        BeverageTemplate milkTea = new MilkTea();
        milkTea.prepareRecipe(); // Executes 1 -> 2 -> 3 -> 4 -> 5 in strict order

        System.out.println("\n=== Preparing Lemon Tea ===");
        BeverageTemplate lemonTea = new LemonTea();
        lemonTea.prepareRecipe();
    }
}
