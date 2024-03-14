package shop.mtcoding.blog.User;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;
import shop.mtcoding.blog.user.UserRequest;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired // DI
    private UserRepository userRepository;

    @Test
    public void findByUsername_test() {
        // given
        UserRequest.LoginDTO requestDTO = new UserRequest.LoginDTO();
        requestDTO.setUsername("ssar");
        requestDTO.setPassword("1234");

        // when
        User user = userRepository.findByUsernameAndPassword(requestDTO);

        // then
        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
    }
}

