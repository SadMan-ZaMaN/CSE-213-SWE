/*
JAVA TEMPLATE: YOUTUBE NOTIFICATION SYSTEM (OBSERVER PATTERN + CUSTOM ACCESS)

*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 1. Observer Interface
interface Subscriber {
    String getName();
    void update(String creatorName, String videoTitle);
}

// 2. Concrete Observer
class UserSubscriber implements Subscriber {
    private String name;

    public UserSubscriber(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(String creatorName, String videoTitle) {
        System.out.println("[" + name + "'s Feed] Notification: " + creatorName + " uploaded dynamic video: \"" + videoTitle + "\"");
    }
}

// Access Control Enum
enum AccessLevel {
    PUBLIC,
    SUBSCRIBERS_ONLY,
    CUSTOM_RESTRICTED // Hides specific subscribers
}

// 3. Subject Class (Creator Channel)
class CreatorChannel {
    private String channelName;
    private List<Subscriber> subscribers = new ArrayList<>();
    // Blacklist for custom access control
    private Set<Subscriber> hiddenSubscribers = new HashSet<>();

    public CreatorChannel(String channelName) {
        this.channelName = channelName;
    }

    // Subscribe subscriber
    public void subscribe(Subscriber sub) {
        if (!subscribers.contains(sub)) {
            subscribers.add(sub);
            System.out.println(sub.getName() + " subscribed to " + channelName);
        }
    }

    // Creator removes subscriber
    public void removeSubscriber(Subscriber sub) {
        if (subscribers.remove(sub)) {
            hiddenSubscribers.remove(sub); // Clean up from blacklist if present
            System.out.println(channelName + " removed subscriber: " + sub.getName());
        }
    }

    // Custom Access Management: Add subscriber to hidden/restricted list
    public void hideSubscriberFromAccess(Subscriber sub) {
        if (subscribers.contains(sub)) {
            hiddenSubscribers.add(sub);
            System.out.println(sub.getName() + " was added to custom restriction list by " + channelName);
        }
    }

    public void unhideSubscriberFromAccess(Subscriber sub) {
        hiddenSubscribers.remove(sub);
    }

    // Upload Video with Access Control logic
    public void uploadVideo(String videoTitle, AccessLevel access) {
        System.out.println("\n--- " + channelName + " uploaded: \"" + videoTitle + "\" [Access: " + access + "] ---");
        
        switch (access) {
            case PUBLIC:
                // Notify all subscribers
                notifySubscribers(videoTitle, false);
                break;

            case SUBSCRIBERS_ONLY:
                // Notify all subscribers (standard notification)
                notifySubscribers(videoTitle, false);
                break;

            case CUSTOM_RESTRICTED:
                // Notify subscribers, excluding hidden ones
                notifySubscribers(videoTitle, true);
                break;
        }
    }

    private void notifySubscribers(String videoTitle, boolean checkRestrictions) {
        for (Subscriber sub : subscribers) {
            if (checkRestrictions && hiddenSubscribers.contains(sub)) {
                System.out.println("--> [Access Denied] Skipping notification for hidden user: " + sub.getName());
                continue;
            }
            sub.update(channelName, videoTitle);
        }
    }
}

// 4. Execution / Demo
public class ObserverYT {
    public static void main(String[] args) {
        CreatorChannel techChannel = new CreatorChannel("Tech central");

        Subscriber alice = new UserSubscriber("Alice");
        Subscriber bob = new UserSubscriber("Bob");
        Subscriber charlie = new UserSubscriber("Charlie");

        // Subscribe operations
        techChannel.subscribe(alice);
        techChannel.subscribe(bob);
        techChannel.subscribe(charlie);

        // Upload 1: Public video
        techChannel.uploadVideo("Java Design Patterns 101", AccessLevel.PUBLIC);

        // Creator restricts Charlie in custom settings
        techChannel.hideSubscriberFromAccess(charlie);

        // Upload 2: Custom access video (Charlie excluded)
        techChannel.uploadVideo("Exclusive Exam Leaks", AccessLevel.CUSTOM_RESTRICTED);

        // Creator removes Bob
        techChannel.removeSubscriber(bob);

        // Upload 3: Subscribers only
        techChannel.uploadVideo("Final Exam Tips", AccessLevel.SUBSCRIBERS_ONLY);
    }
}
