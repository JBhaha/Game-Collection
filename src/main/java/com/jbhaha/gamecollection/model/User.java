package com.jbhaha.gamecollection.model;

/**
 * User model class
 */
public class User {

    private String userUUID;
    private String userName;
    private String password;
    private String userRole;

    /**
     * UserUUID getter
     * @return userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * UserUUID setter
     * @param userUUID
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * UserName getter
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * UserName setter
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Password getter
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * UserRole getter
     * @return userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * UserRole setter
     * @param userRole
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
