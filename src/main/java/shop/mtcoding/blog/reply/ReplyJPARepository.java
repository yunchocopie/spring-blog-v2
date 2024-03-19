package shop.mtcoding.blog.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.blog.board.Board;

public interface ReplyJPARepository extends JpaRepository<Reply, Integer>  {
}
