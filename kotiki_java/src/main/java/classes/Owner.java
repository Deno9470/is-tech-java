package java.classes;

import jakarta.persistence.*;

@Entity
@Table(name="Owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "bdate")
    private String bdate;

    public Owner(){
    }

    public Owner(String  name, String bdate) {
        this.name = name;
        this.bdate = bdate;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public String getBirthDate() {
        return bdate;
    }
}
