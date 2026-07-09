package Creational.Abstract_Factory;

public class WebDevelopers implements Employees {
    @Override
    public int salary() {
        return 72000;
    }

    @Override
    public String name() {
        System.out.println("I am a Web Developer");
        return "Web Developer";
    }
}
