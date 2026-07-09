package Creational.Builder;

public class Main {
    public static void main(String[] args) {
        User user = new User.UserBuilder()
                .setUserName("John Doe")
                .setEmailId("john.doe@example.com")
                .setUserId("12345")
                .build();

        System.out.println("User: " + user);        // toString() method will be called here
    }
}
