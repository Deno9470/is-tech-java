package com.itmo.service;

import com.itmo.entity.Owner;
import com.itmo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.itmo.tools.CatsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerDAL;

    public void saveOwner(Owner owner) throws CatsException {
        List<Owner> invalidOwners = ownerDAL.findAll().stream().filter(ow -> ow.getId() == owner.getId()).collect(Collectors.toList());
        if(invalidOwners.size() > 0)
            throw new CatsException("Owner is already saved");
        ownerDAL.save(owner);
    }

    public List<Owner> getAllOwners() {
        return ownerDAL.findAll();
    }

    public void deleteOwner(Owner owner) {
        ownerDAL.delete(owner);
    }

    public Owner findById(int id) {
        return ownerDAL.findById(id).orElse(null);
    }
}
