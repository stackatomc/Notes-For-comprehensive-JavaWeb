package main.action;

/**
 * @author MayerFang
 * @file CheckUser
 * @Description
 * @date 2018/9/23
 */
public class CheckUser {

    public static boolean isUser(String username,String password){
        String name=username.trim();
        String pwd=password.trim();
        if(name.equals("Java")&&pwd.equals("StrutsIntercept")){
            return true;
        }
        return false;
    }
}
