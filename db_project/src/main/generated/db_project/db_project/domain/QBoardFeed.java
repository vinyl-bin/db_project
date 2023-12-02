package db_project.db_project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardFeed is a Querydsl query type for BoardFeed
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardFeed extends EntityPathBase<BoardFeed> {

    private static final long serialVersionUID = -1874196710L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardFeed boardFeed = new QBoardFeed("boardFeed");

    public final QBoard board;

    public final NumberPath<Long> boardFeed_id = createNumber("boardFeed_id", Long.class);

    public final QFeed feed;

    public QBoardFeed(String variable) {
        this(BoardFeed.class, forVariable(variable), INITS);
    }

    public QBoardFeed(Path<? extends BoardFeed> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardFeed(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardFeed(PathMetadata metadata, PathInits inits) {
        this(BoardFeed.class, metadata, inits);
    }

    public QBoardFeed(Class<? extends BoardFeed> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.feed = inits.isInitialized("feed") ? new QFeed(forProperty("feed")) : null;
    }

}

