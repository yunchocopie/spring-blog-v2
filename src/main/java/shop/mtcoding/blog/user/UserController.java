package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserRepository userRepository;

    @GetMapping("/join-form")
    public String joinForm() {

        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {

        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userRepository.findByUsernameAndPassword(reqDTO);

        if (sessionUser == null) {
            return "redirect:/login-form";
        }

        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {

        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";
    }
}
