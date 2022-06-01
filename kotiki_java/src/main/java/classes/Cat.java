package java.classes;

import java.enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "bdate")
    private String bdate;
    @Column(name = "owner_id")
    private int owner_id;
    @Column(name = "breed")
    private CatBreed breed;
    @Column(name = "color")
    private CatColor color;

    public Cat() {
    }

    public Cat(String name, String date, CatBreed breed, CatColor color) {
        this.name = name;
        this.bdate = date;
        this.breed = breed;
        this.color = color;
    }


    public String getDate() {
        return bdate;
    }

    public CatBreed getBreed() {
        return breed;
    }

    public CatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
