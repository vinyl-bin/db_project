package db_project.db_project.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import db_project.db_project.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import java.util.*;
import java.util.function.Supplier;

import static db_project.db_project.domain.QBoardFeed.boardFeed;

@Repository
public class BoardFeedRepositoryImpl implements BoardFeedCustomRepository{

    public final JPAQueryFactory queryFactory;  // JPAQueryFactory 빈 주입

    public BoardFeedRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<BoardFeed> findBySearchOption(SearchCondition condition) {
        return queryFactory
                .selectFrom(boardFeed)
                .where(isSearchable(condition.getType(), condition.getContent()))
                .fetch();
    }

    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    BooleanBuilder isSearchable(SearchType searchType, String content) {
        if (searchType == searchType.TIT) {
            return titleCt(content);
        }
        else {
            return feedCt(content);
        }
    }

    BooleanBuilder titleCt(String content) {
        return nullSafeBuilder(() -> boardFeed.board.title.contains(content));
    }

    BooleanBuilder feedCt(String content) {
        return nullSafeBuilder(() -> boardFeed.feed.feedClCode.contains(content));
    }
}
