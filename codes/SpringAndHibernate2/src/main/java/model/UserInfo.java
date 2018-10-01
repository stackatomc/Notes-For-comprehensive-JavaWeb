package model;

import javax.persistence.*;

/**
 * @author MayerFang
 * @file UserInfo
 * @Description
 * @date 2018/10/1
 */
@Entity
@Table(name="t_userinfo")
public class UserInfo {

    @Id
    @Column(name="UserID")
    private int userID;

    @Column(name="username")
    private String userName;

    @Column(name="pwd")
    private String Pwd;

    @Column(name="usertype")
    private String userType;

    @Transient
    private int age;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
