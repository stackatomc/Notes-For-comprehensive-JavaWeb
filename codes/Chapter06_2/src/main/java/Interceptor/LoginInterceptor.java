package Interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MayerFang
 * @file LoginInterceptor
 * @Description 拦截器，必须实现通过Login.jsp才能进入welcome.jsp
 * @date 2018/9/29
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response
    ,Object handler) throws Exception{
        String regUrl=request.getRequestURI();
        System.out.println("enter regUrl.contains");
        if(regUrl.contains("/loginAction")){
            String username=request.getParameter("username");
            if(username==null||username.trim().equals("")||username.equals("null")){
                response.sendRedirect("login.jsp");
                return false;
            }else{
                return true;
            }
        }else{
            System.out.println("I am here!");
            response.sendRedirect("login.jsp");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView)throws Exception{
        super.postHandle(request,response,handler,modelAndView);
    }
}
