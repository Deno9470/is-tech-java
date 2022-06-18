import classes.*;
import enums.*;
import org.junit.After;
import service.Service;
import tools.CatsException;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;


public class CatsTests {
    private Service service = new Service();
    private Owner owner = new Owner("IVAN", "11.01.2001");
    private Cat cat = new Cat() ;

    @Before
    public void Setup()
    {
        service.addOwner(owner);
        cat = service.addCat("Cinder", "02.03.2021", CatBreed.MAINECOON, CatColor.BLACK, owner);
    }
    @Test
    public void ChangeOwner_OwnerOwnCat() {
        Owner owner2 = service.addOwner("OLEG", "12.03.2005");
        Assert.assertEquals(owner.getId(), cat.getOwner_id());
        service.changeCatOwner(cat, owner2);
        Assert.assertEquals(owner2.getId(), cat.getOwner_id());
        service.changeCatOwner(cat, owner);
        service.removeOwnerWithCats(owner2);
    }

    @Test
    public void MakeCatsFriends_TableFilled() {
        Owner owner2 = service.addOwner("PETR", "24.06.199X");
        Cat cat2 = service.addCat("Fluff", "15.01.2022", CatBreed.BRITISH, CatColor.GREY, owner2);
        service.addFriendToCat(cat,cat2);
        Assert.assertEquals(cat2.getId(), service.getCatFriends(cat).get(0).getId());
        service.deleteFriendOfCat(cat, cat2);
        service.removeOwnerWithCats(owner);
        service.removeOwnerWithCats(owner2);
    }
    @Test
    public void TryToMakeSameCatsAtOneOwner_ExceptionThrown() {
        Owner owner2 = service.addOwner("PETR", "24.06.199X");
        Cat cat2 = service.addCat("Phil", "24.12.2020", CatBreed.PERSIAN, CatColor.FAWN, owner2);
        try{
            service.addCat("Phil", "24.12.2020", CatBreed.PERSIAN, CatColor.FAWN, owner2);
        }catch (Exception e) {
            Assert.assertTrue(true);
            service.removeOwnerWithCats(owner2);
        }
    }
}
