package dal.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import classes.Owner;
import dal.interfaces.IOwnerDAL;
import hibernate.HibernateSessionFactoryUtil;
import java.util.List;

public class OwnerDAL implements IOwnerDAL {
    public Owner findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    public void save(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(owner);
        transaction.commit();
        session.close();
    }

    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(owner);
        transaction.commit();
        session.close();
    }

    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(owner);
        transaction.commit();
        session.close();
    }
    public List<Owner> getAll() {
        return (List<Owner>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Owner").list();
    }

}
