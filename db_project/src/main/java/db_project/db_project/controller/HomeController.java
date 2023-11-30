package db_project.db_project.controller;

import db_project.db_project.domain.BoardFeed;
import db_project.db_project.domain.SearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        log.info("home controller");

        Long userSession;
        try {
            userSession = (long) session.getAttribute("userId");
        } catch (Exception e) {
            userSession = null;
        }

        log.info(userSession + "!!!!!!");

        model.addAttribute("currentUserId", userSession);

        return "home";
    }

}
