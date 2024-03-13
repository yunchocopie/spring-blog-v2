package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    public Board findById(int id) {
        Board board = em.find(Board.class, id); // 한 건을 받을 땐 find, 뭐로 받을건지, PK
        return board;
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);

        return query.getResultList();
    }


    @Transactional
    public Board save(Board board) {
        // 1. 비영속 객체
        em.persist(board);
        // 2. board -> 영속 객체
        return board;
    }


}
