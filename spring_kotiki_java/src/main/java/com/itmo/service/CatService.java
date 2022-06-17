package com.itmo.service;


import com.itmo.entity.Cat;
import com.itmo.repository.CatRepository;

import com.itmo.entity.Friend;
import com.itmo.repository.FriendRepository;

import com.itmo.tools.CatsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;
    @Autowired
    private FriendRepository friendRepository;
    public CatService(){}

    public void saveCat(Cat cat) throws CatsException {
        if (cat == null)
            throw new CatsException("Invalid cat.");
        catRepository.save(cat);
    }

    public Cat findCatById(int id) {
        return catRepository.findById(id).orElse(null);
    }

    public List<Cat> getAllCats() {
        return catRepository.getAll();
    }

    public void deleteById(int id) throws CatsException {
        Optional<Cat> cat = catRepository.findById(id);
        if(cat.isEmpty())
            throw new CatsException("This cat does not exist");

        catRepository.deleteById(id);
    }

    public void makeFriends(int idOfFirstCat, int idOfSecondCat) throws CatsException {
        if (idOfFirstCat == idOfSecondCat)
            throw new CatsException("Invalid Id");
        if(getFriendsOfCat(idOfFirstCat).stream().anyMatch(i -> i.getId() == idOfSecondCat))
            throw new CatsException("Cats already friends");

        Friend friend = new Friend(idOfFirstCat, idOfSecondCat);
        friendRepository.save(friend);
    }

    public void deleteFriends(int idOfFirstCat, int idOfSecondCat) throws CatsException {
        if (idOfFirstCat == idOfSecondCat)
            throw new CatsException("Invalid Id");
        Friend friend = friendRepository.findAll().stream().filter(fr -> fr.getFirst() == idOfFirstCat).findFirst().orElse(null);
        friendRepository.delete(friend);
    }

    public List<Cat> getFriendsOfCat(int idOfCat) {
        List<Cat> FriendsOfCat = new ArrayList<>();
        List<Friend> allFriends = friendRepository.findAll();
        for (Friend each: allFriends) {
            if(each.getFirst() == idOfCat)
                FriendsOfCat.add(findCatById(each.getSecond()));
            if(each.getSecond() == idOfCat)
                FriendsOfCat.add(findCatById(each.getFirst()));
        }
        return FriendsOfCat;

    }

    public void deleteCat(Cat cat) {
        catRepository.delete(cat);
    }
}
