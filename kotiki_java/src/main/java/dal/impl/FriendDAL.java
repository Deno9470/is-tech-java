package dal.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import classes.Friend;
import dal.interfaces.IFriendDAL;
import hibernate.HibernateSessionFactoryUtil;
import java.util.List;

public class FriendDAL implements IFriendDAL {
    public Friend findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Friend.class, id);
    }

    public void save(Friend friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(friend);
        transaction.commit();
        session.close();
    }

    public void update(Friend friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(friend);
        transaction.commit();
        session.close();
    }

    public void delete(Friend friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(friend);
        transaction.commit();
        session.close();
    }

    public List<Friend> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return loadAllData(session);
    }

    private static <T> List<T> loadAllData(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) Friend.class);
        criteria.from((Class<T>) Friend.class);
        return session.createQuery(criteria).getResultList();
    }
}
