package db_project.db_project.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import db_project.db_project.domain.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;

import java.util.*;
import java.util.function.Supplier;

import static db_project.db_project.domain.QBoardFeed.boardFeed;

@Repository
@RequiredArgsConstructor
@Transactional
public class BoardFeedRepositoryImpl implements BoardFeedCustomRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public List<BoardFeed> findBySearchOption(SearchCondition condition) {

//        if (condition.getType() == null) {
//            return findAll();
//        }

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
        if (searchType == null) {
            return null;


        }
        else if (searchType == searchType.TITLE) {
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
        return nullSafeBuilder(() -> boardFeed.feed.feedClCodeNm.contains(content));
    }

    public void save(BoardFeed boardFeed) {
        if (boardFeed.getBoardFeed_id() == null) {
            em.persist(boardFeed);
        }
        else {
            em.merge(boardFeed);
        }
    }

    public void delete(BoardFeed boardFeed) {
        em.remove(boardFeed);
    }

    public BoardFeed findOne(Long boardFeed_id) {
        return em.find(BoardFeed.class, boardFeed_id);
    }

    public List<BoardFeed> findAll() {
        return em.createQuery("select bf from BoardFeed bf", BoardFeed.class)
                .getResultList();
    }
}
