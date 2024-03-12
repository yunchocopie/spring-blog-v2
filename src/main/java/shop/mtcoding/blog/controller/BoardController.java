package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @GetMapping( "/")
    public String index() {
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) { // Integer null 들어오면 확인 가능 (int는 0이라 뜸)
        return "board/detail";
    }
}
