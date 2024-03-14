package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findAllV2() { // findAll은 쿼리를 작성해야함
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList(); // lazy 로딩이라 user 객체는 없음 -> 동적 객체를 만들어야 함

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // user객체 id 불러오기
        int[] userId = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();

        // IN쿼리문 생성
        String q2 = "select u from User u where u.id in ("; // 보드가 가지고 있는 아이디 개수만큼 물음표 / 스트림 필터 si? 아이디 같은지 비교하고 매칭시키기

        // userId 수만 큼 :id 추가
        int count = 0;
        for (int i=0; i<userId.length; i++){
            if(i == userId.length-1){
                q2 = q2 + ":id" + ++count + ")";
            }else{
                q2 = q2 + ":id" + ++count +", ";
            }
        }

        count = 0; // 카운터 초기화
        // :id에 userId 매칭 시키기
        for (int k = 0; k < userId.length; k++) {
            q2 = q2.replace(":id" + ++count , userId[k]+"");
        }

        List<User> userList = em.createQuery(q2, User.class).getResultList();

        for (Board board : boardList) {
            for (User user : userList) {
                if (board.getUser().getId() == user.getId()) {
                    board.setUser(user);
                }
            }
        }

        /* 선생님 코드
        public List<Board> findAllV2() {
            Query q1 = em.createQuery("select b from Board b order by b.id desc", Board.class);
            List<Board> boardList = q1.getResultList();

            Set<Integer> userIds = new HashSet<>();
            for (Board board : boardList){
                userIds.add(board.getUser().getId());
            }

            Query q2 = em.createQuery("select u from User u where u.id in :userIds", User.class);
            q2.setParameter("userIds", userIds);
            List<User> userList = q2.getResultList();

            for (Board board : boardList){
                for (User user : userList) {
                    if (user.getId() == board.getUser().getId()){
                        board.setUser(user);
                    }
                }
            }

            return boardList;
        }
        */

        return boardList; // user가 채워져있어야 함
    }

    public List<Board> findAll() { // findAll은 쿼리를 작성해야함
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);

        return query.getResultList();
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);

        return (Board) query.getSingleResult();
    }

    public Board findById(int id) {
        // id, title, content, user_id
        Board board = em.find(Board.class, id);

        return board;
    }
}
