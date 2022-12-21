package com.itmo.entity;
import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "friends_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence")
    private int id;
    @Column(name = "first_cat")
    private int first;
    @Column(name = "second_cat")
    private int second;

    public Friend() {
    }

    public Friend(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getId() {
        return id;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) { this.second = second; }

    public void setFirst(int first) { this.first = first; }

    public void setId(int id) { this.id = id; }
}