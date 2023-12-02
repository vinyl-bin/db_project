package db_project.db_project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeed is a Querydsl query type for Feed
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeed extends EntityPathBase<Feed> {

    private static final long serialVersionUID = -1074482584L;

    public static final QFeed feed = new QFeed("feed");

    public final NumberPath<Double> ashsQy = createNumber("ashsQy", Double.class);

    public final ListPath<BoardFeed, QBoardFeed> boardFeeds = this.<BoardFeed, QBoardFeed>createList("boardFeeds", BoardFeed.class, QBoardFeed.class, PathInits.DIRECT2);

    public final NumberPath<Double> clciQy = createNumber("clciQy", Double.class);

    public final NumberPath<Double> crbQy = createNumber("crbQy", Double.class);

    public final NumberPath<Double> crfbQy = createNumber("crfbQy", Double.class);

    public final NumberPath<Double> dryMatter = createNumber("dryMatter", Double.class);

    public final NumberPath<Double> fatQy = createNumber("fatQy", Double.class);

    public final NumberPath<Long> feed_id = createNumber("feed_id", Long.class);

    public final NumberPath<Integer> feedClCode = createNumber("feedClCode", Integer.class);

    public final StringPath feedClCodeNm = createString("feedClCodeNm");

    public final StringPath feedNm = createString("feedNm");

    public final NumberPath<Integer> feedSeqNo = createNumber("feedSeqNo", Integer.class);

    public final NumberPath<Double> inslbltyEdblfibrQy = createNumber("inslbltyEdblfibrQy", Double.class);

    public final NumberPath<Double> liacQy = createNumber("liacQy", Double.class);

    public final NumberPath<Double> lnacQy = createNumber("lnacQy", Double.class);

    public final NumberPath<Double> mitrQy = createNumber("mitrQy", Double.class);

    public final NumberPath<Integer> mtralPc = createNumber("mtralPc", Integer.class);

    public final NumberPath<Double> naQy = createNumber("naQy", Double.class);

    public final StringPath originNm = createString("originNm");

    public final NumberPath<Double> phphQy = createNumber("phphQy", Double.class);

    public final NumberPath<Double> protQy = createNumber("protQy", Double.class);

    public final NumberPath<Double> ptssQy = createNumber("ptssQy", Double.class);

    public final NumberPath<Double> slwtEdblfibrQy = createNumber("slwtEdblfibrQy", Double.class);

    public final NumberPath<Double> totEdblfibrQy = createNumber("totEdblfibrQy", Double.class);

    public final NumberPath<Double> trypQy = createNumber("trypQy", Double.class);

    public final NumberPath<Integer> upperFeedClCode = createNumber("upperFeedClCode", Integer.class);

    public final NumberPath<Double> vtmaQy = createNumber("vtmaQy", Double.class);

    public QFeed(String variable) {
        super(Feed.class, forVariable(variable));
    }

    public QFeed(Path<? extends Feed> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeed(PathMetadata metadata) {
        super(Feed.class, metadata);
    }

}

