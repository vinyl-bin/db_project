package db_project.db_project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")  //pk 지정
    private Long user_id;

    private String name;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();


}
