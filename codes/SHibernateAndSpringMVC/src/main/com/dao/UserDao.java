package com.dao;

import com.model.User;
import org.hibernate.Query;

import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author MayerFang
 * @file UserDao
 * @Description
 * @date 2018/10/2
 */
public class UserDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAll(){

        String hql="from user";
        Query q=sessionFactory.getCurrentSession().createQuery(hql);
        return q.list();
    }

    public void addUser(User user){
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUser(String id){
        String hql="from User where id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,id);
        return (User)query.uniqueResult();
    }

    public void deleteUser(String id){
        String hql="delete from User hwere id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,id);
        query.executeUpdate();
    }

    public void modifyUser(User user){
        String hql="update User set name=? ,age=? where id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,user.getUserName());
        query.setInteger(1,user.getAge());
        query.setInteger(2,user.getUserID());
        query.executeUpdate();
    }


}
