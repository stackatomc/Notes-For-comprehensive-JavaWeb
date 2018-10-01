package service;

import model.UserInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MayerFang
 * @file HibernateMain
 * @Description
 * @date 2018/10/1
 */
public class HibernateMain {


    public static void main(String[] args) {
        SessionFactory sessionFactory=null;

        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        sessionFactory=(SessionFactory) ctx.getBean("sessionFactory");

        Session session=sessionFactory.openSession();

        Transaction tx=session.beginTransaction();

        UserInfo user=new UserInfo();
        user.setUserID(3);
        user.setUserName("Peter");
        user.setPwd("333");
        user.setUserType("Vip");

        try{
            session.save(user);
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }

        List<UserInfo> users=new ArrayList<UserInfo>();
        String hqlstr="from model.UserInfo";
        Query query=session.createQuery(hqlstr);
        users=query.list();
        System.out.println("users size is:"+users.size());
        for(UserInfo u:users){
            System.out.println(u.getUserName()+"    "+u.getUserType());
        }

        session.close();
        sessionFactory.close();

    }
}
