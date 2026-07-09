package Creational.Builder;

public class User {
    private final String userName;
    private final String emailId;
    private final String userId;

    private User(UserBuilder builder) {
        this.userName = builder.userName;
        this.emailId = builder.emailId;
        this.userId = builder.userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getUserId() {
        return userId;
    }



    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    // Inner class to create object of User class
    static class UserBuilder {
        private String userName;
        private String emailId;
        private String userId;

        public UserBuilder() {
        
        }

        public UserBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public UserBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        // Return the finally constructed User object
        public User build() {
            User user = new User(this);
            return user;
        }
    }
}
