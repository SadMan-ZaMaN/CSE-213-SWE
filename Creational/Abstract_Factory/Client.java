package Creational.Abstract_Factory;

public class Client {
    public static void main(String[] args) {

        Employees e1 = EmployeesFactory.getEmployee(new AndroidDevFactory());
        System.out.println("Employee Name: " + e1.name());
        System.out.println("Employee Salary: " + e1.salary());

        Employees e2 = EmployeesFactory.getEmployee(new WebDevFactory());
        System.out.println("Employee Name: " + e2.name());
        System.out.println("Employee Salary: " + e2.salary());
   
    }
}
