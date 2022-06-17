package entity;
import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "friends_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
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
}