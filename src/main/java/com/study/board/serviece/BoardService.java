package com.study.board.serviece;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void write(Board board){
        boardRepository.save(board);
    }

    public Board find(String content){
        return boardRepository.findByContent(content);
    }

    public void remove(Board board){
        boardRepository.delete(board);
    }
    public List<Board> returnAllData(){
        return boardRepository.findAll();
    }

}
