package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("boardDAO")
public class BoardDAO implements com.springbook.biz.board.BoardService {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private final String BOARD_INSERT = "insert into board (seq, title, writer, content)" +
            "values((select nvl(max(seq),0)+1 from board), ?, ?, ?)";
    private final String BOARD_UPDATE = "update board set title=?,content=? where seq=?";
    private final String BOARD_DELETE = "delete board where seq=?";
    private final String BOARD_GET = "select * from board where seq=?";
    private final String BOARD_LIST = "select * from board order by seq desc";

    @Override
    public void insertBoard(BoardVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt,conn);
        }
    }

    @Override
    public void updateBoard(BoardVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt,conn);
        }
    }

    @Override
    public void deleteBoard(BoardVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt,conn);
        }
    }

    @Override
    public BoardVO getBoard(BoardVO vo) {
        BoardVO board = null;
        try {
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, vo.getSeq());
            rs = stmt.executeQuery();
            if (rs.next()) {
                board = new BoardVO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return board;
    }

    @Override
    public List<BoardVO> getBoardList(BoardVO vo) {
        List<BoardVO> boardList = new ArrayList<BoardVO>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
                boardList.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return boardList;
    }

}
