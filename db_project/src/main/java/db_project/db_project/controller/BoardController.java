package db_project.db_project.controller;

import db_project.db_project.domain.*;
import db_project.db_project.repository.BoardFeedRepositoryImpl;
import db_project.db_project.repository.BoardRepository;
import db_project.db_project.repository.UserRepository;
import db_project.db_project.service.BoardFeedService;
import db_project.db_project.service.BoardService;
import db_project.db_project.service.FeedService;
import db_project.db_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final FeedService feedService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final BoardFeedService boardFeedService;

    @Autowired
    BoardFeedRepositoryImpl boardFeedRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/board/write")
    public String createForm(Model model) {
        List<Feed> feeds = feedService.findFeeds();

        model.addAttribute("form", new BoardForm());
        model.addAttribute("feeds", feeds);

        return "board/boardForm";
    }

    @PostMapping("/board/write")
    public String board(@Valid BoardForm form,
                        @RequestParam("feeds") long feed_id, HttpSession session) throws Exception {    //꼭 예외처리 해야 함. writeBoard가 예외처리를 꼭 해야하기 때문.

        if(session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        long userId = (long) session.getAttribute("userId");
        System.out.println("gd" + userId);

        boardService.writeBoard(form.getTitle(), form.getText(), userId, feed_id, form.getFileSave());

        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String boardList(@ModelAttribute ("searchCondition") SearchCondition searchCondition , Model model, HttpSession session) {
        List<BoardFeed> boardFeeds = boardService.findBoards(searchCondition);

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("currentUserId", (long) session.getAttribute("userId"));
        model.addAttribute("boardFeeds", boardFeeds);

        return "board/boardSearchList";
    }

    @GetMapping("/board/{boardId}/edit")
    public String updateBoardForm(@PathVariable("boardId") Long boardId, Model model) {

        //board 정보 불러오기
        Board board = boardService.findOne(boardId);

        BoardForm boardForm = new BoardForm();
        boardForm.setTitle(board.getTitle());
        boardForm.setText(board.getText());
        boardForm.setFilePath(board.getImagePath());
        boardForm.setFileName(board.getImageName());

        //feedId 찾기
        long feed_id = board.getBoardFeed().getFeed().getFeed_id();

        //feed 정보 불러오기
        Feed originFeed = feedService.findOne(feed_id);

        //feeds 카테고리 위해서 리스트로 불러오기
        List<Feed> feeds = feedService.findFeeds();

        model.addAttribute("boardForm", boardForm);
        model.addAttribute("originFeed", originFeed);
        model.addAttribute("feeds", feeds);
        return "board/updateBoardForm";
    }

    @PostMapping("board/{boardId}/edit")
    public String updateItem(@PathVariable String boardId,
                             @ModelAttribute("boardForm") BoardForm boardForm,
                             @RequestParam("feeds") long feed_id,
                             HttpSession session) throws Exception{

        //user_id 구하기
        Long boardIdL = Long.parseLong(boardId);
        Board gboard = boardService.findOne(boardIdL);
//        Long guser_id = gboard.getUser().getUser_id();


        //boardFeed_id 구하기
        Long boardFeed_id = gboard.getBoardFeed().getBoardFeed_id();

        if (session.getAttribute("userId") == null) {
            return  "redirect:/login";
        }

        long userId = (long) session.getAttribute("userId");

        try {
            boardService.updateWriteBoard(boardForm.getTitle(), boardForm.getText(), userId, feed_id, boardIdL, boardFeed_id, boardForm.getFileName(), boardForm.getFilePath(), boardForm.getFileSave());
        } catch (Exception e) {
            throw e;
        }

        return "redirect:/board/list";
    }


}
