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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private long feed_id;

//    @Id
    @Column(name = "feedSeqNo")
    private int feedSeqNo;

    @Column(nullable = true, name = "mtralPc")
    private int mtralPc;
    @Column(nullable = true, name = "clciQy")
    private double clciQy;
    @Column(nullable = true, name = "naQy")
    private double naQy;
    @Column(nullable = true, name = "dryMatter")
    private double dryMatter;
    @Column(nullable = true, name = "ashsQy")
    private double ashsQy;
    @Column(nullable = true, name = "crfbQy")
    private double crfbQy;
    @Column(nullable = true, name = "totEdblfibrQy")
    private double totEdblfibrQy;
    @Column(nullable = true, name = "feedClCode")
    private int feedClCode;
    @Column(nullable = true, name = "ptssQy")
    private double ptssQy;
    @Column(nullable = true, name = "mitrQy")
    private double mitrQy;
    @Column(nullable = true, name = "slwtEdblfibrQy")
    private double slwtEdblfibrQy;
    @Column(nullable = true, name = "liacQy")
    private double liacQy;
    @Column(nullable = true, name = "upperFeedClCode")
    private int upperFeedClCode;
    @Column(nullable = true, name = "fatQy")
    private double fatQy;
    @Column(nullable = true, name = "lnacQy")
    private double lnacQy;
    @Column(nullable = true, name = "vtmaQy")
    private double vtmaQy;
    @Column(nullable = true, name = "trypQy")
    private double trypQy;
    @Column(nullable = false, name = "feedNm")
    private String feedNm;
    @Column(nullable = true, name = "crbQy")
    private double crbQy;
    @Column(nullable = false, name = "feedClCodeNm")
    private String feedClCodeNm;
    @Column(nullable = true, name = "phphQy")
    private double phphQy;
    @Column(nullable = true, name = "protQy")
    private double protQy;
    @Column(nullable = true, name = "originNm")
    private String originNm;
    @Column(nullable = true, name = "inslbltyEdblfibrQy")
    private double inslbltyEdblfibrQy;

    @OneToMany(mappedBy = "feed")
    private List<BoardFeed> boardFeeds = new ArrayList<>();


    //==연관관계 메서드==//
//    public void addBoardFeeds(BoardFeed boardFeeds) {
//        this.boardFeeds.add(boardFeeds);
//        boardFeeds.belongToFeed(this);
//    }

}
