package Behavioral;


/*

The Mediator Pattern restricts direct communication between objects and forces them to collaborate 
exclusively through a central mediator object. This reduces chaotic, tightly coupled dependencies by converting 
a complex many-to-many relationship network into a organized one-to-many relationship.

Key Components

Mediator (Interface): Declares communication methods (e.g., notify(sender, event)).

ConcreteMediator: Coordinates component interactions and holds references to them.

Base Component: Holds a reference to the mediator instead of directly talking to other components.

Concrete Components (Button, Checkbox, etc.): Trigger events by notifying the mediator, remaining completely independent of one another.

*/



// Main.java

// --- 1. Mediator Interface ---
interface Mediator {
    void notify(Component sender, String event);
}

// --- 2. Base Component Class ---
class Component {
    protected Mediator dialog;

    public Component(Mediator dialog) {
        this.dialog = dialog;
    }

    public void click() {
        dialog.notify(this, "click");
    }
}

// --- 3. Concrete Components ---
class Button extends Component {
    private String name;

    public Button(Mediator dialog, String name) {
        super(dialog);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// --- 4. Concrete Mediator ---
class AuthenticationDialog implements Mediator {
    private Button okBtn;
    private Button cancelBtn;

    public AuthenticationDialog() {
        // Pass "this" (the mediator instance) to establish connections
        this.okBtn = new Button(this, "OK");
        this.cancelBtn = new Button(this, "Cancel");
    }

    @Override
    public void notify(Component sender, String event) {
        if (sender == okBtn && event.equals("click")) {
            System.out.println("OK Button clicked: Saving authentication data and logging in.");
        } else if (sender == cancelBtn && event.equals("click")) {
            System.out.println("Cancel Button clicked: Clearing inputs and closing dialog.");
        }
    }

    // Getters to simulate user interaction in the driver code
    public Button getOkBtn() { return okBtn; }
    public Button getCancelBtn() { return cancelBtn; }
}
    

public class MediatorPattern {
    public static void main(String[] args) {
        AuthenticationDialog dialog = new AuthenticationDialog();

        // Simulate user clicking components
        System.out.println("User interacts with UI:");
        dialog.getOkBtn().click();
        dialog.getCancelBtn().click();
    }
}











/*


It contains all essential elements required for a production-ready template:

Abstraction: Clear decoupling via the Mediator interface and Component base class.

Encapsulation: The concrete components (Button) do not reference each other directly; they communicate solely through dialog.notify().

Centralized Logic: The AuthenticationDialog (ConcreteMediator) handles the routing and conditional actions for all UI elements.

Executability: Includes a public class Main with a main method that instantiates the objects and triggers the event chain end-to-end.




To adapt this template for a different use case (e.g., chat rooms, air traffic control, or form fields),
replace Button with your specific domain components and adjust the event handling logic inside the
concrete mediator's notify method.






*/