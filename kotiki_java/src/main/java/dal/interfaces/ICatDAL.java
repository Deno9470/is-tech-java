package java.dal.interfaces;

import java.classes.Cat;
import java.util.List;

public interface ICatDAL {

    Cat findById(int id);

    void save(Cat cat);

    void update(Cat cat);

    void delete(Cat cat);

}
