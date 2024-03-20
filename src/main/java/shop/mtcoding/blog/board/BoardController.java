package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final HttpSession session;

    // TODO : 글 목록 조회 API 필요 -> @GetMapping("/")

    // TODO : 글 상세보기 API 필요 -> @GetMapping("api/boards/{id}/detail")

    // TODO : 글 조회 API 필요 -> @GetMapping("api/boards/{id}")

    @PutMapping("/api/boards/{id}")
    public String update(@PathVariable Integer id, BoardRequest.UpadateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정(id, sessionUser.getId(), reqDTO);

        return "redirect:/board/" + id;
    }

    @DeleteMapping("api/boards/{id}")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id,sessionUser.getId());
        return "redirect:/";
    }

    @PostMapping("/api/boards")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(reqDTO, sessionUser);

        return "redirect:/";
    }


}