<%--
  Created by IntelliJ IDEA.
  User: MayerFang
  Date: 2018/9/20
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%-- 注意指令的使用 --%>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.*" pageEncoding="GBK"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>


<html>
<head>
    <title>Pure JSP</title>
</head>
<body>
<%
    // 提前Dependencies加入Tomcat/lib下的servlet-api.jar
    if(request.getParameter("username")==null){
%>
<form method="post" action="login.jsp" name="userInfo" id="userInfo">
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

<%
    }else{
        String username=request.getParameter("username").toString();
        String password="";
        if(request.getParameter("password")!=null){
            password=request.getParameter("password").toString();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
            if(conn!=null){
                String query="select Sname from student where Sname=? and Password=?";
                pstmt=conn.prepareStatement(query);
                pstmt.setString(1,username);
                pstmt.setString(2,password);

                rs=pstmt.executeQuery();
                if(rs.next()){
        %>
    Welcome,<%=rs.getString("sname")%>
    <%
                }else{
                    %>
    Warming... INVALID ACCOUNT!
<form method="post" action="login.jsp" name="userInfo" id="userInfo">
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

    <%
                }
            }else{
                    System.out.println("Failed to make connection!");
                }
        }catch(SQLException e){
                    System.out.println("Check the JDBC Driver or Conenction!");
                    e.printStackTrace();
        }finally{
            try{
                rs.close();
                pstmt.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
%>


</body>
</html>
