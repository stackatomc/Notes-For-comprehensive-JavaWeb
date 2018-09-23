<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/23
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>输入操作数</title>
</head>
<body>

求和<br/>
<s:form action="mystruts/calSum.action">
    <s:textfield name="num1" label="数1"/>
    <s:textfield name="num2" label="数2"/>
    <s:submit value="求和"/>
</s:form>

</body>
</html>
