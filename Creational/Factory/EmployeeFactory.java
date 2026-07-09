package Creational.Factory;

public class EmployeeFactory {
    
    // get the employee based on the input
    public static Employee getEmployee(String empType){
        if(empType == null){
            return null;
        }
        if(empType.equalsIgnoreCase("WEB")){
            return new WebDeveloper();
        } else if(empType.equalsIgnoreCase("ANDROID")){
            return new AndroidDeveloper();
        }
        return null;
    }
}
