package shop.mtcoding.blog.Reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.reply.ReplyJPARepository;

/**
 * 1. one 관계는 join many 관계는 select 한 번 더 하기 ⇒ 담을 DTO가 필요함
 * 2. Many 관계를 양방향 매핑하기
 */
@DataJpaTest
public class ReplyJPARepositoryTest {

    @Autowired
    private ReplyJPARepository replyJPARepository;
}
