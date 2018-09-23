<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/23
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<s:form action="/login" method="POST">
    <s:textfield name="username" label="用户名"/>
    <s:textfield name="password" label="密码"/>
    <s:submit value="登陆"/>
</s:form>
</body>
</html>
