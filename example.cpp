/*

Here are four fresh practice questions — one per pattern in your syllabus — in the same exam style, each with a full solution.

---




## Q1 — Singleton (Configuration Manager)

**Scenario:** You're building a game engine. All modules (Renderer, PhysicsEngine, InputHandler) need access to the same `GameConfig` object holding settings like resolution, volume, and difficulty. Creating multiple configs would cause modules to disagree on settings.

**Task:** Implement `GameConfig` so only one instance ever exists, lazily created on first access, and safe if multiple threads request it simultaneously during game startup.

**Solution:**
```java
public class GameConfig {
    private static volatile GameConfig instance;

    private int resolution = 1080;
    private int volume = 50;
    private String difficulty = "Normal";

    private GameConfig() {}

    public static GameConfig getInstance() {
        if (instance == null) {
            synchronized (GameConfig.class) {
                if (instance == null) {
                    instance = new GameConfig();
                }
            }
        }
        return instance;
    }

    public void setDifficulty(String d) { difficulty = d; }
    public String getDifficulty() { return difficulty; }
}

// Demonstration
class Renderer {
    void draw() { System.out.println("Resolution: " + GameConfig.getInstance().resolution); }
}
class PhysicsEngine {
    void tune() { GameConfig.getInstance().setDifficulty("Hard"); }
}

public class Main {
    public static void main(String[] args) {
        new PhysicsEngine().tune();
        new Renderer().draw();
        System.out.println(GameConfig.getInstance().getDifficulty()); // Hard — same instance
    }
}
```
**Key points:** private constructor, double-checked locking, one shared mutable state visible to every module.

---















## Q2 — Factory Method (Notification System)

**Scenario:** A notification service must send alerts via Email, SMS, or Push — the exact type isn't known until runtime (read from a config string). Client code shouldn't `new` concrete classes directly; new channels should be addable without changing client code.

**Task:** Implement using Factory Method so `NotificationService` decides which concrete `Notification` to instantiate based on a type string.

**Solution:**
```java
// Product
interface Notification { void send(String message); }

class EmailNotification implements Notification {
    public void send(String message) { System.out.println("Email: " + message); }
}
class SMSNotification implements Notification {
    public void send(String message) { System.out.println("SMS: " + message); }
}
class PushNotification implements Notification {
    public void send(String message) { System.out.println("Push: " + message); }
}

// Creator (factory method lives here)
abstract class NotificationCreator {
    // Factory Method
    protected abstract Notification createNotification();

    public void notify(String message) {
        Notification n = createNotification(); // subclass decides concrete type
        n.send(message);
    }
}

class EmailCreator extends NotificationCreator {
    protected Notification createNotification() { return new EmailNotification(); }
}
class SMSCreator extends NotificationCreator {
    protected Notification createNotification() { return new SMSNotification(); }
}
class PushCreator extends NotificationCreator {
    protected Notification createNotification() { return new PushNotification(); }
}

// Client
public class Main {
    static NotificationCreator getCreator(String type) {
        switch (type) {
            case "EMAIL": return new EmailCreator();
            case "SMS":   return new SMSCreator();
            case "PUSH":  return new PushCreator();
            default: throw new IllegalArgumentException("Unknown type");
        }
    }

    public static void main(String[] args) {
        NotificationCreator creator = getCreator("SMS");
        creator.notify("Server down!"); // SMS: Server down!
    }
}
```
**Key points:** single product hierarchy (`Notification`), decision of *which* concrete class deferred to subclasses — the tell that separates this from Abstract Factory (only one product type, not a coordinated family).

---













## Q3 — Abstract Factory (Cross-DB Data Access Layer)

**Scenario:** An app must support both MySQL and MongoDB backends. Each backend needs a matching `Connection`, `Query`, and `Transaction` object — mixing a MySQL connection with a MongoDB query must be impossible.

**Task:** Implement Abstract Factory so switching databases is a one-line change, with no mixed components.

**Solution:**
```java
interface Connection { void connect(); }
interface Query { void execute(String q); }
interface Transaction { void commit(); }

// MySQL family
class MySQLConnection implements Connection { public void connect() { System.out.println("MySQL connected"); } }
class MySQLQuery implements Query { public void execute(String q) { System.out.println("MySQL query: " + q); } }
class MySQLTransaction implements Transaction { public void commit() { System.out.println("MySQL commit"); } }

// MongoDB family
class MongoConnection implements Connection { public void connect() { System.out.println("Mongo connected"); } }
class MongoQuery implements Query { public void execute(String q) { System.out.println("Mongo query: " + q); } }
class MongoTransaction implements Transaction { public void commit() { System.out.println("Mongo commit (no-op, NoSQL)"); } }

// Abstract Factory
interface DBFactory {
    Connection createConnection();
    Query createQuery();
    Transaction createTransaction();
}

class MySQLFactory implements DBFactory {
    public Connection createConnection() { return new MySQLConnection(); }
    public Query createQuery()           { return new MySQLQuery(); }
    public Transaction createTransaction() { return new MySQLTransaction(); }
}

class MongoFactory implements DBFactory {
    public Connection createConnection() { return new MongoConnection(); }
    public Query createQuery()           { return new MongoQuery(); }
    public Transaction createTransaction() { return new MongoTransaction(); }
}

// Client
class DataAccessLayer {
    private Connection conn; private Query query; private Transaction tx;
    void configure(DBFactory factory) {
        conn = factory.createConnection();
        query = factory.createQuery();
        tx = factory.createTransaction();
    }
    void run(String sql) {
        conn.connect();
        query.execute(sql);
        tx.commit();
    }
}

public class Main {
    public static void main(String[] args) {
        DataAccessLayer dal = new DataAccessLayer();
        dal.configure(new MySQLFactory());
        dal.run("SELECT * FROM users");
    }
}
```
**Key points:** three related product types, one factory per family, client (`DataAccessLayer`) never touches concrete classes — guarantees no mixing.

---













## Q4 — Builder (Custom HTTP Request)

**Scenario:** An `HttpRequest` object has many optional parts: URL (required), method, headers, query params, body, timeout. A telescoping constructor would need 6+ overloads. Requests must be built step-by-step and be immutable once constructed.

**Task:** Implement using Builder so client code can chain configuration calls and get back a fully-formed, immutable `HttpRequest`.

**Solution:**
```java
import java.util.*;

class HttpRequest {
    private final String url;              // required
    private final String method;
    private final Map<String, String> headers;
    private final String body;
    private final int timeout;

    // Private constructor — only Builder can create instances
    private HttpRequest(Builder b) {
        this.url = b.url;
        this.method = b.method;
        this.headers = b.headers;
        this.body = b.body;
        this.timeout = b.timeout;
    }

    public void send() {
        System.out.println(method + " " + url + " headers=" + headers +
                            " body=" + body + " timeout=" + timeout);
    }

    static class Builder {
        private final String url;              // required, set via constructor
        private String method = "GET";         // sensible defaults
        private Map<String, String> headers = new HashMap<>();
        private String body = null;
        private int timeout = 30;

        public Builder(String url) { this.url = url; }

        public Builder method(String method) { this.method = method; return this; }
        public Builder header(String key, String val) { headers.put(key, val); return this; }
        public Builder body(String body) { this.body = body; return this; }
        public Builder timeout(int seconds) { this.timeout = seconds; return this; }

        public HttpRequest build() { return new HttpRequest(this); }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        HttpRequest req = new HttpRequest.Builder("https://api.example.com/users")
                .method("POST")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"Alice\"}")
                .timeout(10)
                .build();
        req.send();
    }
}
```
**Key points:** fluent chained calls, only required field (`url`) is mandatory, everything else optional with defaults, final object is immutable.

---

**Pattern-spotting recap for exam day:**
- Q1: "one shared config, everyone accesses it" → Singleton
- Q2: "runtime decides which single type" → Factory Method
- Q3: "families that must not mix" → Abstract Factory
- Q4: "many optional fields, step-by-step" → Builder







*/