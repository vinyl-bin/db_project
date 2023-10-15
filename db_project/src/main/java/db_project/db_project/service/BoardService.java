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

    @Transactional
    public Long writeBoard(String title, String text, Long user_id, Long feed_id1) {


        User user = userRepository.findOne(user_id);   //user를 board에 추가해서 board 만듦, board와 feed를 boardfeed에 넣어줌
//        userRepository.save(user);
        Board board = Board.createBoard(title, text, user);
//        boardRepository.save(board);

        Feed feed1 = feedRepository.findOne(feed_id1);
//        feedRepository.save(feed1);

        BoardFeed boardFeed1 = BoardFeed.createBoardFeed(board, feed1);
        boardFeedRepository.save(boardFeed1);


        return boardFeed1.getBoardFeed_id();


    }
}
