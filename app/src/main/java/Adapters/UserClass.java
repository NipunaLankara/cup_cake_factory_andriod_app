package Adapters;

public class UserClass {
    private String userId;
    private String userName;
    private String password;
    private String userType;

    public UserClass() {

    }

    public UserClass(String userId, String userName, String password, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }
}
