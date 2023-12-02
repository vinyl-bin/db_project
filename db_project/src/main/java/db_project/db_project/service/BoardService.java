package db_project.db_project.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import db_project.db_project.controller.BoardController;
import db_project.db_project.domain.*;
import db_project.db_project.repository.BoardFeedRepositoryImpl;
import db_project.db_project.repository.BoardRepository;
import db_project.db_project.repository.FeedRepository;
import db_project.db_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final BoardFeedRepositoryImpl boardFeedRepository;
    private final BoardFeedService boardFeedService;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


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

            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            //putObject() 메소드가 파일을 저장해주는 메소드
            amazonS3Client.putObject(bucket,fileName,file.getInputStream(),metadata);

            String fileUrl = amazonS3Client.getUrl(bucket, fileName).toString();


            //board 생성
            Board board = Board.createBoardWithFile(user, boardFeed, title, text, fileName, fileUrl);

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
    public Board updateWriteBoard(String title, String text, Long user_id, long feed_id, Long board_id,
                                  Long boardFeed_id, String fileName, String filePath, MultipartFile fileSave) throws Exception {

        Board board;

        //엔티티 조회
        User user = userRepository.findOne(user_id);
        Feed feed = feedRepository.findOne(feed_id);

        //boardfeed 생성
        BoardFeed boardFeed = BoardFeed.createBoardFeed(feed);
        boardFeed.setBoardFeed_id(boardFeed_id);


        //board 생성
        if (fileName.equals(null) && fileSave.isEmpty()) {
            board = Board.createBoard(user, boardFeed, title, text);
        }
        else if (fileSave.isEmpty()) {
            board = Board.createBoardWithFile(user, boardFeed, title, text, fileName, filePath);
        }
        else {

            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + "_" + fileSave.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileSave.getSize());
            metadata.setContentType(fileSave.getContentType());

            //putObject() 메소드가 파일을 저장해주는 메소드
            amazonS3Client.putObject(bucket,fileName,fileSave.getInputStream(),metadata);

            String newFilePath = amazonS3Client.getUrl(bucket, fileName).toString();

            board = Board.createBoardWithFile(user, boardFeed, title, text, newFileName, newFilePath);
        }

        board.setBoard_id(board_id);

        //board 저장
        boardFeedRepository.save(boardFeed);

        board.setBoardFeed(boardFeed);
        boardRepository.save(board);

        return board;
    }

    @Transactional
    public Board updateBoardCount(Board board) {
        board.setViewCount(board.getViewCount()+1);

        boardRepository.save(board);

        return board;
    }

    @Transactional
    public Board deleteBoard(Board board) {

        boardRepository.delete(board);

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
