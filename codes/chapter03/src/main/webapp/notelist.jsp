<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/20
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.NoteBean,bean.note" %>
<%@ page import="java.util.List,java.util.Iterator" %>
<html>
<head>
    <title>JAVAWEBFULL-notelist</title>
</head>
<body>
    <table>
        <tr>
            <td width="100">留言标题</td>
            <td width="200">内容</td>
            <td width="50">作者姓名</td>
            <td width="200">发布时间</td>
        </tr>
        <%
            NoteBean noteBean=new NoteBean();
            List<note> notelist=noteBean.findAll();
            Iterator<note> noteiter=notelist.iterator();
            while(noteiter.hasNext()){
                note n=noteiter.next();
                %>
        <tr>
            <td><%=n.getTitle()%></td>
            <td><%=n.getContent()%></td>
            <td><%=n.getAuthor()%></td>
            <td><%=n.getCreateTime()%></td>
        </tr>
            <%
            }
        %>
    </table>

</body>
</html>
