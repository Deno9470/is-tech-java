package dal.interfaces;

import classes.Friend;
import java.util.List;

public interface IFriendDAL {
    Friend findById(int id);
    void save(Friend friend);
    void update(Friend friend);
    void delete(Friend friend);
    List<Friend> getAll();
}
