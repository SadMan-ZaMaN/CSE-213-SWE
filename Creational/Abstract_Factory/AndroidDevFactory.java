package Creational.Abstract_Factory;

public class AndroidDevFactory extends Employees_Abstract_Factory {

    @Override
    public Employees createEmployee() {
        return new AndroidDevelopers();
    }   
    
}
