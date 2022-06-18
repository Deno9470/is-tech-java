package service.catService;
import classes.Friend;
import classes.Owner;
import dal.impl.CatDAL;
import classes.Cat;
import dal.impl.FriendDAL;
import tools.CatsException;
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
    public void setNewOwner(int id, Cat cat) {
        cat.setOwner_id(id);
        catDAL.update(cat);
    }

    public Cat findCatById(int id) {
        return CatDAL.findById(id);
    }

    public List<Cat> getAllCats() {
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
    public void deleteCat(Cat cat) {
        CatDAL.delete(cat);
    }
}
