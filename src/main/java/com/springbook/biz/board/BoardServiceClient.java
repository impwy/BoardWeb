package com.springbook.biz.board;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class BoardServiceClient {

    public static void main(String[] args) {
        AbstractApplicationContext container = 
                new GenericXmlApplicationContext("applicationContext.xml");

        BoardService boardService = (BoardService) container.getBean("boardService");
        
        BoardVO vo = new BoardVO();
        vo.setTitle("임시제목");
        vo.setWriter("홍길동");
        vo.setContent("임시내용....");
        boardService.insertBoard(vo);

        List<BoardVO> boardVOList = boardService.getBoardList(vo);
        for (BoardVO board : boardVOList) {
            System.out.println("----->" + board.toString());
        }

        container.close();
    }
}
