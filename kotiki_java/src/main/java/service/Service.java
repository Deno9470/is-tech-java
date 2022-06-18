package service;

import classes.Cat;
import classes.Owner;
import enums.CatBreed;
import enums.CatColor;
import service.catService.CatService;
import service.ownerService.OwnerService;

import java.util.List;
import java.util.stream.Collectors;

public class Service {
    CatService catService = new CatService();
    OwnerService ownerService = new OwnerService();

    public void addOwner(Owner owner) {
        try {
            ownerService.saveOwner(owner);
            } catch (Exception e) {

            }
    }
    public Owner addOwner(String name, String bdate) {
        Owner owner = new Owner(name, bdate);
        try {
            ownerService.saveOwner(owner);
        } catch (Exception e) {

        }
        return owner;
    }
    public Cat addCat(String name, String bdate, CatBreed breed, CatColor color, Owner owner) {
        Cat cat = new Cat(name, bdate, breed, color, owner.getId());
        try {
            catService.saveCat(cat);
        } catch (Exception e) {

        }
        return cat;
    }
    public void changeCatOwner(Cat cat, Owner newOwner) {
        catService.setNewOwner(newOwner.getId(), cat);
    }
    public void removeOwnerWithCats(Owner owner) {
        ownerService.deleteOwner(owner);
    }
    public void removeCat(Cat cat) {
        catService.deleteCat(cat);
    }
    public List<Cat> getOwnerCats(Owner owner) {
        return catService.getAllCats().stream().filter(ct -> ct.getOwner_id() == owner.getId()).collect(Collectors.toList());
    }
    public void addFriendToCat(Cat cat, Cat cats_friend) {
        try {
            catService.makeFriends(cat.getId(), cats_friend.getId());
        }catch (Exception e) {

        }
    }
    public void deleteFriendOfCat(Cat cat, Cat cats_friend) {
        try {
            catService.deleteFriends(cat.getId(), cats_friend.getId());
        }catch (Exception e) {

        }
    }
    public List<Cat> getCatFriends(Cat cat) {
        return catService.getFriendsOfCat(cat.getId());
    }
}
