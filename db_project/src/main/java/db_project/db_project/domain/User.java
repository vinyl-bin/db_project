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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    //연관관계 주인인 board 객체에 board값 넣어주기 위해 연관관계 메소드 작성
    public void addBoards(Board boards) {
        this.boards.add(boards);
        boards.belongTo(this);
    }
}
