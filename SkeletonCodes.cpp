/*


================================================================================
1. STRATEGY PATTERN TEMPLATE
================================================================================
// WHEN TO USE: Swap algorithms at runtime (e.g., payment, sorting, routing).
// KEY FEATURE: Context holds interface; strategy does NOT switch itself.

// STEP 1: Define the Strategy Interface
interface Strategy {
    // TODO: Define the contract method with required inputs
    void executeAlgorithm(String data);
}

// STEP 2: Implement Concrete Strategies
class ConcreteStrategyA implements Strategy {
    @Override
    public void executeAlgorithm(String data) {
        // TODO: Implement Variant A logic
        System.out.println("Executed via Strategy A on: " + data);
    }
}

class ConcreteStrategyB implements Strategy {
    @Override
    public void executeAlgorithm(String data) {
        // TODO: Implement Variant B logic
        System.out.println("Executed via Strategy B on: " + data);
    }
}

// STEP 3: Create Context Class to hold/swap Strategy
class Context {
    private Strategy strategy;

    public Context(Strategy initialStrategy) {
        this.strategy = initialStrategy;
    }

    // TODO: Method to swap strategy at runtime
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void performAction(String data) {
        // Delegate work to current strategy
        strategy.executeAlgorithm(data);
    }
}

// STEP 4: Exam Main Demo
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyA());
        context.performAction("Test Input");

        // Swap strategy dynamically
        context.setStrategy(new ConcreteStrategyB());
        context.performAction("Test Input");
    }
}














================================================================================
2. STATE PATTERN TEMPLATE
================================================================================
// WHEN TO USE: Behavior changes dynamically as internal status/mode changes.
// KEY FEATURE: State objects know about Context and handle transitions.

// STEP 1: Define the State Interface
interface State {
    // TODO: Define operations whose behavior changes per state
    void handleAction(StateContext context);
    void promote(StateContext context);
}

// STEP 2: Implement Concrete States (Define State Machine behavior)
class InitialState implements State {
    @Override
    public void handleAction(StateContext context) {
        // TODO: Add logic for Initial State behavior
        System.out.println("Handling action in INITIAL state.");
    }

    @Override
    public void promote(StateContext context) {
        // TODO: Transition logic to next state
        System.out.println("Transitioning from INITIAL -> ACTIVE state.");
        context.setState(new ActiveState());
    }
}

class ActiveState implements State {
    @Override
    public void handleAction(StateContext context) {
        // TODO: Add logic for Active State behavior
        System.out.println("Handling action in ACTIVE state.");
    }

    @Override
    public void promote(StateContext context) {
        // TODO: Top state boundary logic
        System.out.println("Already at top state. No change.");
    }
}

// STEP 3: Create Context Class
class StateContext {
    private State currentState;

    public StateContext() {
        // TODO: Set default starting state
        this.currentState = new InitialState();
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void handleAction() {
        currentState.handleAction(this);
    }

    public void promote() {
        currentState.promote(this);
    }
}

// STEP 4: Exam Main Demo
public class Main {
    public static void main(String[] args) {
        StateContext obj = new StateContext();
        obj.handleAction(); // Behavior in State 1
        obj.promote();      // Transition
        obj.handleAction(); // Behavior in State 2
    }
}


















================================================================================
3. OBSERVER PATTERN TEMPLATE
================================================================================
// WHEN TO USE: 1-to-N event notification; sub/unsub at runtime.
// KEY FEATURE: Subject maintains List<Observer> and notifies in a loop.

import java.util.ArrayList;
import java.util.List;

// STEP 1: Define Observer Interface
interface Observer {
    // TODO: Define method called when subject updates
    void update(String eventData);
}

// STEP 2: Implement Concrete Observers
class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String eventData) {
        // TODO: React to notification
        System.out.println("[" + name + "] Received notification: " + eventData);
    }
}

// STEP 3: Define Subject/Publisher
class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer obs) {
        observers.add(obs);
    }

    public void unsubscribe(Observer obs) {
        observers.remove(obs);
    }

    public void notifyObservers(String eventData) {
        for (Observer obs : observers) {
            obs.update(eventData);
        }
    }

    // TODO: Trigger event state change
    public void changeState(String newData) {
        System.out.println("Subject state changed to: " + newData);
        notifyObservers(newData);
    }
}

// STEP 4: Exam Main Demo
public class Main {
    public static void main(String[] args) {
        Subject publisher = new Subject();
        Observer o1 = new ConcreteObserver("Obs1");
        Observer o2 = new ConcreteObserver("Obs2");

        publisher.subscribe(o1);
        publisher.subscribe(o2);
        publisher.changeState("Event 1 Triggered");

        publisher.unsubscribe(o1);
        publisher.changeState("Event 2 Triggered");
    }
}





















================================================================================
4. MEDIATOR PATTERN TEMPLATE
================================================================================
// WHEN TO USE: Decouple direct object-to-object (M:N) calls into hub (1:N).
// KEY FEATURE: Colleagues talk ONLY to Mediator, never to each other.

// STEP 1: Define Mediator Interface
interface Mediator {
    void notify(String event, Colleague sender);
}

// STEP 2: Define Base Colleague Class
abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}

// STEP 3: Implement Concrete Colleagues
class ComponentA extends Colleague {
    public ComponentA(Mediator mediator) { super(mediator); }

    public void doActionA() {
        System.out.println("[Component A] Performed Action A.");
        // Report event to mediator instead of calling Component B directly
        mediator.notify("ACTION_A_DONE", this);
    }
}

class ComponentB extends Colleague {
    public ComponentB(Mediator mediator) { super(mediator); }

    public void reactToA() {
        System.out.println("[Component B] Reacting to Action A via Mediator command.");
    }
}

// STEP 4: Implement Concrete Mediator Hub
class CentralMediator implements Mediator {
    private ComponentA compA;
    private ComponentB compB;

    public void setCompA(ComponentA compA) { this.compA = compA; }
    public void setCompB(ComponentB compB) { this.compB = compB; }

    @Override
    public void notify(String event, Colleague sender) {
        // TODO: Route traffic and orchestrate behavior between objects
        if ("ACTION_A_DONE".equals(event) && sender == compA) {
            System.out.println("[Mediator] Intercepted Action A. Command to Component B.");
            compB.reactToA();
        }
    }
}

// STEP 5: Exam Main Demo
public class Main {
    public static void main(String[] args) {
        CentralMediator mediator = new CentralMediator();
        ComponentA a = new ComponentA(mediator);
        ComponentB b = new ComponentB(mediator);

        mediator.setCompA(a);
        mediator.setCompB(b);

        a.doActionA(); // Starts orchestrated chain reaction
    }
}






















================================================================================
5. TEMPLATE METHOD PATTERN TEMPLATE
================================================================================
// WHEN TO USE: Fixed overall workflow with customizable sub-steps.
// KEY FEATURE: Public final template method in abstract base class.

// STEP 1: Define Abstract Base Class with Template Method
abstract class AbstractAlgorithmTemplate {

    // IMPORTANT: Marked final so subclasses CANNOT override step order
    public final void executeWorkflow() {
        stepOne();
        customStepTwo(); // Abstract step overridden by subclasses
        stepThree();
    }

    private void stepOne() {
        // TODO: Invariant base step 1
        System.out.println("Step 1 [Fixed]: Common initialization.");
    }

    // Abstract method to force subclass customization
    protected abstract void customStepTwo();

    private void stepThree() {
        // TODO: Invariant base step 3
        System.out.println("Step 3 [Fixed]: Common cleanup/completion.");
    }
}

// STEP 2: Implement Concrete Subclasses
class VariationOne extends AbstractAlgorithmTemplate {
    @Override
    protected void customStepTwo() {
        // TODO: Implement custom sub-step for Variation One
        System.out.println("Step 2 [Custom - Var 1]: Specialized execution logic.");
    }
}

class VariationTwo extends AbstractAlgorithmTemplate {
    @Override
    protected void customStepTwo() {
        // TODO: Implement custom sub-step for Variation Two
        System.out.println("Step 2 [Custom - Var 2]: Alternative execution logic.");
    }
}

// STEP 3: Exam Main Demo
public class Main {
    public static void main(String[] args) {
        AbstractAlgorithmTemplate v1 = new VariationOne();
        v1.executeWorkflow();

        System.out.println("---");

        AbstractAlgorithmTemplate v2 = new VariationTwo();
        v2.executeWorkflow();
    }
}




*/