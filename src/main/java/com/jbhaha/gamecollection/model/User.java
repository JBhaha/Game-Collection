package com.jbhaha.gamecollection.model;

/**
 * User model class
 */
public class User {

    private String userUUID;
    private String username;
    private String password;
    private String role;

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
     * Username getter
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username setter
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
     * Role getter
     * @return userRole
     */
    public String getRole() {
        return role;
    }

    /**
     * Role setter
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
