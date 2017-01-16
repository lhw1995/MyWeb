package com.restaurant.controller.dto;

/**
 * Created by lhw on 2016/12/2.
 */
public class LoginConsumerDto {
    private String userName;
    private String password;
    private String remName;

    public String getRemName() {
        return remName;
    }

    @Override
    public String toString() {
        return "LoginConsumerDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", remName='" + remName + '\'' +
                '}';
    }

    public void setRemName(String remName) {
        this.remName = remName;
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
}
