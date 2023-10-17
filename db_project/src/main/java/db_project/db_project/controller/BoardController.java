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

import javax.persistence.EntityManager;
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
//    private final BoardRepository boardRepository;

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
                        @RequestParam("feeds") Long feed_id) {

        User user = new User();
        user.setName(form.getUserName());
        user.setPassword(form.getPassword());

        userService.join(user);

        boardService.writeBoard(form.getTitle(), form.getText(), user.getUser_id(), feed_id);

        return "redirect:/";
    }

    @GetMapping("/board/list")
    public String boardList(@ModelAttribute ("searchCondition") SearchCondition searchCondition , Model model) {
        List<BoardFeed> boardFeeds = boardService.findBoards(searchCondition);
        model.addAttribute("boardFeeds", boardFeeds);

        return "board/boardSearchList";
    }

    @GetMapping("/board/{boardId}/edit")
    public String updateBoardForm(@PathVariable("boardId") Long boardId, Model model) {

        //board 정보 불러오기
        Board board = boardService.findOne(boardId);

        BoardForm boardForm = new BoardForm();
        boardForm.setTitle(board.getTitle());
        boardForm.setUserName(board.getUser().getName());
        boardForm.setPassword(board.getUser().getPassword());
        boardForm.setText(board.getText());

        //feedId 찾기
        Long feed_id = board.getBoardFeed().getFeed().getFeed_id();

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
                             @RequestParam("feeds") Long feed_id) {

        //user_id 구하기
        Long boardIdL = Long.parseLong(boardId);
        Board gboard = boardService.findOne(boardIdL);
        Long guser_id = gboard.getUser().getUser_id();


        //boardFeed_id 구하기
        Long boardFeed_id = gboard.getBoardFeed().getBoardFeed_id();



        User user = new User();
        user.setUser_id(guser_id);
        user.setName(boardForm.getUserName());
        user.setPassword(boardForm.getPassword());

        userService.createUser(user);


        boardService.updateWriteBoard(boardForm.getTitle(), boardForm.getText(), user.getUser_id(), feed_id, boardIdL, boardFeed_id);

        return "redirect:/board/list";
    }


}
