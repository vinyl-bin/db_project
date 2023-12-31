package db_project.db_project.service;

import db_project.db_project.domain.Board;
import db_project.db_project.domain.BoardFeed;
import db_project.db_project.domain.SearchCondition;
import db_project.db_project.domain.User;
import db_project.db_project.repository.BoardFeedRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardFeedService {

    private final BoardFeedRepositoryImpl boardFeedRepository;

    @Transactional
    public void save(BoardFeed boardFeed) {
        boardFeedRepository.save(boardFeed);
    }

    @Transactional
    public BoardFeed deleteBoardFeed(BoardFeed boardFeed) {

        boardFeedRepository.delete(boardFeed);

        return boardFeed;
    }


}
