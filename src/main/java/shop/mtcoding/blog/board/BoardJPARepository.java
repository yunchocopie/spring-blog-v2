package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    // 이 쿼리로 다 해결 가능 (Lazy 로딩으로)
    @Query("select b from Board b join fetch b.user where b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id); // 한 건만 전달 받을 때는 @Param 안 써도 됨

    @Query("select b from Board b join fetch b.user u left join fetch b.replies r where b.id = :id")
    Optional<Board> findByIdJoinUserAnAndReplies(@Param("id") int id);
}

