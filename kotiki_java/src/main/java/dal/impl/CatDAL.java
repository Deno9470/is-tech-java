package dal.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import classes.Cat;
import dal.interfaces.ICatDAL;
import hibernate.HibernateSessionFactoryUtil;
import java.util.List;

public class CatDAL implements ICatDAL {


    public static Cat findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);
    }

    public void save(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(cat);
        transaction.commit();
        session.close();
    }

    public void update(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(cat);
        transaction.commit();
        session.close();
    }

    public static void delete(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(cat);
        transaction.commit();
        session.close();
    }
    public static List<Cat> getAll() {
        return (List<Cat>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Cats").list();
    }
}
