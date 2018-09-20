<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/20
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="mybean" scope="session" class="bean.NameHandler"/>
<jsp:setProperty name="mybean" property="sid" value="*"/><%-- value="*" 表示空 --%>
<%
    if(request.getParameter("username")!=null){
        response.sendRedirect("responseuser.jsp");
    }
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>JspAndServletAndJavaBean</title>
</head>
<body>
    <h1>Hello,my name is 张三.  What's your name?</h1>
    <form method="get" action="responseuser.jsp">
        <input type="text" name="username" size="25"/>
        <input type="submit" value="提交"/>
        <input type="reset" value="重置"/>
    </form>
</body>
</html>
