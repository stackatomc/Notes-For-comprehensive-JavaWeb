<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/10/1
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<s:form action="demo/login.do" method="post">
    <s:textfield name="userName" label="User Name"/>
        <br/>
            <s:password name="userPwd" label="Password"/>
        <br/>
    <s:submit value="Login"/>
    </s:form>


</body>
</html>
