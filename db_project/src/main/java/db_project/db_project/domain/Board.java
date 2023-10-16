package db_project.db_project.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor   //기본 생성자를 생성해준다. 이 경우 초기값 세팅이 필요한 final 변수가 있을 경우 컴파일 에러가 발생함으로 주의한다. @NoArgsConstructor(force=true) 를 사용하면 null, 0 등 기본 값으로 초기화 된다.
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long board_id;

    public String title;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne(mappedBy = "board", fetch = LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "feed_id")
    private BoardFeed boardFeed = new BoardFeed();


    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

    public void belongTo(User user) {
        this.user = user;
    }

    public void addBoardFeed(BoardFeed boardFeed) {
        this.boardFeed = boardFeed;
        boardFeed.belongTo(this);
    }

    //==생성 메서드==//
    public static Board createBoard(String title, String text, User user) {
        Board board = new Board();

        board.setTitle(title);
        board.setText(text);

        board.setUser(user);
        return board;
    }
}
