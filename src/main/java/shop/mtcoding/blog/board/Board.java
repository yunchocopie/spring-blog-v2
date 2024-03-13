package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@Table(name = "board_tb")
@Entity
@Data
@NoArgsConstructor // entity는 디폴트 생성자가 무조건 있어야 한다.
//@Getter setter는 굳이 만들지 않음#
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;
    private String title;
    private String content;

    // @JoinColumn(name = "user_id") // 직접 이름 지정
    @ManyToOne(fetch = FetchType.LAZY) // 연관관계로 보고 user_id로 만들어줌. user(변수명), _id(PK)
    private User user;

    @CreationTimestamp // pc -> db (날짜 주입)
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}

