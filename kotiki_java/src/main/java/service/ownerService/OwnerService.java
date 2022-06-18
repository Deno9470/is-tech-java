package service.ownerService;

import classes.Owner;
import dal.impl.OwnerDAL;
import tools.CatsException;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerService {
    private OwnerDAL ownerDAL = new OwnerDAL();

    public void saveOwner(Owner owner) throws CatsException {
        List<Owner> unvalidOwners = ownerDAL.getAll().stream().filter(ow -> ow.getId() == owner.getId()).collect(Collectors.toList());
        if(unvalidOwners.size() > 0)
            throw new CatsException("Owner is already saved");
        ownerDAL.save(owner);
    }

    public List<Owner> getAllOwners() {
        return ownerDAL.getAll();
    }

    public void deleteOwner(Owner owner) {
        ownerDAL.delete(owner);
    }

    public Owner findById(int id) {
        return ownerDAL.findById(id);
    }
}
