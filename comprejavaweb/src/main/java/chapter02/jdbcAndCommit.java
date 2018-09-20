package chapter02;

import java.sql.*;

/**
 * @author MayerFang
 * @file jdbcAndCommit
 * @Description
 * @date 2018/9/19
 */
public class jdbcAndCommit {

    public static void main(String[] args) throws SQLException{
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
                //1 关闭自动提交事务
                conn.setAutoCommit(false);
                conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

                String query="insert into course values (?,?,?)";
                pstmt=conn.prepareStatement(query);
                //pstmt第一个设置位置是1，不是0，注意
                pstmt.setInt(1,8);
                pstmt.setString(2,"C8");
                pstmt.setInt(3,1);
                pstmt.addBatch();
                pstmt.setInt(1,7);
                pstmt.setString(2,"C7");
                pstmt.setInt(3,1);
                pstmt.addBatch();

                //执行批处理
                pstmt.executeBatch();

                //2 手动提交事务
                conn.commit();
            }else{
                System.out.println("Failed to make connection!");
            }
        }catch(SQLException e){
            //3 在catch中对若出现异常进行回滚，还可以设置setSavaepoint和releaseSavepoint设置和释放保存点
            //，这样rollback可会到指定的位置而不是事务的开始位置，但不推荐这种做法
            conn.rollback();
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
