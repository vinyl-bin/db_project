package db_project.db_project.controller;

import db_project.db_project.domain.Feed;
import db_project.db_project.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/feeds")
    public String feedList(Model model) {
        List<Feed> feeds = feedService.findFeeds();
        model.addAttribute("feeds", feeds);
        return "feed/feedList";
    }
}
