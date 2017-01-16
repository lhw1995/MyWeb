package com.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhw on 2016/12/1.
 */
@Entity
public class Consumer {
    private int id;
    private String userName;
    private String userNameMD5;
    private String showName;
    private String password;
    private String phone;
    private String email;
    private String headPortrait;
    private float Consumption; //总消费额
    public Consumer(){}

    public Consumer(int id, String userName,String userNameMD5, String showName, String password, String phone, String email, String headPortrait, float consumption) {
        this.id = id;
        this.userName = userName;
        this.userNameMD5 = userNameMD5;
        this.showName = showName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.headPortrait = headPortrait;
        Consumption = consumption;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable=false,columnDefinition="varchar(255) default '0'")
    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public float getConsumption() {
        return Consumption;
    }

    public void setConsumption(float consumption) {
        Consumption = consumption;
    }

    public String getUserNameMD5() {
        return userNameMD5;
    }

    public void setUserNameMD5(String userNameMD5) {
        this.userNameMD5 = userNameMD5;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
