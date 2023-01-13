package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.serviece.BoardService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write") //board/write로 들어오면
    public String boardWriteForm(){
        return "boardwrite";    //resources/templates에 있는 boardwrite.html 페이지를 보여주겠다
    }
    @PostMapping("/board/writepro")
    public String boardWritePro(@NotNull Board board){
        System.out.println("id: "+board.getId());
        System.out.println("title: "+board.getTitle());
        System.out.println("content: "+board.getContent());
        boardService.write(board);
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(){
        return "boardlist";
    }
}
