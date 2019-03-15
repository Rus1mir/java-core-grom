package lesson35.model;

import lesson35.exception.DataFormatErrorException;

public class User {

    private static long loginedId = -1;
    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;

    public User(long id, String userName, String password, String country, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
    }

    public User (String fields[]) throws DataFormatErrorException {
        try {
            this.id = Long.parseLong(fields[0]);
            this.userName = fields[1];
            this.password = fields[2];
            this.country = fields[3];
            this.userType = UserType.valueOf(fields[4]);
        } catch (Exception e) {
            throw new DataFormatErrorException("Can't create object 'Room', one or many fields is incorrect", e);
        }
    }

    public static void login(long userId) {
        loginedId = userId;
    }

    public static void logout() {
        loginedId = -1;
    }

    public static boolean isLogined (Long userId) {
        return userId == loginedId;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ',' +
                userName + ',' +
                password + ',' +
                country + ',' +
                userType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
