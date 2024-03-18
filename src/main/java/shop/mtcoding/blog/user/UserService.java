package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IoC 등록
public class UserService {

    private final UserJPARepository userJPARepository;

    public User 회원수정(int id, UserRequest.UpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다.")); // 예외처리

        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());

        return user;
    } // 더티체킹

    public User 회원수정폼(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다.")); // 예외처리

        return user;
    }

    // 조회라서 transactional 안 붙여도 됨
    public User 로그인(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다.")); // ssar, 12345 넣으면 널값 -> 조회를 했는데 값이 널이면 throw 날리겠다 아니면 값을 받겠다

        return sessionUser; // session에 넣기 위해 return
    }

    @Transactional
    public void 회원가입(UserRequest.JoinDTO reqDTO) {
        // 1. 유효성 검사 - controller 책임

        // 2. 유저네임 중복 검사 - service (DB연결이 필요한 것들은 서비스가)
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());

        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저입니다.");
        }

        userJPARepository.save(reqDTO.toEntity());
    }
}
