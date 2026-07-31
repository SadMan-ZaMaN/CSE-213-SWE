package Creational.Builder;

public class Main {
    public static void main(String[] args) {
        User user = new User.UserBuilder()
                .setUserName("John Doe")
                .setEmailId("john.doe@example.com")
                .setUserId("12345")
                .build();                           // If you don't call .build(), then you do not get a User object. You only have a UserBuilder object.

        System.out.println("User: " + user);        // toString() method will be called here
    }
}
