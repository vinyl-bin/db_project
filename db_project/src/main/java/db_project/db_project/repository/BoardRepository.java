package db_project.db_project.repository;

import db_project.db_project.domain.Board;
import db_project.db_project.domain.BoardFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        if (board.getBoard_id() == null) {
            em.persist(board);
        }
        else {
            em.merge(board);
        };
    }

    public Board findOne(Long board_id) {
        return em.find(Board.class, board_id);
    }


    // Board 목록 보여주는 기는은 BoardFeedRepositoryImpl에 있음(검색기능도 추가함)

}


