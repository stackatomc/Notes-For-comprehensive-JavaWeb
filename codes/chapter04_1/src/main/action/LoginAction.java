package main.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

/**
 * @author MayerFang
 * @file LoginAction
 * @Description
 * @date 2018/9/23
 */
public class LoginAction {

    private String username;
    private String password;

    public String execute() throws Exception{
        Map sessionMap= ActionContext.getContext().getSession();
        sessionMap.put("username",username);
        if(CheckUser.isUser(username,password)){
            return "success";
        }
        return "login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
