package com.spring;

import com.model.UserInfo;

import java.sql.DriverManager;

/**
 * @author MayerFang
 * @file helloWorld
 * @Description
 * @date 2018/10/1
 */
public class helloWorldAction {


    private String message;

    private helloWorldService hws;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public helloWorldService getHws() {
        return hws;
    }

    public void setHws(helloWorldService hws) {
        this.hws = hws;
    }

    private String userName;
    private String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String login(){

        UserInfo user=hws.getUserInfoByLoginName(userName,userPwd);
        if(user!=null){
            message=user.getUserName();
            return "success";
        }else return "failure";
    }
}
