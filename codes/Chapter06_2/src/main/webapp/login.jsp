<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/29
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="loginAction" method="GET">
    <table>
        <tr>
            <td>Your username:</td>
            <!-- 注意表单提交的数据都用控件的name而不是id来控制-->
            <td><input type="text" name="username" value="KK"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="submit!"/>
                <input type="reset" value="reset!"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
