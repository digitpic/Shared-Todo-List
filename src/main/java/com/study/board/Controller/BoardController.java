package com.study.board.Controller;

import com.study.board.entity.Board;
import com.study.board.serviece.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    /*
    *test String
    @GetMapping("/")
    @ResponseBody
    public String main(){
        return "Spring Boot Start!";
    }
     */
    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write") //board/write로 들어오면
    public String boardWriteForm(){
        return "boardwrite";    //resources/templates에 있는 boardwrite.html 페이지를 보여주겠다
    }
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){
        boardService.write(board);
        return "";
    }
}
