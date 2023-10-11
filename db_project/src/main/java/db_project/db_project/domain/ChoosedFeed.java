package db_project.db_project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class ChoosedFeed {

    @Id @GeneratedValue
    @Column(name = "choosedFeed_id")
    private Long choosedFeed_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;
}
