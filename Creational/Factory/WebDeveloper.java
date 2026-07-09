package Creational.Factory;

public class WebDeveloper implements Employee {
    @Override
    public int salary() {
        System.out.println("Web Developer Salary: 60000");
        return 60000;
    }
}
