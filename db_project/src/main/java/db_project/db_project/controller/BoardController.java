package db_project.db_project.controller;

import db_project.db_project.domain.*;
import db_project.db_project.repository.BoardRepository;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;
    @Autowired
    private final FeedService feedService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final BoardFeedService boardFeedService;
//    private final BoardRepository boardRepository;

    @Autowired
    BoardRepository boardRepository;


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

//    @GetMapping("/board/list")
//    public String boardList(@ModelAttribute ("searchCondition") SearchCondition searchCondition , Model model) {
//        List<BoardFeed> boardFeeds = boardFeedService.findBoardFeedContent(searchCondition);
//        model.addAttribute("boardFeeds", boardFeeds);
//
//        return "board/boardSearchList";
//    }

    @GetMapping("/board/list")
    public String boardList(@ModelAttribute ("searchCondition") SearchCondition searchCondition , Model model) {
        List<BoardFeed> boardFeeds = boardFeedService.findBoardFeedContent(searchCondition);
        List<BoardFeed> boardFeeds1 = new ArrayList<>();
        for (int i = 0; i < boardFeeds.size(); i++) {
            BoardFeed boardFeed = boardFeeds.get(i);
            Long board_id = boardFeed.getBoard().getBoard_id();
            Long feed_id = boardFeed.getFeed().getFeed_id();


            //test
            Long test_id = 7L;
            Board test = boardRepository.findOne(test_id);


            Board board = boardService.findOne(board_id);
            Feed feed = feedService.findOne(feed_id);

            BoardFeed boardFeed1 = BoardFeed.createBoardFeed(board, feed);
            boardFeeds1.add(boardFeed1);
        }

        model.addAttribute("boardFeeds", boardFeeds1);

        return "board/boardSearchList";
    }

}
