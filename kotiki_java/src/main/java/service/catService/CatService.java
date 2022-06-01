package java.service.catService;
import java.classes.Friend;
import java.dal.impl.CatDAL;
import java.classes.Cat;
import java.dal.impl.FriendDAL;
import java.tools.CatsException;
import java.util.ArrayList;
import java.util.List;

public class CatService {
    private final CatDAL catDAL = new CatDAL();
    private final FriendDAL friendDAL = new FriendDAL();

    public void saveCat(Cat cat) throws CatsException {
        if (cat == null)
            throw new CatsException("Invalid cat.");

        catDAL.save(cat);
    }

    public Cat findCatById(int id) {
        return CatDAL.findById(id);
    }

    public List<Cat> findAllCats() {
        return CatDAL.getAll();
    }

    public void deleteById(int id) {
        Cat cat = CatDAL.findById(id);
        if (cat == null)
            return;

        CatDAL.delete(cat);
    }



    public void makeFriends(int idOfFirstCat, int idOfSecondCat) throws CatsException {
        if (idOfFirstCat == idOfSecondCat)
            throw new CatsException("Invalid Id");
        Friend friend = new Friend(idOfFirstCat, idOfSecondCat);
        friendDAL.save(friend);
    }

    public void deleteFriends(int idOfFirstCat, int idOfSecondCat) throws CatsException {
        if (idOfFirstCat == idOfSecondCat)
            throw new CatsException("Invalid Id");

        Friend friend = new Friend(idOfFirstCat, idOfSecondCat);
    }

    public List<Cat> getFriendsOfCat(int idOfCat) {
        List<Cat> FriendsOfCat = new ArrayList<>();
        List<Friend> allFriends = friendDAL.getAll();
        for (Friend each: allFriends) {
            if(each.getFirst() == idOfCat)
                FriendsOfCat.add(findCatById(each.getSecond()));
            if(each.getSecond() == idOfCat)
                FriendsOfCat.add(findCatById(each.getFirst()));
        }
        return FriendsOfCat;

    }
}
