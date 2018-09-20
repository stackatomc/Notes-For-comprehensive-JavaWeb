<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/20
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP+Servlet</title>
</head>
<body>
<form method="post" action="./servlet/jspAndservlet" name="form2">
    <table>
        <tr>
            <td>User Name</td>
            <td><input type="text" name="username" id="username" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" id="password" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><!-- rowspan -->
                <input type="submit" name="login" value="Login" />&nbsp;&nbsp;
                <input type="reset" name="reset" value="Reset" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
