package classes;

import jakarta.persistence.*;

@Entity
@Table(name= "owners")
public class Owner {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "owners_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "bdate")
    private String bdate;

    public Owner(){
    }

    public void setId(int id) {
        this.id = id;
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
