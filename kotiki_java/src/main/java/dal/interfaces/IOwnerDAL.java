package dal.interfaces;
import classes.Owner;
import java.util.List;

public interface IOwnerDAL {
    Owner findById(int id);

    void save(Owner owner);

    void update(Owner owner);

    void delete(Owner owner);

    List<Owner> getAll();

}
