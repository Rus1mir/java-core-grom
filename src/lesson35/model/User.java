package lesson35.model;

public class User extends Entity {

    private String userName;
    private String password;
    private String country;
    private UserType userType;

    public User(long id, String userName, String password, String country, UserType userType) {
        super(id);
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ',' +
                userName + ',' +
                password + ',' +
                country + ',' +
                userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public enum UserType {ADMIN, USER}
}
