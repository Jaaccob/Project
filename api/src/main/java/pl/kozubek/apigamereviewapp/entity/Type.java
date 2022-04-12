package pl.kozubek.apigamereviewapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "TYPES")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idType",updatable = false,insertable = false)
    private List<ConnectGameType> cType;

}
