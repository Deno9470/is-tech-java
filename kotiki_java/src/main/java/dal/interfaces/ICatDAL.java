package java.dal.interfaces;

import java.classes.Cat;
import java.hibernate.HibernateSessionFactoryUtil;
import java.util.List;

public interface ICatDAL {

    static Cat findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);
    }

    void save(Cat cat);

    void update(Cat cat);

    static void delete(Cat cat) {}
    static List<Cat> getAll() {
        return null;
    }
}
