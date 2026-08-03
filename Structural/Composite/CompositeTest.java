package Structural.Composite;

public class CompositeTest {
    public static void main(String[] args) {
        component hd = new Leaf("Hard Drive", 100);
        component ram = new Leaf("RAM", 50);
        component cpu = new Leaf("CPU", 200);
        component monitor = new Leaf("Monitor", 150);
        component keyboard = new Leaf("Keyboard", 30);

        Composite computer = new Composite("Computer");
        Composite peripherals = new Composite("Peripherals");
        Composite cabinet = new Composite("Cabinet");
        Composite motherboard = new Composite("Motherboard");

        peripherals.addComponent(monitor);
        peripherals.addComponent(keyboard);

        motherboard.addComponent(cpu);
        motherboard.addComponent(ram);

        cabinet.addComponent(hd);
        cabinet.addComponent(motherboard);      // Composite can contain other composites as well as leaves

        computer.addComponent(cabinet);
        computer.addComponent(peripherals);
        computer.showPrice();

        peripherals.showPrice();
        cabinet.showPrice();
        motherboard.showPrice();
    }
}
