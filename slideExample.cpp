/*



Good call — the PDF actually has several scenarios that are only **described or diagrammed**, without full code. Here are practice questions built directly from those (Kiosk, Honda Car Factory, Computer Builder), plus one Singleton since the only Singleton example in the slides *is* fully coded (DB Connection).

---

## Q1 — Factory Method: Kiosk Payment Processor
*(from slide 8 — UML only, no code given)*

**Scenario:** A self-checkout kiosk must process payments via Cash, CreditCard, or Coupon. The kiosk shouldn't know the concrete payment class; a `GetPayment` factory should return the right `PaymentProcessor` based on the type selected at the terminal.

**Task:** Implement using Factory Method.

```java
// Product
interface PaymentProcessor {
    void processPayment(double amount);
}

class Cash implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}
class CreditCard implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}
class Coupon implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Applying coupon worth $" + amount);
    }
}

// Factory
class GetPayment {
    public PaymentProcessor getProcessor(String type) {
        if (type == null) return null;
        switch (type.toUpperCase()) {
            case "CASH":   return new Cash();
            case "CREDIT": return new CreditCard();
            case "COUPON": return new Coupon();
            default: return null;
        }
    }
}

// Client
class Kiosk {
    public static void main(String[] args) {
        GetPayment factory = new GetPayment();

        PaymentProcessor p1 = factory.getProcessor("CASH");
        p1.processPayment(50.0);

        PaymentProcessor p2 = factory.getProcessor("CREDIT");
        p2.processPayment(120.0);
    }
}
```
**Why Factory Method:** one product hierarchy (`PaymentProcessor`), one factory method (`getProcessor`) decides the concrete class — matches the slide's UML exactly (`GetPayment +getProcessor(type):PaymentProcessor`).

---



















## Q2 — Abstract Factory: Honda Car Parts Factory
*(from slide 30 — UML only, no code given)*

**Scenario:** A Honda parts supplier builds Civic and Accord models. Each model needs a matching `Engine` and `Transmission` — a Civic engine must never be paired with an Accord transmission.

**Task:** Implement using Abstract Factory.

```java
interface Engine { void specs(); }
interface Transmission { void specs(); }

class CivicEngine implements Engine {
    public void specs() { System.out.println("1.5L Civic Turbo Engine"); }
}
class CivicTrans implements Transmission {
    public void specs() { System.out.println("CVT Transmission (Civic)"); }
}

class AccordEngine implements Engine {
    public void specs() { System.out.println("1.5L Accord Turbo Engine"); }
}
class AccordTrans implements Transmission {
    public void specs() { System.out.println("10-speed Automatic (Accord)"); }
}

interface HondaFactory {
    Engine createEngine();
    Transmission createTrans();
}

class CivicFactory implements HondaFactory {
    public Engine createEngine()      { return new CivicEngine(); }
    public Transmission createTrans() { return new CivicTrans(); }
}
class AccordFactory implements HondaFactory {
    public Engine createEngine()      { return new AccordEngine(); }
    public Transmission createTrans() { return new AccordTrans(); }
}

// Client
class AssemblyLine {
    public static void main(String[] args) {
        HondaFactory factory = new CivicFactory(); // switch to AccordFactory for the other model

        Engine engine = factory.createEngine();
        Transmission trans = factory.createTrans();

        engine.specs();
        trans.specs();
    }
}
```
**Why Abstract Factory:** two related products per family (Engine + Transmission), each factory (`CivicFactory`, `AccordFactory`) guarantees matching parts — exactly the "families of related objects" tell.

---




















## Q3 — Builder: Custom Computer Assembly
*(from slide 45 — described only: "different parts assembled depending on order... 500GB HDD with Intel, or 250GB with AMD")*

**Scenario:** A PC assembly service builds computers to order. Customers pick processor, RAM, storage, and GPU (optional) in whatever combination they want — a plain constructor would need too many overloads.

**Task:** Implement using Builder.

```java
class Computer {
    private final String processor;   // required
    private final String ram;
    private final String storage;
    private final String gpu;         // optional

    private Computer(Builder b) {
        this.processor = b.processor;
        this.ram = b.ram;
        this.storage = b.storage;
        this.gpu = b.gpu;
    }

    public void showSpecs() {
        System.out.println("Processor: " + processor + ", RAM: " + ram +
                            ", Storage: " + storage + ", GPU: " + (gpu == null ? "Integrated" : gpu));
    }

    static class Builder {
        private final String processor;
        private String ram = "8GB";       // default
        private String storage = "256GB SSD";
        private String gpu = null;

        public Builder(String processor) { this.processor = processor; }

        public Builder ram(String ram) { this.ram = ram; return this; }
        public Builder storage(String storage) { this.storage = storage; return this; }
        public Builder gpu(String gpu) { this.gpu = gpu; return this; }

        public Computer build() { return new Computer(this); }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Computer pc1 = new Computer.Builder("Intel i7")
                .ram("16GB")
                .storage("500GB SSD")
                .build();
        pc1.showSpecs();

        Computer pc2 = new Computer.Builder("AMD Ryzen 5")
                .storage("250GB SSD")
                .gpu("RTX 3060")
                .build();
        pc2.showSpecs();
    }
}
```
**Why Builder:** matches the slide's exact scenario — same construction process (assemble a computer), different final representations depending on chosen options, with sensible defaults for unspecified parts.

---
















## Q4 — Singleton: Print Spooler
*(the slides' only Singleton example, DB Connection, is already fully coded — so here's a fresh one matching the "Applicability" text: control access to a shared resource)*

**Scenario:** An office network has one physical printer. All departments (HR, Finance, Sales) submit jobs to it. If each department created its own spooler, print jobs could interleave and corrupt output — there must be exactly one spooler queuing jobs in order.

**Task:** Implement using Singleton.

```java
import java.util.LinkedList;
import java.util.Queue;

public class PrintSpooler {
    private static volatile PrintSpooler instance;
    private final Queue<String> jobQueue = new LinkedList<>();

    private PrintSpooler() {}

    public static PrintSpooler getInstance() {
        if (instance == null) {
            synchronized (PrintSpooler.class) {
                if (instance == null) {
                    instance = new PrintSpooler();
                }
            }
        }
        return instance;
    }

    public synchronized void addJob(String job) {
        jobQueue.add(job);
        System.out.println("Queued: " + job);
    }

    public synchronized void printNext() {
        String job = jobQueue.poll();
        System.out.println(job != null ? "Printing: " + job : "Queue empty");
    }
}

// Client
class HRDept {
    void submit() { PrintSpooler.getInstance().addJob("HR - payslips.pdf"); }
}
class FinanceDept {
    void submit() { PrintSpooler.getInstance().addJob("Finance - invoice.pdf"); }
}

public class Main {
    public static void main(String[] args) {
        new HRDept().submit();
        new FinanceDept().submit();

        PrintSpooler spooler = PrintSpooler.getInstance();
        spooler.printNext(); // HR job first — same shared queue
        spooler.printNext(); // Finance job next
    }
}
```
**Why Singleton:** matches the "control access to shared resource" applicability point in the slides exactly — private constructor, lazy thread-safe init, one queue every department funnels into.





*/