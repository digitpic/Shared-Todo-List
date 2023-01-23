package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.serviece.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @ResponseBody
    @GetMapping("/getdata")
    public String[][] getData(){
        List<Board> getData = boardService.returnAllData();
        String[][] returnValues = new String[getData.size()][2];

        for(int i=0;i<getData.size();i++){
            returnValues[i][0] = getData.get(i).getContent();
            returnValues[i][1] = Integer.toString(getData.get(i).getComplete());
        }

        return returnValues;
    }

    @ResponseBody
    @PostMapping("/todo")
    public String todoSend(@RequestBody Board board) {
        System.out.println("입력된 데이터 : " + board);
        boardService.write(board);
        return "";
    }
    @ResponseBody
    @PostMapping("/modifydata")
    public String modifyData(@RequestBody Board board) {

        //POST 요청에 들어있는 content 값에 해당하는 노드를 DB에서 찾아오기
        Board modifiedBoard = boardService.find(board.getContent());
        System.out.println("수정된 데이터 : "+ modifiedBoard);

        //complete가 0이면 1로
        if(modifiedBoard.getComplete() == 0){
            modifiedBoard.setComplete(1);
        }

        //1이면 0으로 바꾸기
        else{
            modifiedBoard.setComplete(0);
        }

        //변경된 데이터를 DB에 저장하기 (update)
        boardService.write(modifiedBoard);
        return "";
    }

    @ResponseBody
    @PostMapping("/remove")
    public String removeData(@RequestBody Board board){
        Board removeBoard = boardService.find(board.getContent());
        System.out.println("삭제된 데이터 : "+removeBoard);
        boardService.remove(removeBoard);
        return "";
    }

}
