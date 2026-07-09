package Creational.Abstract_Factory;

public class EmployeesFactory {
    
    public static Employees getEmployee(Employees_Abstract_Factory factory) {
        return factory.createEmployee();
        
    }
}
