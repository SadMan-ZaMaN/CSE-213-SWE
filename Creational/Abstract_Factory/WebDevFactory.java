package Creational.Abstract_Factory;

public class WebDevFactory extends Employees_Abstract_Factory {

    @Override
    public Employees createEmployee() {
        return new WebDevelopers();
    }

}
