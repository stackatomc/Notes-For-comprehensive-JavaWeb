<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/29
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
Welcome!
<%=request.getParameter("username")%>
<%=request.getAttribute("username")%><!--   推荐下面这种  -->
</body>
</html>
