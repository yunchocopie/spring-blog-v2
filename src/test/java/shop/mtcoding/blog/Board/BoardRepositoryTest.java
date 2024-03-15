package shop.mtcoding.blog.Board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test(){
        // given
        int id = 1;
        String title = "new title";
        String content = "new content";

        // when
        boardRepository.updateById(id, title, content);
        em.flush(); // 실제코드에는 트랜젝션 공유 될거기 때문에 안 해도 됨

        // then
        System.out.println("updateById_test : " + boardRepository.findById(id));
    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardRepository.deleteById(id);

        // then
        System.out.println("deleteById_test : " + boardRepository.findAll().size());
    }

    @Test
    public void _test(){
        List<Board> boardList = boardRepository.findAllV2();
        boardList.forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    public void randomquery_test(){
        //int[] ids = {1,2};
        List<Board> boardList = boardRepository.findAll();
        int[] userId = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();

        // select u from User u where u.id in (?,?);
        String q = "select u from User u where u.id in (";
        int count = 0;
        for (int i=0; i<userId.length; i++){
            if(i == userId.length-1){
                q = q + ":id" + ++count + ");";
            }else{
                q = q + ":id" + ++count +", ";
            }
        }
        System.out.println(q);

        count = 0; // 카운터 초기화
        for (int k = 0; k < userId.length; k++) {
            q = q.replace(":id" + ++count , userId[k]+"");
        }
        System.out.println(q);

        System.out.println(userId[0]);
        System.out.println(userId[1]);
        System.out.println(userId[2]);
    }

    @Test
    public void findAll_custom_inquery_test(){
        List<Board> boardList = boardRepository.findAll();

        int[] userId = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userId) {
            System.out.println(i);
        }
        // select * from user_tb where id in (3 ,2 ,1, 1);
        // select * from user_tb where id in (3 ,2 ,1);
    }

    @Test
    public void findAll_lazyloading_test(){
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });


        // then
    }

    @Test
    public void findAll_test(){
        // given

        // when
        boardRepository.findAll();

        // then
    }


    @Test
    public void findByIdJoinUser_test() {
        int id = 1;

        boardRepository.findByIdJoinUser(id);
    }


    @Test
    public void findById_test() {
        int id = 1;

        boardRepository.findById(id);
    }
}

