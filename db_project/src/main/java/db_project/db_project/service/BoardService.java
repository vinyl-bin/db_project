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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

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
    public Board writeBoard(String title, String text, Long user_id, long feed_id, MultipartFile file) throws Exception {

        //엔티티 조회
        User user = userRepository.findOne(user_id);
        Feed feed = feedRepository.findOne(feed_id);

        //boardfeed 생성
        BoardFeed boardFeed = BoardFeed.createBoardFeed(feed);

        if (!file.isEmpty()) {
            // 파일 저장 부분
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);
            String filePath = "/home/dabin/Desktop/SpringBoot/db_project/db_project/src/main/resources/static/files/" + fileName;

            file.transferTo(saveFile);  //예외처리 꼭 필요해서 throws 추가함.

            //board 생성
            Board board = Board.createBoardWithFile(user, boardFeed, title, text, fileName, filePath);

            //board 저장
            boardFeedRepository.save(boardFeed);
            boardRepository.save(board);

            return board;
        }
        else {
            //board 생성
            Board board = Board.createBoard(user, boardFeed, title, text);

            //board 저장
            boardFeedRepository.save(boardFeed);
            boardRepository.save(board);

            return board;
        }
    }

    /**
     *  게시판 수정 후 재작성
     */

    @Transactional
    public Board updateWriteBoard(String title, String text, Long user_id, long feed_id, Long board_id, Long boardFeed_id, String fileName, String filePath) {

        Board board;

        //엔티티 조회
        User user = userRepository.findOne(user_id);
        Feed feed = feedRepository.findOne(feed_id);

        //boardfeed 생성
        BoardFeed boardFeed = BoardFeed.createBoardFeed(feed);
        boardFeed.setBoardFeed_id(boardFeed_id);


        //board 생성
        if (fileName.equals(null)) {
            board = Board.createBoard(user, boardFeed, title, text);
        }
        else {
            board = Board.createBoardWithFile(user, boardFeed, title, text, fileName, filePath);
        }

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
