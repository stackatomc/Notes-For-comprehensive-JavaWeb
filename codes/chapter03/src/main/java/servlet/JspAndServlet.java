package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MayerFang
 * @file JspAndServlet
 * @Description JSP+Servlet
 * @date 2018/9/20
 */

public class JspAndServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    public JspAndServlet(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("<HEAD><TITLE>Check Account</TITLE></HEAD>");
        out.println("<BODY>");
        if(username.length()<1||password.length()<1){
            out.println("请输入您的用户名和密码，<a href='../login2.jsp'>点击此处返回</a>");
        }else if(username.equalsIgnoreCase("hacker")){
            out.println("您被禁止登陆次页面");
        }else{
            response.sendRedirect("../welcome.jsp?username="+username);
        }
        out.println("   </BODY>");
        out.println("</HTML>");
        out.flush();//由于某些机制，而flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满.
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
