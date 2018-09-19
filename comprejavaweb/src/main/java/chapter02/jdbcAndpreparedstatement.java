package chapter02;

import java.sql.*;

/**
 * @author MayerFang
 * @file jdbcAndpreparedstatement
 * @Description 预处理、防SQL注入、批处理
 * @date 2018/9/19
 */
public class jdbcAndpreparedstatement {

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student"
            ,"root","root");
            if(conn!=null){
                String query="insert into course values (?,?,?)";
                pstmt=conn.prepareStatement(query);
                //pstmt第一个设置位置是1，不是0，注意
                pstmt.setInt(1,6);
                pstmt.setString(2,"C6");
                pstmt.setInt(3,1);
                pstmt.addBatch();
                pstmt.setInt(1,7);
                pstmt.setString(2,"C7");
                pstmt.setInt(3,1);
                pstmt.addBatch();

                //执行批处理
                pstmt.executeBatch();
                ResultSet rs=pstmt.executeQuery("select cid,cname from course");
                while(rs.next()){
                    System.out.print(rs.getInt("CID")+" ");
                    System.out.println(rs.getString("CName"));
                }
            }else{
                System.out.println("Failed to make connection!");
            }
        }catch(SQLException e){
            System.out.println("Some of students were not inserted corrently." +
                    "please check the student table and insert manually.");
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
