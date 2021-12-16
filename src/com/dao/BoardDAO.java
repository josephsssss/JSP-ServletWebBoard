package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.vo.BoardVO;
import com.vo.PageBlock;

public interface BoardDAO { //인터페이스는 상수와 추상메소드만 들어가 있어야 함
	String list_all = "SELECT seq, title, writer, writedate, readed FROM BB  ORDER BY seq DESC";
	String insert_bb = "INSERT INTO BB (seq, writer, pwd, email, title, tag, content, filename, filerealname) VALUES (SEQ_BB.NEXTVAL, ?, ?, ?, ? , ? ,? ,?, ?)";
	String increase_readed = "UPDATE BB SET readed = readed+1 WHERE seq=?";
	String board_view = "SELECT seq, writer, title, content, readed, writedate, email, filename, filerealname FROM BB WHERE seq = ?";
	String select_pw = "SELECT pwd FROM BB WHERE seq = ?";
	String delete_writing = "DELETE FROM BB WHERE seq = ? ";
	String select_all = "SELECT seq, writer, email,  title, content, readed, writedate, tag, pwd FROM BB WHERE seq = ?";
	String update_bb = "UPDATE BB SET WRITER = ?, PWD=?, EMAIL=?, TITLE=?, TAG=?, CONTENT = ? WHERE SEQ = ?";
	String view_bb = "SELECT seq, writer, title, content, readed, writedate , email FROM BB WHERE seq = ?";
	String search_list = " SELECT * FROM ( SELECT rownum no,  t.* FROM ( SELECT seq, title, writer, writedate, readed , case when ( sysdate - writedate)  between 0 and 1/24 then 'true' else 'false' end newmark FROM BB  ";
	String search_1 = " WHERE regexp_like( title , ? , 'i' )  ";
	String search_2 = " WHERE regexp_like( content , ? , 'i' )  ";
	String search_3 = " WHERE regexp_like( writer , ? , 'i' )  ";
	String search_4 =  "WHERE regexp_like( title , ? , 'i' ) OR  regexp_like( content , ? , 'i' )   ";
	String search_order = " ORDER BY seq DESC ) t ) b WHERE b.no BETWEEN  ? AND ?  ";
	String page_count = "SELECT count(*) totalRecords ,  CEIL(count(*)/?) totalPages" + " FROM BB ";
	
	
	//1. 모든 게시글 목록 반환하는 메소드 선언
	ArrayList<BoardVO> select() throws SQLException;
	//1-1. 선택적 게시글 반환
	ArrayList<BoardVO> select(int currentPage, int numberPerPage, int searchCondition, String searchWord) throws SQLException; 
	//매개변수 : 보고자 하는 페이지, 한 페이지에 뿌릴 개수
	//1-2. 선택적 페이지 반환
	PageBlock select1(int currentPage, int numberPerPage, int searchCondition, String searchWord) throws SQLException;

	//2. 게시글 쓰기 메소드
	int insert(String writer, String pw, String email, String title, int tag, String content, String fileName, String fileRealName) throws SQLException;
	//3. 조회수 증가 메소드 
	void increaseReaded(int seq)throws SQLException;
	//4. 해당 게시글 상세 보기 메소드
	BoardVO view(int seq) throws SQLException;
	//5. 게시글 삭제 메소드
	int delete(int seq, String pwd) throws SQLException;
	//6. 게시글 업데이트 창 보는 메소드 
	BoardVO edit(int seq) throws SQLException;
	//7. 게시글 업데이트 보는 메소드
	int update(String writer, String pwd, String email, String title, int tag, String content, int seq) throws SQLException;
	
}