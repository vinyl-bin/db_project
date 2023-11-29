package db_project.db_project.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Null;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long board_id;

    public String title;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    private String imageName;

    private String imagePath;


    /**
     * @JoinColumn은 연관관계주인인 엔티티가 다른 엔티티의 외래키를 가져올때 사용한다.
     */


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "board", fetch = LAZY)
    private BoardFeed boardFeed = new BoardFeed();




    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

    public void addBoardFeed(BoardFeed boardFeed) {
        this.boardFeed = boardFeed;
        boardFeed.setBoard(this);
    }


    //==생성 메서드==//
    public static Board createBoardWithFile(User user, BoardFeed boardFeed, String title, String text, String fileName, String filePath) {
        Board board = new Board();
        board.setUser(user);
        board.addBoardFeed(boardFeed);
        board.setTitle(title);
        board.setText(text);
        board.setImageName(fileName);
        board.setImagePath(filePath);
        return board;
    }

    public static Board createBoard(User user, BoardFeed boardFeed, String title, String text) {
        Board board = new Board();
        board.setUser(user);
        board.addBoardFeed(boardFeed);
        board.setTitle(title);
        board.setText(text);
        board.setImageName(null);
        board.setImagePath(null);
        return board;
    }

    //==비즈니스 로직==//
//    public void cancel() {
//        boardFeed.cancel();
//    }


}
