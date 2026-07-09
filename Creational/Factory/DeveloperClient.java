package Creational.Factory;

public class DeveloperClient {
    public static void main(String[] args){
        // Employee employee1 = new WebDeveloper(); // this is tight coupling because we are directly creating an instance of WebDeveloper
        // but we can use Factory to create an employee based on input 
        Employee employee1 = EmployeeFactory.getEmployee("ANDROID");
        System.out.println("Hehehehe:" + employee1.salary());
    }
}
