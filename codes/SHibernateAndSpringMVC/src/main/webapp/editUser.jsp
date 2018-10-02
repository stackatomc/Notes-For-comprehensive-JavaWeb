<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/10/2
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>editUser</title>
</head>
<% String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+path;%>
<body>
<body>
<form action="<%=basePath%>/modifyUser" method="post">
    <input type="hidden" name="id" value="${user.id}">
    用户名：<input type="text" name="name" value="${user.name}"><br/>
    年&nbsp;龄：<input type="text" name="age"><br/>
    <input type="submit" value="修改"/>
</form>
</body>
</html>
