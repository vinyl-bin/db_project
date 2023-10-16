package db_project.db_project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class BoardFeed {


    @Id @GeneratedValue
    @Column(name = "boardFeed_id")
    private Long boardFeed_id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;


    //==생성 메서드==//
    public static BoardFeed createBoardFeed(Feed feed) {
        BoardFeed boardFeed = new BoardFeed();
        boardFeed.setFeed(feed);
        return boardFeed;
    }

}
