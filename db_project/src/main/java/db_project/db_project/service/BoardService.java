package db_project.db_project.service;

import db_project.db_project.domain.Board;
import db_project.db_project.domain.BoardFeed;
import db_project.db_project.domain.Feed;
import db_project.db_project.domain.User;
import db_project.db_project.repository.BoardFeedRepositoryImpl;
import db_project.db_project.repository.BoardRepository;
import db_project.db_project.repository.FeedRepository;
import db_project.db_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final BoardFeedRepositoryImpl boardFeedRepository;
    private final BoardFeedService boardFeedService;


    /**
     * 게시판 작성
     */

    @Transactional
    public Board writeBoard(String title, String text, Long user_id, Long feed_id) {

        //엔티티 조회
        User user = userRepository.findOne(user_id);
        Feed feed = feedRepository.findOne(feed_id);

        //boardfeed 생성
        BoardFeed boardFeed = BoardFeed.createBoardFeed(feed);

        //board 생성
        Board board = Board.createBoard(user, boardFeed, title, text);

        //board 저장
        boardRepository.save(board);
        boardFeedRepository.save(boardFeed);

        return board;
    }

    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }
}
