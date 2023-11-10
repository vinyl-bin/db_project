package db_project.db_project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Feed {

    @Id @GeneratedValue
    @Column(name = "feed_id")
    private Long feed_id;

    private int mtralPc;
    private float clciQy;
    private float naQy;
    private float dryMatter;
    private float ashsQy;
    private int feedSeqNo;
    private float crfbQy;
    private float totEdblfibrQy;
    private int feedClCode;
    private float ptssQy;
    private float mitrQy;
    private float slwtEdblfibrQy;
    private float liacQy;
    private int upperFeedClCode;
    private float fatQy;
    private float lnacQy;
    private float vtmaQy;
    private float trypQy;
    private String feedNm;
    private float crbQy;
    private String feedClCodeNm;
    private float phphQy;
    private float protQy;
    private String originNm;
    private float inslbltyEdblfibrQy;

    @OneToMany(mappedBy = "feed")
    private List<BoardFeed> boardFeeds = new ArrayList<>();


    //==연관관계 메서드==//
//    public void addBoardFeeds(BoardFeed boardFeeds) {
//        this.boardFeeds.add(boardFeeds);
//        boardFeeds.belongToFeed(this);
//    }

}
