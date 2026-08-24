/*                                  State

Question 1 (Subsection A1): State Subscription SimulatorQuestion 
Explanation
A brain-support subscription system transitions between dynamic operational modes: Common, Plus, and Lux.
The system's behavior changes depending on its current state (e.g., safe travel range, available mood control). 
State pattern delegates state transitions (promote, demote) and state-specific actions (travelCheck, setMood) 
to dedicated state classes, removing long if-else or switch statements.  


*/










/*

import java.util.Objects;

// 1. State Interface
interface TierState {
    void travelCheck(PatientContext context, int km);
    void promote(PatientContext context);
    void demote(PatientContext context);
    void setMood(PatientContext context, String mood);
    String getName();
}

// 2. Concrete States
class CommonState implements TierState {
    @Override
    public void travelCheck(PatientContext context, int km) {
        if (km <= 10) {
            System.out.println("[Common] Distance " + km + " km: STABLE");
        } else {
            System.out.println("[Common] Distance " + km + " km: UNSTABLE! Patient blacked out. Please bring patient back into coverage.");
            System.out.println("--> Bringing patient back into coverage...");
            context.travelCheck(0); // Regains consciousness
        }
    }

    @Override
    public void promote(PatientContext context) {
        System.out.println("Promoting from Common to Plus.");
        context.setState(new PlusState());
    }

    @Override
    public void demote(PatientContext context) {
        System.out.println("Already at lowest tier (Common). No change.");
    }

    @Override
    public void setMood(PatientContext context, String mood) {
        System.out.println("Mood control unavailable.");
    }

    @Override
    public String getName() { return "Common"; }
}

class PlusState implements TierState {
    @Override
    public void travelCheck(PatientContext context, int km) {
        if (km <= 50) {
            System.out.println("[Plus] Distance " + km + " km: STABLE");
        } else {
            System.out.println("[Plus] Distance " + km + " km: UNSTABLE! Patient blacked out. Please bring patient back into coverage.");
            System.out.println("--> Bringing patient back into coverage...");
            context.travelCheck(0);
        }
    }

    @Override
    public void promote(PatientContext context) {
        System.out.println("Promoting from Plus to Lux.");
        context.setState(new LuxState());
    }

    @Override
    public void demote(PatientContext context) {
        System.out.println("Demoting from Plus to Common.");
        context.setState(new CommonState());
    }

    @Override
    public void setMood(PatientContext context, String mood) {
        System.out.println("Mood control unavailable.");
    }

    @Override
    public String getName() { return "Plus"; }
}

class LuxState implements TierState {
    @Override
    public void travelCheck(PatientContext context, int km) {
        if (km <= 50) {
            System.out.println("[Lux] Distance " + km + " km: STABLE");
        } else {
            System.out.println("[Lux] Distance " + km + " km: UNSTABLE! Patient blacked out. Please bring patient back into coverage.");
            System.out.println("--> Bringing patient back into coverage...");
            context.travelCheck(0);
        }
    }

    @Override
    public void promote(PatientContext context) {
        System.out.println("Already at highest tier (Lux). No change.");
    }

    @Override
    public void demote(PatientContext context) {
        System.out.println("Demoting from Lux to Plus.");
        context.setState(new PlusState());
    }

    @Override
    public void setMood(PatientContext context, String mood) {
        System.out.println("[Lux] Patient mood set to: " + mood);
    }

    @Override
    public String getName() { return "Lux"; }
}

// 3. Context Class
class PatientContext {
    private TierState currentState;

    public PatientContext() {
        this.currentState = new CommonState();
    }

    public void setState(TierState state) {
        this.currentState = state;
    }

    public TierState getState() {
        return currentState;
    }

    public void travelCheck(int km) {
        currentState.travelCheck(this, km);
    }

    public void promote() {
        currentState.promote(this);
    }

    public void demote() {
        currentState.demote(this);
    }

    public void setMood(String mood) {
        currentState.setMood(this, mood);
    }

    public void activateLux(int hours) {
        System.out.println("\n--- Activating temporary Lux mode for " + hours + " hours ---");
        TierState previousState = this.currentState;
        this.currentState = new LuxState();
        
        // Simulating duration
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Lux duration expired. Restoring previous state: " + previousState.getName());
        this.currentState = previousState;
        System.out.println("----------------------------------------------------\n");
    }
}

// 4. Execution
public class Main {
    public static void main(String[] args) {
        PatientContext patient = new PatientContext();

        System.out.println("=== Initial State Checks ===");
        patient.travelCheck(5);
        patient.travelCheck(12);

        System.out.println("\n=== Promotion Checks ===");
        patient.promote(); // To Plus
        patient.travelCheck(30);
        patient.setMood("happy"); // Should fail

        patient.activateLux(2); // Temporary Lux check
        patient.setMood("calm"); // Should work during Lux inside method if called during mode context

        System.out.println("\n=== Demotion Checks ===");
        patient.demote(); // Back to Common
        patient.travelCheck(5);
    }
}








*/













package online;

public class A1 {
    
}
