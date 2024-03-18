package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 자동 컴퍼넌트 스캔
public interface UserJPARepository extends JpaRepository<User, Integer>{

    // 추상 메소드
    // @Query("select u from User u where u.username = :username and u.password = :password") // 간단한 쿼리는 여기 적되, 복잡한 쿼리문은 레파지토리 하나 더 만들기
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
