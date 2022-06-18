package com.itmo;

import com.itmo.entity.Cat;
import com.itmo.entity.Owner;
import com.itmo.enums.CatBreed;
import com.itmo.enums.CatColor;
import com.itmo.enums.Role;
import com.itmo.service.CatService;
import com.itmo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MainService {
    @Autowired
    private CatService catServiceImpl;
    @Autowired
    private OwnerService ownerServiceImpl;

    public void addOwner(Owner owner) {
        try {
            ownerServiceImpl.saveOwner(owner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Owner addOwner(String name, String bdate) {
        Owner owner = new Owner(name, bdate);
        try {
            ownerServiceImpl.saveOwner(owner);
        } catch (Exception e) {

        }
        return owner;
    }
    public Owner getOwnerById(int id) {
        return ownerServiceImpl.findById(id);
    }
    public List<Owner> getAllOwners() {
        return ownerServiceImpl.getAllOwners();
    }

    public Cat addCat(String name, String bdate, CatBreed breed, CatColor color, Owner owner){
        Cat cat = new Cat(name, bdate, breed, color, owner.getId());
        try {
            catServiceImpl.saveCat(cat);
        } catch (Exception e) {
            //throw new CatsException("Cats can not have the same names");
        }
        return cat;
    }
    public void addCat(Cat cat) {
        try {
            catServiceImpl.saveCat(cat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Cat> getAllCats() {
        return catServiceImpl.getAllCats();
    }
    public Cat getCatById(int id){ return catServiceImpl.findCatById(id); }

    public void removeOwnerWithCats(Owner owner) {
        ownerServiceImpl.deleteOwner(owner);
    }
    public void removeCat(Cat cat) {
        catServiceImpl.deleteCat(cat);
    }
    public void deleteCat(int id) {
        Cat cat = catServiceImpl.findCatById(id);
        catServiceImpl.deleteCat(cat);
    }
    public void deleteOwner(int id) {
        Owner owner = ownerServiceImpl.getAllOwners().stream().filter(ow -> ow.getId() == id).findFirst().orElse(null);
        ownerServiceImpl.deleteOwner(owner);
    }
    public List<Cat> getOwnedCats(Owner owner) {
        return catServiceImpl.getAllCats().stream().filter(ct -> ct.getOwner_id() == owner.getId()).collect(Collectors.toList());
    }


    public void addFriendToCat(Cat cat, Cat cats_friend) {
        try {
            catServiceImpl.makeFriends(cat.getId(), cats_friend.getId());
        }catch (Exception e) {

        }
    }
    public void deleteFriendOfCat(Cat cat, Cat cats_friend) {
        try {
            catServiceImpl.deleteFriends(cat.getId(), cats_friend.getId());
        }catch (Exception e) {

        }
    }
    public List<Cat> getCatFriends(Cat cat) {
        return catServiceImpl.getFriendsOfCat(cat.getId());
    }
    public Map<String, String> getNotFriendsMap(int id) {
        Cat cat = catServiceImpl.findCatById(id);
        List<Cat> notFriends = getAllCats();
        notFriends.removeAll(getCatFriends(cat));
        notFriends.remove(cat);
        return IntStream.range(0, notFriends.size()).boxed()
                .collect(Collectors.toMap(i -> String.valueOf(notFriends.get(i).getId()) , i-> notFriends.get(i).getName()));
    }
    public Map<String,String> getAllBreeds() {
        List<String> stringBreeds =  Arrays.stream(CatBreed.values()).map(Enum::toString).collect(Collectors.toList());
        stringBreeds.remove("TESTBREED");
        List<String> lowerStringBreeds = stringBreeds.stream().map(String::toLowerCase).collect(Collectors.toList());
        List<String> finalLowerStringBreeds = lowerStringBreeds.stream().map(str -> str.substring(0,1).toUpperCase()+str.substring(1)).collect(Collectors.toList());
        Map<String, String> days = IntStream.range(0, stringBreeds.size()).boxed()
                .collect(Collectors.toMap(i -> stringBreeds.get(i), i -> finalLowerStringBreeds.get(i)));
        return days;
    }
    public Map<String,String>getAllColors() {
        List<String> stringColors =  Arrays.stream(CatColor.values()).map(Enum::toString).collect(Collectors.toList());
        stringColors.remove("TESTCOLOR");
        List<String> lowerStringColors = stringColors.stream().map(String::toLowerCase).collect(Collectors.toList());
        List<String> finalLowerStringBreeds = lowerStringColors.stream().map(str -> str.substring(0,1).toUpperCase()+str.substring(1)).collect(Collectors.toList());
        Map<String, String> days = IntStream.range(0, stringColors.size()).boxed()
                .collect(Collectors.toMap(i -> stringColors.get(i), i -> finalLowerStringBreeds.get(i)));
        return days;
    }
    public Map<String,String>getAllRoles() {
        List<String> stringRoles =  Arrays.stream(Role.values()).map(Enum::toString).collect(Collectors.toList());
        List<String> lowerStringRoles = stringRoles.stream().map(String::toLowerCase).collect(Collectors.toList());
        List<String> finalLowerStringRoles = lowerStringRoles.stream().map(str -> str.substring(0,1).toUpperCase()+str.substring(1)).collect(Collectors.toList());
        Map<String, String> days = IntStream.range(0, stringRoles.size()).boxed()
                .collect(Collectors.toMap(i -> stringRoles.get(i), i -> finalLowerStringRoles.get(i)));
        return days;
    }


    public boolean isValidCat(Cat cat) {
        return getOwnedCats(getOwnerById(cat.getOwner_id())).stream().noneMatch(i -> i.getName().equals(cat.getName()));
    }
}
