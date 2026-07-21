// C1 — Singleton Pattern (AuditLogger)



/*

public class AuditLogger {
    // The single instance, volatile for safe publication across threads
    private static volatile AuditLogger instance;

    private final StringBuilder logStore = new StringBuilder();

    // Private constructor -> cannot be called directly from client classes
    private AuditLogger() {
        // Guards against reflection-based instantiation creating a 2nd instance
        if (instance != null) {
            throw new IllegalStateException("AuditLogger already initialized. Use getInstance().");
        }
    }

    // Thread-safe lazy initialization (double-checked locking)
    public static AuditLogger getInstance() {
        if (instance == null) {                       // 1st check (no locking cost normally)
            synchronized (AuditLogger.class) {
                if (instance == null) {                // 2nd check inside lock
                    instance = new AuditLogger();
                }
            }
        }
        return instance;
    }

    public synchronized void log(String message) {
        logStore.append(message).append(System.lineSeparator());
    }

    public synchronized String getLogs() {
        return logStore.toString();
    }
}

// ---- Demonstration ----
class StudentLoginModule {
    void login(String user) {
        AuditLogger.getInstance().log("Login attempt: " + user);
    }
}

class QuestionProcessingModule {
    void process(String qId) {
        AuditLogger.getInstance().log("Processed question: " + qId);
    }
}

public class Main {
    public static void main(String[] args) {
        AuditLogger l1 = AuditLogger.getInstance();      // first ever call -> creates instance
        new StudentLoginModule().login("student42");

        AuditLogger l2 = AuditLogger.getInstance();      // second module, different reference request
        new QuestionProcessingModule().process("Q101");

        System.out.println("Same instance? " + (l1 == l2));  // true
        System.out.println(l1.getLogs());
    }
}

*/