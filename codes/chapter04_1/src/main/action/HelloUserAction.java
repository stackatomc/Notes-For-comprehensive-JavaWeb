package main.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author MayerFang
 * @file HelloUserAction
 * @Description
 * @date 2018/9/23
 */
public class HelloUserAction extends ActionSupport {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String execute()throws Exception{
        return "success";
    }
}
