package db_project.db_project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class BoardFeed {

//    private final EntityManager em;


    @Id @GeneratedValue
    @Column(name = "boardFeed_id")
    private Long boardFeed_id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    //==연관관계 메서드==//
    public void belongTo(Board board) {
        this.board = board;
    }

    public void belongToFeed(Feed feed) {
        this.feed = feed;
    }



    //==생성 메서드==//
    public static BoardFeed createBoardFeed(Board board, Feed feed) {
        BoardFeed boardFeed = new BoardFeed();
        boardFeed.setBoard(board);
        boardFeed.setFeed(feed);

        boardFeed.belongTo(board);
        boardFeed.belongToFeed(feed);

//        em.persist(boardFeed);
        return boardFeed;
    }

}
