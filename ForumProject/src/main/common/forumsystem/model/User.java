package common.forumsystem.model;

public class User {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return ("name: '" + userName + "'}");
    }
}
