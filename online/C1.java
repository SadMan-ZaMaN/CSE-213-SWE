package online;

/*                                  Template Method

Every hospital visit executes an invariant 5-step workflow: Check-In, Vitals, Assessment, Treatment, 
and Discharge. The Template Method Pattern defines the fixed skeleton in a final method within a base class 
while allowing subclasses (General, Pediatrics, Emergency) to override the specific Assessment and Treatment 
implementations.  

Pattern Used: Template Method Pattern

*/


















/*



// 1. Abstract Template Class
abstract class HospitalVisitTemplate {
    
    // Fixed algorithm workflow skeleton
    public final void processPatientVisit(String patientName, String visitId) {
        checkIn(patientName, visitId);
        recordVitals();
        assessment();
        treatment();
        dischargeSummary();
        System.out.println("----------------------------------------");
    }

    private void checkIn(String patientName, String visitId) {
        System.out.println("Step 1 [Check-In]: Patient " + patientName + " (ID: " + visitId + ") registered.");
    }

    private void recordVitals() {
        System.out.println("Step 2 [Vitals]: Recorded Temperature = 98.6°F, BP = 120/80.");
    }

    // Customizable algorithm steps
    protected abstract void assessment();
    protected abstract void treatment();

    private void dischargeSummary() {
        System.out.println("Step 5 [Discharge]: Patient discharged. Instructions delivered.");
    }
}

// 2. Concrete Department Implementation 1
class GeneralDepartment extends HospitalVisitTemplate {
    @Override
    protected void assessment() {
        System.out.println("Step 3 [Assessment]: Doctor performs normal diagnosis.");
    }

    @Override
    protected void treatment() {
        System.out.println("Step 4 [Treatment]: Prescribe standard medicine.");
    }
}

// 3. Concrete Department Implementation 2
class PediatricsDepartment extends HospitalVisitTemplate {
    @Override
    protected void assessment() {
        System.out.println("Step 3 [Assessment]: Doctor checks symptoms by ensuring child comfort level.");
    }

    @Override
    protected void treatment() {
        System.out.println("Step 4 [Treatment]: Give child-safe medicine, friendly reassurance message.");
    }
}

// 4. Concrete Department Implementation 3
class EmergencyDepartment extends HospitalVisitTemplate {
    @Override
    protected void assessment() {
        System.out.println("Step 3 [Assessment]: Quick triage check (urgent/non-urgent).");
    }

    @Override
    protected void treatment() {
        System.out.println("Step 4 [Treatment]: Immediate emergency procedure.");
    }
}

// 5. Execution
public class Main {
    public static void main(String[] args) {
        System.out.println("=== HOSPITAL VISIT SIMULATION ===\n");

        HospitalVisitTemplate general = new GeneralDepartment();
        general.processPatientVisit("John Doe", "V-101");

        HospitalVisitTemplate peds = new PediatricsDepartment();
        peds.processPatientVisit("Timmy Smith", "V-102");

        HospitalVisitTemplate emergency = new EmergencyDepartment();
        emergency.processPatientVisit("Alice Brown", "V-103");
    }
}





*/



public class C1 {
    
}
