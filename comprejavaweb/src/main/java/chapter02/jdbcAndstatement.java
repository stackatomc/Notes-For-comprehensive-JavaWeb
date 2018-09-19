package chapter02;

import java.sql.*;

/**
 * @author MayerFang
 * @file jdbcAndstatement
 * @Description try...catch、抛异常处理、finally关闭等回收
 * @date 2018/9/19
 */
public class jdbcAndstatement {

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        Connection conn=null;
        Statement stmt=null;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student"
            ,"root","root");
            if(conn!=null){
                stmt=conn.createStatement();
                String query="select CID,CName from course";
                //String query="select CID,CName from course where cid=1 and cname='1'or '1'='1'";
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next()){
                    System.out.print(rs.getInt("CID")+" ");
                    System.out.println(rs.getString("CName"));
                }
            }else{
                System.out.println("Failed to make connection!");
            }
        }catch(SQLException e){
            System.out.println("Check the JDBC Driver or Connection!");
            e.printStackTrace();
        }finally{
            try{
                stmt.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}

/*对Statement设计SQL注入
*
* UserName
* PassWord
*
* normal:select username from users where username='UserName' and pwd='PassWord';
* SQL注入:select username from users where username='1' and pwd='1' or '1'='1';
* 解决方法:使用PreparedStatement防SQL注入，避免'1' or '1'='1'
* */
