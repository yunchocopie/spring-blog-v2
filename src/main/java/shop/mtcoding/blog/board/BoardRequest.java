package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String username;
    }

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String username;

        public Board toEntity() { // entity로 바꾸는 메소드 (insert 하기위한 것 - insert 하려면 엔티티여야만 함)
            return new Board(title, content, username);
        }
    }
}
