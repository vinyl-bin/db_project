package db_project.db_project.service;

import db_project.db_project.domain.Board;
import db_project.db_project.domain.BoardFeed;
import db_project.db_project.domain.Feed;
import db_project.db_project.domain.User;
import db_project.db_project.repository.BoardRepository;
import db_project.db_project.repository.FeedRepository;
import db_project.db_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public Long writeBoard(String title, String text, Long user_id, Long feed_id1, Long feed_id2, Long feed_id3, Long feed_id4, Long feed_id5) {


        User user = userRepository.findOne(user_id);

        Feed feed1 = feedRepository.findOne(feed_id1);
        BoardFeed boardFeed1 = BoardFeed.createBoardFeed(feed1);

        Feed feed2 = feedRepository.findOne(feed_id2);
        BoardFeed boardFeed2 = BoardFeed.createBoardFeed(feed2);

        Feed feed3 = feedRepository.findOne(feed_id3);
        BoardFeed boardFeed3 = BoardFeed.createBoardFeed(feed3);

        Feed feed4 = feedRepository.findOne(feed_id4);
        BoardFeed boardFeed4 = BoardFeed.createBoardFeed(feed4);

        Feed feed5 = feedRepository.findOne(feed_id5);
        BoardFeed boardFeed5 = BoardFeed.createBoardFeed(feed5);


        Board board = Board.createBoard(title, text, user, boardFeed1, boardFeed2, boardFeed3, boardFeed4, boardFeed5);

        // board 저장
        boardRepository.save(board);

        return board.getBoard_id();


    }
}
