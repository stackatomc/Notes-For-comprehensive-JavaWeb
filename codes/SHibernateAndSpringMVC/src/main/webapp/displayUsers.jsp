<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/10/2
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>displayUsers</title>
</head>
<body>
<a href="addUser.jsp">添加用户</a>
<table>
    <tr>
        <th>名字</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${users}" var="u">
    <tr>
        <td>${u.name}</td>
        <td>${u.age}</td>
        <td>
            <a href="/getUser/${u.id}">更新</a>
        <a href="/deleteUser/${u.id}">删除</a>
        </td>
    </tr></c:forEach>
</table>
</body>
</html>
