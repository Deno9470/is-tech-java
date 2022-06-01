package java.dal.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.classes.Friend;
import java.dal.interfaces.IFriendDAL;
import java.hibernate.HibernateSessionFactoryUtil;
import java.util.List;
import java.util.Objects;

public class FriendDAL implements IFriendDAL {
    public Friend findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Friend.class, id);
    }

    public void save(Friend friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(friend);
        transaction.commit();
        session.close();
    }

    public void update(Friend friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(friend);
        transaction.commit();
        session.close();
    }

    public void delete(Friend friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(friend);
        transaction.commit();
        session.close();
    }

    public List<Friend> getAll() {
        return (List<Friend>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Friends").list();
    }

}
