package Creational.Factory;

public class AndroidDeveloper implements Employee {
    @Override
    public int salary() {
        System.out.println("Android Developer Salary: 100000");
        return 100000;
    }
    
}
