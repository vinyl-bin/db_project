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

            // MultipartFile 객체에서 파일 데이터를 byte[] 형태로 추출합니다.
            byte[] fileBytes = file.getBytes();

            // 파일 저장 부분
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();

//            File saveFile = new File(projectPath, fileName);
            String saveFilePath = projectPath + fileName;
            String filePath = "/files/" + fileName;

            // Files.write() 메서드를 사용하여 파일을 즉시 저장합니다.
            try {
                Files.write(Paths.get(saveFilePath), fileBytes);
            } catch (Exception e) {
                throw e;
            }

            // files 폴더 경로
            String folderPath = "/home/dabin/Desktop/SpringBoot/db_project/db_project/src/main/resources/static/files";

            // 변경된 파일 목록 업데이트
            List<String> updatedFileList = Files.list(Paths.get(folderPath))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());

            System.out.println("***************************************************************************************************************************************************************************");
            for(String a : updatedFileList) {
                System.out.println(a);
            }

//            file.transferTo(saveFile);  //예외처리 꼭 필요해서 throws 추가함.

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

            byte[] fileBytes = fileSave.getBytes();

            // 파일 저장 부분
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + "_" + fileSave.getOriginalFilename();

            String saveFilePath = projectPath + newFileName;
            String newFilePath = "/files/" + newFileName;

            try {
                Files.write(Paths.get(saveFilePath), fileBytes);
            } catch (Exception e) {
                throw e;
            }

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
