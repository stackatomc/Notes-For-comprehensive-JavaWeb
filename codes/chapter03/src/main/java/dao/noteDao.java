package dao;

import bean.note;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MayerFang
 * @file noteDao
 * @Description
 * @date 2018/9/20
 */
public class noteDao {

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MySQL Driver?");
            e.printStackTrace();
            return null;
        }
        Connection conn=null;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
            if(conn!=null)
                return conn;
            else{
                System.out.println("Failed to make conenction");
                return null;
            }
        }catch(SQLException e){
            System.out.println("Failed to make connection!");
        }
        return null;
    }

    public List<note> findAll(){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            conn=getConnection();
            if(conn!=null){
                String query="select id,title,content,author,create_time from notes";
                stmt=conn.createStatement();
                rs=stmt.executeQuery(query);
                List<note> notes=new ArrayList<note>();
                while(rs.next()){
                    note n=new note();
                    n.setId(rs.getInt("id"));
                    n.setTitle(rs.getString("title"));
                    n.setContent(rs.getString("content"));
                    n.setAuthor(rs.getString("author"));
                    n.setCreateTime(rs.getTimestamp("create_time"));
                    notes.add(n);
                }
                return notes;
            }else{
                System.out.println("Failed to make connection");
                return null;
            }
        }catch(SQLException e){
            System.out.println("Check connect or statement");
            e.printStackTrace();
            return null;
        }finally{
            try{
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
