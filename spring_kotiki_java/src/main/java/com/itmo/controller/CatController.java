package com.itmo.controller;

import com.itmo.MainService;
import com.itmo.entity.Cat;
import com.itmo.entity.Friend;
import com.itmo.entity.Owner;
import com.itmo.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/cats")
public class CatController {
    private Authentication authOfUser;
    @Autowired
    private MainService service;
    @RequestMapping("/")
    public String welcome(Model model) {
        return "index";
    }



    @PreAuthorize("hasAuthority('write')")
    @RequestMapping("/owners/")
    public String allOwnerList(Model model) {
        List<Owner> ownerList = service.getAllOwners();
        model.addAttribute("ownerList", ownerList);
        model.addAttribute("taskSize", ownerList.size());
        return "ownerlist";
    }

    @PreAuthorize("hasAuthority('write')")
    @RequestMapping(value = "/owners/add", method=RequestMethod.GET)
    public String addOwner( Model model) {
        model.addAttribute("roles", service.getAllRoles());
        model.addAttribute("owner" , new Owner());
        return "ownerAdd";
    }
    @PreAuthorize("hasAuthority('write')")
    @RequestMapping(value = "/owners/add", method=RequestMethod.POST)
    public String saveOwner(@ModelAttribute Owner owner, Model model) {
        model.addAttribute("owner" , owner);
        if (owner.getName().isEmpty() || owner.getDate().isEmpty() ) {
            String error = "Fields must not be empty";
            model.addAttribute("inputError", error);
            return "ownerAdd";
        }
        owner.setPassword(WebSecurityConfig.passwordEncoder().encode(owner.getPassword()));
        service.addOwner(owner);
        return "redirect:/owners/";
    }
    @RequestMapping(value = "/owners/delete/{id}")
    public String deleteOwner(@PathVariable int id) {
        service.deleteOwner(id);
        return "redirect:/owners/";
    }

    @RequestMapping(value = "/owners/{id}")
    public String showOwnerInfo(@PathVariable int id, Model model) {
        Owner owner = service.getOwnerById(id);
        List<Cat> catList = service.getOwnedCats(owner);
        model.addAttribute("catList", catList);
        model.addAttribute("owner", owner);
        return "ownerInfo";
    }
    @RequestMapping(value = "/cats/{id}/add", method=RequestMethod.GET)
    public String addCat(@PathVariable int id, Model model) {
        Cat cat = new Cat();
        cat.setOwner_id(id);
        model.addAttribute("cat" , cat);
        model.addAttribute("owner_id", id);
        model.addAttribute("breeds", service.getAllBreeds());
        model.addAttribute("colors",service.getAllColors());
        return "catAdd";
    }
    @RequestMapping(value = "/cats/{id}/add", method=RequestMethod.POST)
    public String saveCat(@ModelAttribute Cat cat, Model model) {
        model.addAttribute("cat", cat);
        if(!service.isValidCat(cat)) {
            String error = "Cats must have unique names";
            model.addAttribute("inputError", error);
            model.addAttribute("cat", cat);
            model.addAttribute("owner_id", cat.getOwner_id());
            model.addAttribute("breeds", service.getAllBreeds());
            model.addAttribute("colors", service.getAllColors());
            return "catAdd";
        }
        if (cat.getName().isEmpty() || cat.getDate().isEmpty() ) {
            String error = "Fields must not be empty";
            model.addAttribute("inputError", error);
            model.addAttribute("cat" , cat);
            model.addAttribute("owner_id", cat.getOwner_id());
            model.addAttribute("breeds", service.getAllBreeds());
            model.addAttribute("colors",service.getAllColors());
            return "catAdd";
        }
        service.addCat(cat);
        return String.format("redirect:/owners/%d", cat.getOwner_id());
    }

    @PreAuthorize("hasAuthority('read')")
    @RequestMapping(value = "/cats/{id}")
    public String catInfo(@PathVariable int id, Model model) {
        Cat cat = service.getCatById(id);
        Owner owner = service.getOwnerById(cat.getOwner_id());
        List<Cat> friendList = service.getCatFriends(cat);
        model.addAttribute("cat", cat);
        model.addAttribute("owner_name", owner.getName());
        model.addAttribute("friendList", friendList);
        return "catInfo";
    }

    @RequestMapping(value = "/cats/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        service.deleteCat(id);
        return "redirect:/owners/";
    }

    @RequestMapping(value = "cats/{id}/friend")
    public String makeFriends(@PathVariable int id, Model model) {
        model.addAttribute("friend", new Friend());
        model.addAttribute("notFriends", service.getNotFriendsMap(id));
        model.addAttribute("cat", service.getCatById(id));
        return "friendAdd";
    }
    @RequestMapping(value = "cats/{id}/friend", method = RequestMethod.POST)
    public String saveFriends(@ModelAttribute Friend friend, @PathVariable int id, Model model) {
        service.addFriendToCat(service.getCatById(friend.getFirst()),service.getCatById(friend.getSecond()));
        return String.format("redirect:/cats/%d", id);
    }
    @RequestMapping(value = "cats/{id}/delete/{idf}")
    public String deleteFriends(@PathVariable int id, @PathVariable int idf) {
        service.deleteFriendOfCat(service.getCatById(id), service.getCatById(idf));
        return String.format("redirect:/cats/%d", id);
    }
    @PreAuthorize("hasAuthority('read')")
    @RequestMapping(value = "/search")
    public String colorSearch(Model model, Authentication authentication) {
        authOfUser = authentication;
        Map<String, String> breeds = service.getAllBreeds();
        Map<String,String> colors = service.getAllColors();
        breeds.put("TESTBREED", "*");
        colors.put("TESTCOLOR", "*");
        model.addAttribute("breeds", breeds);
        model.addAttribute("colors",colors);
        model.addAttribute("modelCat", new Cat());
        return "searchPage";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchResult(@ModelAttribute Cat cat, Model model) {
        List<Cat> result = new ArrayList<>();
        if(!cat.getBreed().toString().equals("TESTBREED") && !cat.getColor().toString().equals("TESTCOLOR")) {
                result = service.getAllCats().stream()
                        .filter(ct -> ct.getBreed() == cat.getBreed() && ct.getColor() == cat.getColor())
                        .collect(Collectors.toList());
        } else if (cat.getBreed().toString().equals("TESTBREED") && cat.getColor().toString().equals("TESTCOLOR")) {
            result = service.getAllCats();
        } else if (cat.getBreed().toString().equals("TESTBREED")) {
            result = service.getAllCats().stream().filter(ct -> ct.getColor() == cat.getColor()).collect(Collectors.toList());
        } else if (cat.getColor().toString().equals("TESTCOLOR"))
            result = service.getAllCats().stream().filter(ct -> ct.getBreed() == cat.getBreed()).collect(Collectors.toList());
        if(!cat.getName().isEmpty())
            result = service.getAllCats().stream()
                    .filter(ct -> ct.getName().equals(cat.getName()))
                    .collect(Collectors.toList());
            if(authOfUser.getAuthorities().size() != 2)
                result.retainAll(service.getAllCats().stream()
                        .filter(ct -> service.getOwnerById(ct.getOwner_id()).getName()
                                .equals(authOfUser.getName())).collect(Collectors.toList()));
        model.addAttribute("searchList", result);
        return "searchRes";
    }


}
