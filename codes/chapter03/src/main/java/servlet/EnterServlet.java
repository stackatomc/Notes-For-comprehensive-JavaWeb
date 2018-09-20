package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MayerFang
 * @file EnterServlet
 * @Description JSP+Servlet+JavaBean+JDBC
 * @date 2018/9/20
 */
public class EnterServlet extends HttpServlet {

    private static final long serialVersionUID=1L;
    public EnterServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        response.sendRedirect("../notelist.jsp");
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
