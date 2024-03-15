package shop.mtcoding.blog.user;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserRepository userRepository;

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        userRepository.updateById(sessionUser.getId(), reqDTO.getPassword(), reqDTO.getEmail());
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    @PostMapping("join")
    public String join(UserRequest.JoinDTO reqDTO) {
        try {
            User sessionUser = userRepository.save(reqDTO.toEntity());
        } catch (NoResultException e) {
            throw new Exception400("동일한 유저네임이 존재합니다.");
        }

        // session.setAttribute("sessionUser", sessionUser); // 회원가입과 동시에 로그인
        return "redirect:/";
    }

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
        try {
            User sessionUser = userRepository.findByUsernameAndPassword(reqDTO);

            if (sessionUser == null) {
                return "redirect:/login-form";
            }

            session.setAttribute("sessionUser", sessionUser);

        } catch (Exception e) {
            throw new Exception401("유저네임 혹은 비밀번호가 틀렸어요.");
        }

        return "redirect:/";
    }

    @GetMapping("/user/update-form") // 세션에 아이디 값이 저장되어있기 때문에 {id} 안 함 + 보안
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

//        if (sessionUser == null) {
//            throw new Exception401("인증되지 않았어요. 로그인 해주세요.");
//        }

        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);

        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }
}
