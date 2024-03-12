package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@Table(name = "board_tb")
@Entity
@Data
@NoArgsConstructor // entity는 디폴트 생성자가 무조건 있어야 한다.
//@Getter setter는 굳이 만들지 않음
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;
    private String title;
    private String content;
    private String username;

    @CreationTimestamp // pc -> db (날짜 주입)
    private Timestamp createdAt;


//    public void update(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }


    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }
}

