package db_project.db_project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Feed {

    @Id @GeneratedValue
    @Column(name = "feed_id")
    private Long feed_id;

    private String mtralPc;
    private String clciQy;
    private String naQy;
    private String dryMatter;
    private String ashsQy;
    private String feedSeqNo;
    private String crfbQy;
    private String totEdblfibrQy;
    private String feedClCode;
    private String ptssQy;
    private String mitrQy;
    private String slwtEdblfibrQy;
    private String liacQy;
    private String upperFeedClCode;
    private String fatQy;
    private String lnacQy;
    private String vtmaQy;
    private String trypQy;
    private String feedNm;
    private String crbQy;
    private String feedClCodeNm;
    private String phphQy;
    private String protQy;
    private String originNm;
    private String inslbltyEdblfibrQy;

    @OneToOne(mappedBy = "choosedFeed", fetch = LAZY)
    private ChoosedFeed choosedFeed;

}
