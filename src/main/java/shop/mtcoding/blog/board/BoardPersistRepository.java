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

    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO reqDTO) {
        Board board = findById(id); // 조회된 객체는 영속화되 객체임
        board.update(reqDTO);
    } // 더티체킹

    @Transactional
    public void deleteById(int id) { // delete 이 코드 사용
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteByIdV2(int id) { // 추천 X 코드
        Board board = findById(id);
        em.remove(board); // 트랜젝션 종료시 쿼리 전송
        // 비지니스 로직을 여기 짜는 걸 추천하지 않음 → 다양한 예외가 있는데 재사용할 수 있는 곳이기때문
    }

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
