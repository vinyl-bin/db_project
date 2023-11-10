package db_project.db_project.service;

import db_project.db_project.controller.BoardController;
import db_project.db_project.domain.*;
import db_project.db_project.repository.BoardFeedRepositoryImpl;
import db_project.db_project.repository.BoardRepository;
import db_project.db_project.repository.FeedRepository;
import db_project.db_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public Board writeBoard(String title, String text, Long user_id, long feed_id) {

        //엔티티 조회
        User user = userRepository.findOne(user_id);
        Feed feed = feedRepository.findOne(feed_id);

        //boardfeed 생성
        BoardFeed boardFeed = BoardFeed.createBoardFeed(feed);


        //board 생성
        Board board = Board.createBoard(user, boardFeed, title, text);

        //board 저장
        boardFeedRepository.save(boardFeed);
        boardRepository.save(board);

        return board;
    }

    /**
     *  게시판 수정 후 재작성
     */

    @Transactional
    public Board updateWriteBoard(String title, String text, Long user_id, long feed_id, Long board_id, Long boardFeed_id) {

        //엔티티 조회
        User user = userRepository.findOne(user_id);
        Feed feed = feedRepository.findOne(feed_id);

        //boardfeed 생성
        BoardFeed boardFeed = BoardFeed.createBoardFeed(feed);
        boardFeed.setBoardFeed_id(boardFeed_id);


        //board 생성
        Board board = Board.createBoard(user, boardFeed, title, text);
        board.setBoard_id(board_id);

        //board 저장
        boardFeedRepository.save(boardFeed);

        board.setBoardFeed(boardFeed);
        boardRepository.save(board);

        return board;
    }

    /**
     * 검색
     */
    public List<BoardFeed> findBoards(SearchCondition searchCondition) {
        return boardFeedRepository.findBySearchOption(searchCondition);
    }

    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }
}
