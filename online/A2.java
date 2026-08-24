/*                                      observer 

Question 2 (Subsection A2): RavenBoard Announcement SystemQuestion 
Explanation
Multiple teams (Scouts, Supply Team, Commanders) need to be notified dynamically when new messages 
arrive on a central message board. 
Observers can register or unregister at runtime without altering the central publishing mechanism.  


*/







/*



import java.util.ArrayList;
import java.util.List;

// 1. Observer Interface
interface BoardSubscriber {
    void update(String message);
}

// 2. Concrete Observers
class Scouts implements BoardSubscriber {
    @Override
    public void update(String message) {
        System.out.println("[Scouts] Received: \"" + message + "\" -> Action: Dispatch riders!");
    }
}

class SupplyTeam implements BoardSubscriber {
    @Override
    public void update(String message) {
        System.out.println("[Supply Team] Received: \"" + message + "\" -> Action: Update inventory!");
    }
}

class Commander implements BoardSubscriber {
    @Override
    public void update(String message) {
        System.out.println("[Commander] Received: \"" + message + "\" -> Action: Plan battle strategy!");
    }
}

// 3. Subject Class
class RavenBoard {
    private final List<BoardSubscriber> subscribers = new ArrayList<>();

    public void subscribe(BoardSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(BoardSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void postMessage(String message) {
        System.out.println("\n[RavenBoard Post] " + message);
        for (BoardSubscriber sub : subscribers) {
            sub.update(message);
        }
    }
}

// 4. Execution
public class Main {
    public static void main(String[] args) {
        RavenBoard board = new RavenBoard();

        BoardSubscriber scouts = new Scouts();
        BoardSubscriber supply = new SupplyTeam();
        BoardSubscriber commander = new Commander();

        board.subscribe(scouts);
        board.subscribe(supply);
        board.subscribe(commander);

        board.postMessage("Enemy spotted near the river");
        board.postMessage("Winter supplies running low");

        System.out.println("\n--> Scouts are leaving the board room...");
        board.unsubscribe(scouts);

        board.postMessage("Ships seen in the east");
    }
}




*/

package online;

public class A2 {
    
}
