package online;


/*                                      Observer

Question Explanation
A core market feed updates real-time prices across various independent display components 
(Ticker Tape, Graph, Automated Trading Bot). Using the Observer Pattern, components subscribe to updates 
from StockData without the subject requiring concrete knowledge of individual UI widgets.  

Pattern Used: Observer Pattern

*/









/*



import java.util.ArrayList;
import java.util.List;

// 1. Observer Interface
interface StockObserver {
    void update(String stockSymbol, double price);
}

// 2. Concrete Observers (Widgets & Bots)
class TickerTapeWidget implements StockObserver {
    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[Ticker Tape] Scrolling -> " + stockSymbol + ": $" + price);
    }
}

class GraphWidget implements StockObserver {
    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[Graph Widget] Plotting data point -> (" + stockSymbol + ", " + price + ")");
    }
}

class BuySellBot implements StockObserver {
    private final double thresholdPrice;

    public BuySellBot(double thresholdPrice) {
        this.thresholdPrice = thresholdPrice;
    }

    @Override
    public void update(String stockSymbol, double price) {
        if (price < thresholdPrice) {
            System.out.println("[Buy/Sell Bot] ALERT: Price $" + price + " below threshold $" + thresholdPrice + "! Executing BUY order.");
        } else {
            System.out.println("[Buy/Sell Bot] Price checked. No trade executed.");
        }
    }
}

// 3. Subject Class
class StockData {
    private final List<StockObserver> observers = new ArrayList<>();

    public void addWidget(StockObserver observer) {
        observers.add(observer);
    }

    public void removeWidget(StockObserver observer) {
        observers.remove(observer);
    }

    public void setPrice(String stockSymbol, double newPrice) {
        System.out.println("\n--- Stock Update: " + stockSymbol + " is now $" + newPrice + " ---");
        notifyObservers(stockSymbol, newPrice);
    }

    private void notifyObservers(String stockSymbol, double price) {
        for (StockObserver observer : observers) {
            observer.update(stockSymbol, price);
        }
    }
}

// 4. Execution
public class Main {
    public static void main(String[] args) {
        StockData stockFeed = new StockData();

        StockObserver ticker = new TickerTapeWidget();
        StockObserver graph = new GraphWidget();
        StockObserver bot = new BuySellBot(150.00);

        stockFeed.addWidget(ticker);
        stockFeed.addWidget(graph);
        stockFeed.addWidget(bot);

        // Price change events
        stockFeed.setPrice("AAPL", 175.50);
        stockFeed.setPrice("AAPL", 142.00);

        // Remove widget dynamically
        System.out.println("\n--> Removing Ticker Tape Widget...");
        stockFeed.removeWidget(ticker);

        stockFeed.setPrice("AAPL", 160.00);
    }
}


*/



public class C2 {
    
}
