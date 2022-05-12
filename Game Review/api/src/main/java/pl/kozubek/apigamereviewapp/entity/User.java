package pl.kozubek.apigamereviewapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_Name", length = 30)
    private String firstName;

    @Column(name = "last_Name", length = 30)
    private String lastName;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = true)
    private String nick;

    @Column(length = 30, unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_User", updatable = false, insertable = false)
    private List<Review> reviews;

}
