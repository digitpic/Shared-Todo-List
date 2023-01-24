package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.serviece.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @ResponseBody
    @GetMapping("/getdata")
    public String[][] getData(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd hh:mm:ss) ");

        System.out.println(sdf.format(timestamp)+"Read : All Data in Database");
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd hh:mm:ss) ");
        System.out.println(sdf.format(timestamp)+"Create : "+ board);
        boardService.write(board);
        return "";
    }
    @ResponseBody
    @PostMapping("/modifydata")
    public String modifyData(@RequestBody Board board) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd hh:mm:ss) ");

        //POST 요청에 들어있는 content 값에 해당하는 노드를 DB에서 찾아오기
        Board modifiedBoard = boardService.find(board.getContent());
        System.out.println(sdf.format(timestamp)+"Update :");
        System.out.println("(target) " + modifiedBoard);
        System.out.print("(status) "+modifiedBoard.getComplete() + " → ");

        //complete가 0이면 1로
        if(modifiedBoard.getComplete() == 0){
            modifiedBoard.setComplete(1);
        }

        //1이면 0으로 바꾸기
        else{
            modifiedBoard.setComplete(0);
        }

        System.out.println(modifiedBoard.getComplete());

        //변경된 데이터를 DB에 저장하기 (update)
        boardService.write(modifiedBoard);
        return "";
    }

    @ResponseBody
    @PostMapping("/remove")
    public String removeData(@RequestBody Board board){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd hh:mm:ss) ");

        Board removeBoard = boardService.find(board.getContent());
        System.out.println(sdf.format(timestamp)+"Delete : "+ removeBoard);
        boardService.remove(removeBoard);
        return "";
    }

}
