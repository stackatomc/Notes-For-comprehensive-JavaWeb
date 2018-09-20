<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/20
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="mybean" scope="session" class="bean.NameHandler"/>
<jsp:setProperty name="mybean" property="sid" value='<%=request.getParameter("username")%>'/>

<html>
<head>
    <title>responseuser</title>
</head>
<body>
    Welcome!<jsp:getProperty name="mybean" property="sid"/>
</body>
</html>
