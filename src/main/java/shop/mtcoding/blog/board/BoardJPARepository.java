package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b join fetch b.user where b.id = :id")
    Board findByIdJoinUser(@Param("id") int id); // 한 건만 전달 받을 때는 @Param 안 써도 됨

}
