package shop.mtcoding.blog.Board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardPersistRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired // DI (IoC애 있는 것을 DI 해줌)
    private BoardPersistRepository boardPersistRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목수정1";

        // when
        Board board = boardPersistRepository.findById(id);
        board.setTitle(title);
        em.flush();
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteById(id);

        // then

    }

    @Test
    public void deleteByIdV2_test() {
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteByIdV2(id);

        // then
        em.flush();

    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardPersistRepository.findById(id);
        // em.clear(); // PC를 지우는 것 -> 쿼리가 날라감 => select 두번 됨
        boardPersistRepository.findById(id);
        //System.out.println("findById_test" + board);

        // then
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");


        // org.assertj.core.api

    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");

    }

    @Test
    public void save_test() {
        // given
        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardPersistRepository.save(board);
        System.out.println("save_test" + board);

        // then


    }
}
