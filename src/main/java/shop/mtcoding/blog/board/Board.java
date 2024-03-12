package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Table(name = "board_tb")
@Entity
@Data
//@Getter setter는 굳이 만들지 않음
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;
    private String title;
    private String content;
    private String username;
    private String createdAt;

//    public void update(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
}