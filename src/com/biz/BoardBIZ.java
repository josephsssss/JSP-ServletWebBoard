package com.biz;
import com.vo.BoardVO;
import com.vo.PageBlock;

import static common.JDBCTemplate.*;

import com.dao.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
public class BoardBIZ {
	
	public int getDelete_Writing(int seq, String pwd) {
		Connection conn = getConnection();
		int res = new BoardDAOImpl(conn).delete(seq, pwd);
		close(conn);
		return res;
	}
	
	public BoardVO getView(int seq) {
		Connection conn = getConnection();
		BoardVO res = new BoardDAOImpl(conn).view(seq);
		close(conn);
		return res;
	}
	
	public int getWriting(String writer, String pw, String email, String title, int tag, String content, String fileName, String fileRealName) {
		Connection conn = getConnection();
		int res = new BoardDAOImpl(conn).insert(writer, pw, email, title, tag, content, fileName, fileRealName);
		close(conn);
		return res;
	}
	
	public BoardVO getEditing(int seq) {
		Connection conn = getConnection();
		BoardVO res = new BoardDAOImpl(conn).edit(seq);
		close(conn);
		return res;
	}
	
	public int getUpdating(String writer, String pw, String email, String title, int tag, String content, int seq) {
		Connection conn = getConnection();
		int res = new BoardDAOImpl(conn).update(writer, pw, email, title, tag, content, seq);
		close(conn);
		return res;
	}
	
	public void getIncreaseReaded(int seq) {
		Connection conn = getConnection();
		new BoardDAOImpl(conn).increaseReaded(seq);
		close(conn);
	}
	
	public ArrayList<BoardVO> getList(int currentPage, int numberPerPage, int searchCondition, String searchWord) {
		Connection conn = getConnection();
		ArrayList<BoardVO> res = new BoardDAOImpl(conn).select(currentPage, numberPerPage, searchCondition, searchWord);
		close(conn);
		return res;
	}
	public PageBlock getPageBlock(int currentPage, int numberPerPage, int searchCondition, String searchWord) {
		Connection conn = getConnection();
		PageBlock res = new BoardDAOImpl(conn).select1(currentPage, numberPerPage, searchCondition, searchWord);
		close(conn);
		return res;
	}
}
