package db_project.db_project.controller;

import db_project.db_project.domain.User;
import db_project.db_project.dto.LoginForm;
import db_project.db_project.dto.UserForm;
import db_project.db_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/user/new") //회원가입
    public String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/createUserForm";
    }

    @PostMapping("/user/new")
    public String create(@Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "user/createUserForm";
        }

        User user = new User();
        user.setName(form.getName());
        String secret = passwordEncoder.encode(form.getPassword());
        user.setPassword(secret);
        user.setEmail(form.getEmail());

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/users")
    public String UserList(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("loginForm") LoginForm form) {

        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpSession session) {
        try{
            if (bindingResult.hasErrors()) {
                return "user/login";
            }

            User loginUser = userService.login((String) form.getName(), form.getPassword());

            if (loginUser == null) {
                bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");

                String warningMessage = "아이디 또는 비밀번호가 맞지 않습니다.";

                String encodedMessage = URLEncoder.encode(warningMessage, StandardCharsets.UTF_8);
                return "redirect:/login?loginFailMessage=" + encodedMessage;
            }

            session.setAttribute("userId", loginUser.getUser_id());
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

}
