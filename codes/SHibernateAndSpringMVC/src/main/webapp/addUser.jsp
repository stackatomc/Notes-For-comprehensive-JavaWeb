<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/10/2
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addUser</title>
</head>
<% String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+path; %>
<body>
<form action="<%=basePath%>/addUser" method="post">
    用户名：<input type="text" name="name"><br/>
    年&nbsp;龄：<input type="text" name="age"><br/>
    <input type="submit" value="添加用户"/>
</form>
</body>
</html>
