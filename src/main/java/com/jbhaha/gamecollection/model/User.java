package com.jbhaha.gamecollection.model;

/**
 * User model class
 */
public class User {

    private String userUUID;
    private String username;
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
    public String getUsername() {
        return username;
    }

    /**
     * UserName setter
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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
