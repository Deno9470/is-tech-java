package com.itmo.entity;

import com.itmo.enums.CatBreed;
import com.itmo.enums.CatColor;
import javax.persistence.*;

@Entity(name = "Cat")
@Table(name = "cats")
public class Cat {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "cats_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence")
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

    public Cat(String name, String date, CatBreed breed, CatColor color, int owner_id) {
        this.name = name;
        this.bdate = date;
        this.breed = breed;
        this.color = color;
        this.owner_id = owner_id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return bdate;
    }

    public void setDate(String bdate) { this.bdate = bdate; }

    public CatBreed getBreed() {
        return breed;
    }

    public CatColor getColor() {
        return color;
    }

    public void setColor(CatColor color) { this.color = color; }

    public void setBreed(CatBreed breed) { this.breed = breed; }

    public int getOwner_id() { return owner_id; }

    public void setOwner_id(int id) { owner_id = id;}
}
