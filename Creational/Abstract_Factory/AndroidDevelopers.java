package Creational.Abstract_Factory;

public class AndroidDevelopers implements Employees {
    @Override
    public int salary() {
        return 67000;
    }

    @Override
    public String name() {
        System.out.println("I am an Android Developer");
        return "Android Developer";
    }
}
