package shop.mtcoding.blog.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserJPARepository;

import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void save_test(){
        // given
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();

        // when
        userJPARepository.save(user);

        // then
    }

    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        // userJPARepository.findById(id);

        // 옵셔널 처리
        Optional<User> userOP = userJPARepository.findById(5);

        if (userOP.isPresent()) {
            User user = userOP.get();
            System.out.println("findById_test : " + user.getUsername());
        }

        // then
    }

    @Test
    public void findAll_test() throws JsonProcessingException {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 2, sort);

        // when
        Page<User> userPG = userJPARepository.findAll(pageable);

        // then
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userPG); // 자바 클래스를 json 언어로 바꾸는 것
        System.out.println(json);
    }

    @Test
    public void findByUsernameAndPassword_test(){
        // given
        String username = "ssar";
        String password = "1234";

        // when
        userJPARepository.findByUsernameAndPassword(username, password);

        // then
    }
}
