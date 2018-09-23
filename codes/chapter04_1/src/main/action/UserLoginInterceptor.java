package main.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * @author MayerFang
 * @file UserLoginInterceptor
 * @Description
 * @date 2018/9/23
 */
public class UserLoginInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation arg0) throws Exception {
        if(LoginAction.class==arg0.getAction().getClass()){
            return arg0.invoke();
        }
        Map sessionMap=arg0.getInvocationContext().getSession();
        if(null==sessionMap.get("username")){
            System.out.println("in loginInterceptor,the username is null");
            return Action.LOGIN;
        }
        //同上，表示不做任何处理，把请求继续法向下一个环节
        return arg0.invoke();
    }
}


