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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    private final EntityManager em;


    @GetMapping("/board/write")
    public String createForm(Model model) {
        List<Feed> feeds = feedService.findFeeds();

        model.addAttribute("from", new BoardForm());
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


}
