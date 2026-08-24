package online;


/*                                  Mediator

Question 3 (Subsection B2): Smart Home Automation HubQuestion Explanation
Multiple devices (Light Sensor, Blinds, Air Conditioner) need to react to changes in each other's status without 
calling each other directly. The Mediator Pattern centralizes complex communications into a single hub object,
 reducing direct coupled dependencies from M:N connections to 1:N. 
  Pattern Used: Mediator Pattern




*/















/*


// 1. Mediator Interface
interface CentralHub {
    void notify(String event, Device sender);
}

// 2. Base Colleague Class
abstract class Device {
    protected CentralHub hub;

    public Device(CentralHub hub) {
        this.hub = hub;
    }
}

// 3. Concrete Colleagues
class LightSensor extends Device {
    public LightSensor(CentralHub hub) { super(hub); }

    public void detectBrightness(String level) {
        System.out.println("[LightSensor] Detected: " + level);
        if ("High Brightness".equalsIgnoreCase(level)) {
            hub.notify("HIGH_BRIGHTNESS", this);
        }
    }
}

class AutomaticBlinds extends Device {
    public AutomaticBlinds(CentralHub hub) { super(hub); }

    public void close() {
        System.out.println("[AutomaticBlinds] Closing blinds...");
        hub.notify("BLINDS_CLOSED", this);
    }
}

class AirConditioner extends Device {
    public AirConditioner(CentralHub hub) { super(hub); }

    public void turnOn() {
        System.out.println("[AirConditioner] Turning ON AC (Room getting stuffy)...");
    }
}

// 4. Concrete Mediator
class SmartHomeHub implements CentralHub {
    private LightSensor lightSensor;
    private AutomaticBlinds blinds;
    private AirConditioner ac;

    public void setLightSensor(LightSensor lightSensor) { this.lightSensor = lightSensor; }
    public void setBlinds(AutomaticBlinds blinds) { this.blinds = blinds; }
    public void setAc(AirConditioner ac) { this.ac = ac; }

    @Override
    public void notify(String event, Device sender) {
        if (event.equals("HIGH_BRIGHTNESS") && sender == lightSensor) {
            System.out.println("[Hub] Light sensor reported high brightness. Triggering Blinds.");
            blinds.close();
        } else if (event.equals("BLINDS_CLOSED") && sender == blinds) {
            System.out.println("[Hub] Blinds reported closed. Triggering AC.");
            ac.turnOn();
        }
    }
}

// 5. Execution
public class Main {
    public static void main(String[] args) {
        SmartHomeHub hub = new SmartHomeHub();

        LightSensor sensor = new LightSensor(hub);
        AutomaticBlinds blinds = new AutomaticBlinds(hub);
        AirConditioner ac = new AirConditioner(hub);

        hub.setLightSensor(sensor);
        hub.setBlinds(blinds);
        hub.setAc(ac);

        // Action trigger sequence
        sensor.detectBrightness("High Brightness");
    }
}





*/









public class B2 {
    
}
