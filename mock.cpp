/*

MOCK QUESTION 1 & SOLUTION: RIDE-SHARING NAVIGATION (STRATEGY PATTERN)
MOCK QUESTION 2 & SOLUTION: DOCUMENT APPROVAL WORKFLOW (STATE PATTERN)
MOCK QUESTION 3 & SOLUTION: LIVE AUCTION BIDDING (OBSERVER PATTERN)
MOCK QUESTION 4 & SOLUTION: ORDER PROCESSING PIPELINE (TEMPLATE METHOD)
MOCK QUESTION 5 & SOLUTION: AIR TRAFFIC CONTROL TOWER (MEDIATOR PATTERN)






Here are the complete solutions (Question + Java Implementation Template) for all 5 mock exam questions. They are formatted in plain text so you can copy and paste them into your `.txt` study files without formatting issues.

# ================================================================================
MOCK QUESTION 1 & SOLUTION: RIDE-SHARING NAVIGATION (STRATEGY PATTERN)

QUESTION:
You are building the routing module for a Ride-Sharing and Navigation App. The app needs to calculate the estimated time of arrival (ETA) and display the path, but the algorithm changes dynamically based on the user's selected travel mode:

* Driving (Calculates via highways, factors in car traffic, speed = 2 mins/km)
* Cycling (Avoids highways, uses bike lanes, speed = 4 mins/km)
* Walking (Uses pedestrian paths, ignores one-way street rules, speed = 12 mins/km)

Implement a simulator with:

* setTravelMode(Mode)
* calculateRoute(origin, destination, distanceInKm)

Task: Implement using Strategy pattern so routing logic can be swapped at runtime.

---

## JAVA CODE SOLUTION:

```java
// 1. Strategy Interface
interface RouteStrategy {
    void calculateRoute(String origin, String destination, int distanceInKm);
}

// 2. Concrete Strategies
class DrivingStrategy implements RouteStrategy {
    @Override
    public void calculateRoute(String origin, String destination, int distanceInKm) {
        int eta = distanceInKm * 2;
        System.out.println("[DRIVING] Route: " + origin + " to " + destination + " via Highways. ETA: " + eta + " mins.");
    }
}

class CyclingStrategy implements RouteStrategy {
    @Override
    public void calculateRoute(String origin, String destination, int distanceInKm) {
        int eta = distanceInKm * 4;
        System.out.println("[CYCLING] Route: " + origin + " to " + destination + " via Bike Lanes. ETA: " + eta + " mins.");
    }
}

class WalkingStrategy implements RouteStrategy {
    @Override
    public void calculateRoute(String origin, String destination, int distanceInKm) {
        int eta = distanceInKm * 12;
        System.out.println("[WALKING] Route: " + origin + " to " + destination + " via Pedestrian Paths. ETA: " + eta + " mins.");
    }
}

// 3. Context Class
class NavigationApp {
    private RouteStrategy strategy;

    public NavigationApp(RouteStrategy defaultStrategy) {
        this.strategy = defaultStrategy;
    }

    public void setTravelMode(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void calculateRoute(String origin, String destination, int distanceInKm) {
        if (strategy == null) {
            System.out.println("Please select a travel mode first!");
            return;
        }
        strategy.calculateRoute(origin, destination, distanceInKm);
    }
}

// 4. Execution
public class Main {
    public static void main(String[] args) {
        NavigationApp nav = new NavigationApp(new DrivingStrategy());
        
        System.out.println("--- Route 1 ---");
        nav.calculateRoute("Point A", "Point B", 10);

        System.out.println("\n--- Swapping Mode to Cycling ---");
        nav.setTravelMode(new CyclingStrategy());
        nav.calculateRoute("Point A", "Point B", 10);

        System.out.println("\n--- Swapping Mode to Walking ---");
        nav.setTravelMode(new WalkingStrategy());
        nav.calculateRoute("Point A", "Point B", 10);
    }
}

```



















# ================================================================================
MOCK QUESTION 2 & SOLUTION: DOCUMENT APPROVAL WORKFLOW (STATE PATTERN)

QUESTION:
A corporate CMS uses a document approval workflow with three states: Draft, UnderReview, and Published.
Implement a simulator with operations: edit(text), sendForReview(), approve().
Rules:

* Draft: edit() updates text. sendForReview() moves to UnderReview. approve() prints "Cannot approve a draft."
* UnderReview: edit() prints "Cannot edit while under review". sendForReview() prints "Already under review". approve() moves to Published.
* Published: approve() prints "Already published". sendForReview() prints "Already published". edit(text) updates text AND demotes to Draft.

---

## JAVA CODE SOLUTION:

```java
// 1. State Interface
interface DocumentState {
    void edit(Document doc, String text);
    void sendForReview(Document doc);
    void approve(Document doc);
}

// 2. Concrete States
class DraftState implements DocumentState {
    @Override
    public void edit(Document doc, String text) {
        doc.setContent(text);
        System.out.println("[Draft] Content updated: \"" + text + "\"");
    }

    @Override
    public void sendForReview(Document doc) {
        System.out.println("[Draft -> UnderReview] Document submitted for review.");
        doc.setState(new UnderReviewState());
    }

    @Override
    public void approve(Document doc) {
        System.out.println("[Draft] Error: Cannot approve a draft directly.");
    }
}

class UnderReviewState implements DocumentState {
    @Override
    public void edit(Document doc, String text) {
        System.out.println("[UnderReview] Error: Cannot edit while under review!");
    }

    @Override
    public void sendForReview(Document doc) {
        System.out.println("[UnderReview] Already under review.");
    }

    @Override
    public void approve(Document doc) {
        System.out.println("[UnderReview -> Published] Document approved and published!");
        doc.setState(new PublishedState());
    }
}

class PublishedState implements DocumentState {
    @Override
    public void edit(Document doc, String text) {
        doc.setContent(text);
        System.out.println("[Published -> Draft] Document edited! Demoting status back to Draft.");
        doc.setState(new DraftState());
    }

    @Override
    public void sendForReview(Document doc) {
        System.out.println("[Published] Already published.");
    }

    @Override
    public void approve(Document doc) {
        System.out.println("[Published] Already published.");
    }
}

// 3. Context Class
class Document {
    private DocumentState state;
    private String content;

    public Document() {
        this.state = new DraftState();
        this.content = "Empty Document";
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void edit(String text) {
        state.edit(this, text);
    }

    public void sendForReview() {
        state.sendForReview(this);
    }

    public void approve() {
        state.approve(this);
    }
}

// 4. Execution
public class Main {
    public static void main(String[] args) {
        Document doc = new Document();

        doc.edit("Initial Draft Text");
        doc.approve(); // Fails

        doc.sendForReview();
        doc.edit("Trying to hack text"); // Fails

        doc.approve(); // Becomes Published
        
        doc.edit("New post-release fix"); // Triggers demotion back to Draft
        doc.sendForReview(); // Resubmits
    }
}

```































# ================================================================================
MOCK QUESTION 3 & SOLUTION: LIVE AUCTION BIDDING (OBSERVER PATTERN)

QUESTION:
Build a live Auction System. A central Auctioneer receives bids. Multiple Bidders register/leave dynamically. When a new high bid is accepted, all registered Bidders receive notifications.

---

## JAVA CODE SOLUTION:

```java
import java.util.ArrayList;
import java.util.List;

// 1. Observer Interface
interface Bidder {
    String getName();
    void update(String item, double price, String highestBidder);
}

// 2. Concrete Observer
class ConcreteBidder implements Bidder {
    private String name;

    public ConcreteBidder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(String item, double price, String highestBidder) {
        System.out.println("[" + name + "'s Screen] Alert: New high bid for " + item + " -> $" + price + " by " + highestBidder);
    }
}

// 3. Subject Class
class Auctioneer {
    private String item;
    private double currentHighestBid = 0.0;
    private String highestBidder = "None";
    private List<Bidder> bidders = new ArrayList<>();

    public Auctioneer(String item, double startingPrice) {
        this.item = item;
        this.currentHighestBid = startingPrice;
    }

    public void registerBidder(Bidder bidder) {
        bidders.add(bidder);
        System.out.println(bidder.getName() + " entered the auction room.");
    }

    public void removeBidder(Bidder bidder) {
        bidders.remove(bidder);
        System.out.println(bidder.getName() + " left the auction room.");
    }

    public void acceptNewBid(double amount, Bidder bidder) {
        if (amount > currentHighestBid) {
            System.out.println("\n[Auctioneer] Accepted new bid of $" + amount + " from " + bidder.getName());
            this.currentHighestBid = amount;
            this.highestBidder = bidder.getName();
            notifyBidders();
        } else {
            System.out.println("\n[Auctioneer] Rejected bid of $" + amount + " from " + bidder.getName() + " (Must be > $" + currentHighestBid + ")");
        }
    }

    private void notifyBidders() {
        for (Bidder bidder : bidders) {
            bidder.update(item, currentHighestBid, highestBidder);
        }
    }
}

// 4. Execution
public class Main {
    public static void main(String[] args) {
        Auctioneer auctioneer = new Auctioneer("Mona Lisa Replica", 1000.0);

        Bidder john = new ConcreteBidder("John");
        Bidder mary = new ConcreteBidder("Mary");
        Bidder alex = new ConcreteBidder("Alex");

        auctioneer.registerBidder(john);
        auctioneer.registerBidder(mary);
        auctioneer.registerBidder(alex);

        auctioneer.acceptNewBid(1200.0, john);
        auctioneer.acceptNewBid(1500.0, mary);

        // Alex leaves
        auctioneer.removeBidder(alex);

        auctioneer.acceptNewBid(1800.0, john);
    }
}

```


























# ================================================================================
MOCK QUESTION 4 & SOLUTION: ORDER PROCESSING PIPELINE (TEMPLATE METHOD)

QUESTION:
An e-commerce platform processes orders using a fixed 4-step pipeline:

1. Verify Payment (Shared)
2. Check Inventory (Custom)
3. Package Item (Custom)
4. Generate Receipt (Shared)

Implement for `PhysicalItemOrder` and `DigitalSoftwareOrder`.

---

## JAVA CODE SOLUTION:

```java
// 1. Abstract Template Class
abstract class OrderProcessorTemplate {

    // Final template method locks in the execution order
    public final void processOrder(String orderId) {
        System.out.println("=== Processing Order: " + orderId + " ===");
        verifyPayment();
        checkInventory();
        packageItem();
        generateReceipt();
        System.out.println("Order Completed Successfully.\n");
    }

    private void verifyPayment() {
        System.out.println("Step 1 [Shared]: Payment verified via secure gateway.");
    }

    // Abstract steps to be customized by subclasses
    protected abstract void checkInventory();
    protected abstract void packageItem();

    private void generateReceipt() {
        System.out.println("Step 4 [Shared]: Digital receipt sent to customer email.");
    }
}

// 2. Concrete Implementation 1
class PhysicalItemOrder extends OrderProcessorTemplate {
    @Override
    protected void checkInventory() {
        System.out.println("Step 2 [Custom]: Checking warehouse physical stock reserves.");
    }

    @Override
    protected void packageItem() {
        System.out.println("Step 3 [Custom]: Boxing item with bubble wrap and printing shipping label.");
    }
}

// 3. Concrete Implementation 2
class DigitalSoftwareOrder extends OrderProcessorTemplate {
    @Override
    protected void checkInventory() {
        System.out.println("Step 2 [Custom]: Verifying software license server capacity.");
    }

    @Override
    protected void packageItem() {
        System.out.println("Step 3 [Custom]: Generating activation license key.");
    }
}

// 4. Execution
public class Main {
    public static void main(String[] args) {
        OrderProcessorTemplate physicalOrder = new PhysicalItemOrder();
        physicalOrder.processOrder("ORD-PHYSICAL-99");

        OrderProcessorTemplate digitalOrder = new DigitalSoftwareOrder();
        digitalOrder.processOrder("ORD-DIGITAL-42");
    }
}

```





















# ================================================================================
MOCK QUESTION 5 & SOLUTION: AIR TRAFFIC CONTROL TOWER (MEDIATOR PATTERN)

QUESTION:
Build an Air Traffic Control (ATC) simulator. Airplanes (`Boeing747`, `AirbusA320`) communicate solely with the `ATCTower` mediator to request landings, avoiding direct airplane-to-airplane calls.

---

## JAVA CODE SOLUTION:

```java
// 1. Mediator Interface
interface ATCTower {
    boolean requestLanding(Airplane plane);
    void notifyLandingComplete(Airplane plane);
}

// 2. Base Colleague
abstract class Airplane {
    protected ATCTower tower;
    protected String flightNumber;

    public Airplane(ATCTower tower, String flightNumber) {
        this.tower = tower;
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public abstract void requestLanding();
    public abstract void completeLanding();
}

// 3. Concrete Colleagues
class Boeing747 extends Airplane {
    public Boeing747(ATCTower tower, String flightNumber) {
        super(tower, flightNumber);
    }

    @Override
    public void requestLanding() {
        System.out.println("[" + flightNumber + " (Boeing 747)] Requesting landing permission...");
        tower.requestLanding(this);
    }

    @Override
    public void completeLanding() {
        System.out.println("[" + flightNumber + " (Boeing 747)] Touchdown successful. Runway clear.");
        tower.notifyLandingComplete(this);
    }
}

class AirbusA320 extends Airplane {
    public AirbusA320(ATCTower tower, String flightNumber) {
        super(tower, flightNumber);
    }

    @Override
    public void requestLanding() {
        System.out.println("[" + flightNumber + " (Airbus A320)] Requesting landing permission...");
        tower.requestLanding(this);
    }

    @Override
    public void completeLanding() {
        System.out.println("[" + flightNumber + " (Airbus A320)] Touchdown successful. Runway clear.");
        tower.notifyLandingComplete(this);
    }
}

// 4. Concrete Mediator
class ControlTower implements ATCTower {
    private boolean isRunwayOccupied = false;

    @Override
    public synchronized boolean requestLanding(Airplane plane) {
        if (!isRunwayOccupied) {
            System.out.println("-> [ATC Tower] Permission GRANTED for " + plane.getFlightNumber() + ". Runway locked.");
            isRunwayOccupied = true;
            return true;
        } else {
            System.out.println("-> [ATC Tower] Permission DENIED for " + plane.getFlightNumber() + ". Runway occupied! Hold pattern.");
            return false;
        }
    }

    @Override
    public synchronized void notifyLandingComplete(Airplane plane) {
        System.out.println("-> [ATC Tower] Acknowledged landing completion of " + plane.getFlightNumber() + ". Runway UNLOCKED.");
        isRunwayOccupied = false;
    }
}

// 5. Execution
public class Main {
    public static void main(String[] args) {
        ATCTower tower = new ControlTower();

        Airplane flight1 = new Boeing747(tower, "BG-701");
        Airplane flight2 = new AirbusA320(tower, "BG-302");

        // Flight 1 requests landing -> Granted
        flight1.requestLanding();

        // Flight 2 requests landing while Flight 1 is landing -> Denied
        flight2.requestLanding();

        // Flight 1 completes landing
        flight1.completeLanding();

        // Flight 2 tries again -> Granted
        flight2.requestLanding();
        flight2.completeLanding();
    }
}

```







*/