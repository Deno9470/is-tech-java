import com.itmo.entity.Cat;
import com.itmo.entity.Owner;
import com.itmo.enums.CatBreed;
import com.itmo.enums.CatColor;
import com.itmo.MainService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class CatsTests {
    @Autowired
    private MainService service;
    private Owner owner = new Owner("IVAN", "11.01.2001");
    private Cat cat = new Cat() ;

    public void Setup()
    {
        service.addOwner(owner);
        cat = service.addCat("Cinder", "02.03.2021", CatBreed.MAINECOON, CatColor.BLACK, owner);
    }



    public void MakeCatsFriends_TableFilled() {
        Owner owner2 = service.addOwner("PETR", "24.06.199X");
        Cat cat2 = service.addCat("Fluff", "15.01.2022", CatBreed.BRITISH, CatColor.GREY, owner2);
        service.addFriendToCat(cat,cat2);
        //Assert.assertEquals(cat2.getId(), service.getCatFriends(cat).get(0).getId());
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
            //Assert.assertTrue(true);
            service.removeOwnerWithCats(owner2);
        }
    }
}
