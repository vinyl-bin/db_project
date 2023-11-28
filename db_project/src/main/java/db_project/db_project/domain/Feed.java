package db_project.db_project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Feed {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private long feed_id;


    @Column(name = "feedSeqNo")
    private Integer feedSeqNo;
    @Column(nullable = true, name = "mtralPc")
    private Integer mtralPc;
    @Column(nullable = true, name = "clciQy")
    private Double clciQy;
    @Column(nullable = true, name = "naQy")
    private Double naQy;
    @Column(nullable = true, name = "dryMatter")
    private Double dryMatter;
    @Column(nullable = true, name = "ashsQy")
    private Double ashsQy;
    @Column(nullable = true, name = "crfbQy")
    private Double crfbQy;
    @Column(nullable = true, name = "totEdblfibrQy")
    private Double totEdblfibrQy;
    @Column(nullable = true, name = "feedClCode")
    private Integer feedClCode;
    @Column(nullable = true, name = "ptssQy")
    private Double ptssQy;
    @Column(nullable = true, name = "mitrQy")
    private Double mitrQy;
    @Column(nullable = true, name = "slwtEdblfibrQy")
    private Double slwtEdblfibrQy;
    @Column(nullable = true, name = "liacQy")
    private Double liacQy;
    @Column(nullable = true, name = "upperFeedClCode")
    private Integer upperFeedClCode;
    @Column(nullable = true, name = "fatQy")
    private Double fatQy;
    @Column(nullable = true, name = "lnacQy")
    private Double lnacQy;
    @Column(nullable = true, name = "vtmaQy")
    private Double vtmaQy;
    @Column(nullable = true, name = "trypQy")
    private Double trypQy;
    @Column(nullable = false, name = "feedNm")
    private String feedNm;
    @Column(nullable = true, name = "crbQy")
    private Double crbQy;
    @Column(nullable = false, name = "feedClCodeNm")
    private String feedClCodeNm;
    @Column(nullable = true, name = "phphQy")
    private Double phphQy;
    @Column(nullable = true, name = "protQy")
    private Double protQy;
    @Column(nullable = true, name = "originNm")
    private String originNm;
    @Column(nullable = true, name = "inslbltyEdblfibrQy")
    private Double inslbltyEdblfibrQy;

    @OneToMany(mappedBy = "feed")
    private List<BoardFeed> boardFeeds = new ArrayList<>();

    public Feed(Integer mtralPc, Double clciQy, Double naQy, Double dryMatter, Double ashsQy, Integer feedSeqNo, Double crfbQy, Double totEdblfibrQy, Integer feedClCode,
                Double ptssQy, Double mitrQy, Double slwtEdblfibrQy, Double liacQy, Integer upperFeedClCode, Double fatQy, Double lnacQy,
                Double vtmaQy, Double trypQy, String feedNm, Double crbQy, String feedClCodeNm, Double phphQy, Double protQym,
                String originNm, Double inslbltyEdblfibrQy)
    {
        this.mtralPc = mtralPc;
        this.clciQy = clciQy;
        this.naQy = naQy;
        this.dryMatter = dryMatter;
        this.ashsQy = ashsQy;
        this.feedSeqNo = feedSeqNo;
        this.crfbQy = crfbQy;
        this.totEdblfibrQy = totEdblfibrQy;
        this.feedClCode = feedClCode;
        this.ptssQy = ptssQy;
        this.mitrQy = mitrQy;
        this.slwtEdblfibrQy = slwtEdblfibrQy;
        this.liacQy = liacQy;
        this.upperFeedClCode = upperFeedClCode;
        this.fatQy = fatQy;
        this.lnacQy = lnacQy;
        this.vtmaQy = vtmaQy;
        this.trypQy = trypQy;
        this.feedNm = feedNm;
        this.crbQy = crbQy;
        this.feedClCodeNm = feedClCodeNm;
        this.phphQy = phphQy;
        this.protQy = protQym;
        this.originNm = originNm;
        this.inslbltyEdblfibrQy = inslbltyEdblfibrQy;
    }

}
