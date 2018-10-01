package com.model;

import javax.persistence.*;

/**
 * @author MayerFang
 * @file UserInfo
 * @Description
 * @date 2018/10/1
 */
@Entity
@Table(name="t_userinfo")
public class UserInfo{

    @Id
    @Column(name="UserID")
    private Long userID;

    @Column(name="username")
    private String userName;

    @Column(name="pwd")
    private String Pwd;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }
}
