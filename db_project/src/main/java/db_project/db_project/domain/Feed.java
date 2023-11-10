package db_project.db_project.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Feed {

    @Id @GeneratedValue
    @Column(name = "feed_id")
    private Long feed_id;

    @Column(nullable = true)
    private int mtralPc;
    @Column(nullable = true)
    private float clciQy;
    @Column(nullable = true)
    private float naQy;
    @Column(nullable = true)
    private float dryMatter;
    @Column(nullable = true)
    private float ashsQy;
    @Column(nullable = true)
    private int feedSeqNo;
    @Column(nullable = true)
    private float crfbQy;
    @Column(nullable = true)
    private float totEdblfibrQy;
    @Column(nullable = true)
    private int feedClCode;
    @Column(nullable = true)
    private float ptssQy;
    @Column(nullable = true)
    private float mitrQy;
    @Column(nullable = true)
    private float slwtEdblfibrQy;
    @Column(nullable = true)
    private float liacQy;
    @Column(nullable = true)
    private int upperFeedClCode;
    @Column(nullable = true)
    private float fatQy;
    @Column(nullable = true)
    private float lnacQy;
    @Column(nullable = true)
    private float vtmaQy;
    @Column(nullable = true)
    private float trypQy;
    private String feedNm;
    @Column(nullable = true)
    private float crbQy;
    private String feedClCodeNm;
    @Column(nullable = true)
    private float phphQy;
    @Column(nullable = true)
    private float protQy;
    private String originNm;
    @Column(nullable = true)
    private float inslbltyEdblfibrQy;

    @OneToMany(mappedBy = "feed")
    private List<BoardFeed> boardFeeds = new ArrayList<>();


    //==연관관계 메서드==//
//    public void addBoardFeeds(BoardFeed boardFeeds) {
//        this.boardFeeds.add(boardFeeds);
//        boardFeeds.belongToFeed(this);
//    }

}
