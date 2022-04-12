package pl.kozubek.apigamereviewapp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_Name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_Name", length = 30, nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = true)
    private String nick;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_User", updatable = false, insertable = false)
    private List<Review> reviews;

}
