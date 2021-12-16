package com.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import com.vo.BoardVO;
import com.vo.PageBlock;

public class BoardDAOImpl implements BoardDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public BoardDAOImpl() {
	}

	// 1 생성자에 의해 의존성주입
	public BoardDAOImpl(Connection con) { // 커넥션 객체를 외부에서 주입받아 오기
		this.con = con; // Connection 객체를 의존성 주입 (DI)
	}

	@Override
	public ArrayList<BoardVO> select() {
		try {
			this.pstmt = this.con.prepareStatement(list_all);
			this.rs = this.pstmt.executeQuery();
			ArrayList<BoardVO> list = null;

			int seq;
			String title;
			String writer;
			Date writedate; // java.sql.Date
			int readed;
			BoardVO vo = null;

			if (rs.next()) {
				list = new ArrayList<BoardVO>();
				do {
					// 글번호 글제목 작성자 작성일 조회수
					seq = rs.getInt("seq");
					title = rs.getString("title");
					writer = rs.getString("writer");
					writedate = rs.getDate("writedate");
					readed = rs.getInt("readed");

					vo = new BoardVO();

					vo.setSeq(seq);
					vo.setTitle(title);
					vo.setWriter(writer);
					vo.setWritedate(writedate);
					vo.setReaded(readed);

					list.add(vo);
				} while (rs.next());
			}

			this.rs.close();
			this.pstmt.close();
			return list; // 레코드가 없으면 0을 돌림
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollBack(this.con);
		}
		return null;
	}
	
	@Override
	public ArrayList<BoardVO> select(int currentPage, int numberPerPage, int searchCondition, String searchWord) {
		// List?currentPage=1&numberPerPage=10
		String sql = search_list;
		switch (searchCondition) {
		case 1: // 제목
			sql += search_1;
			break;
		case 2: // 내용
			sql += search_2;
			break;
		case 3: // 작성자
			sql += search_3;
			break;
		case 4: // 제목 + 내용
			sql += search_4;
			break;
		} // switch
		sql += search_order;

		
		ArrayList<BoardVO> list = null;

		
		int start = (currentPage - 1) * numberPerPage + 1;
		int end = currentPage * numberPerPage;
		
		
		try {

			pstmt = con.prepareStatement(sql);
			// BETWEEN ? AND ?
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			// 검색 조건 1/2/3/4
			if (searchCondition == 4) {
				// 검색조건 4 Where ? or ? Between ? And ?
				pstmt.setString(1, searchWord);
				pstmt.setString(2, searchWord);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			} else {
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}

			rs = pstmt.executeQuery();

			int seq;
			String title;
			String writer;
			Date writedate; // java.sql.Date 타입.
			int readed;
			boolean newmark;

			BoardVO vo = null;

			if (rs.next()) {
				list = new ArrayList<BoardVO>();
				do {
					// 글번호 글제목 글쓴이 작성일 조회수
					seq = rs.getInt("seq");

					title = rs.getString("title");
					if (searchCondition == 1 && !searchWord.equals("*")) {
						title = title.replace(searchWord,
								String.format("<span class=\"searchWord\">%s</span>", searchWord));
					}

					writer = rs.getString("writer");
					writedate = rs.getDate("writedate");
					readed = rs.getInt("readed");
					newmark = Boolean.parseBoolean(rs.getString("newmark"));

					vo = new BoardVO();
					vo.setSeq(seq);
					vo.setTitle(title);
					vo.setWriter(writer);
					vo.setWritedate(writedate);
					vo.setReaded(readed);
					vo.setNewmark(newmark); //

					list.add(vo);
				} while (rs.next());
			} // if
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try 현재 페이지 게시글 ArrayList 채워넣는 코딩

		return list;
	}

	@Override
	public PageBlock select1(int currentPage, int numberPerPage, int searchCondition, String searchWord){
		int totalPages = 0;
		String sql = page_count;

		switch (searchCondition) {
		case 1: // 제목
			sql += search_1;
			break;
		case 2: // 내용
			sql += search_2;
			break;
		case 3: // 작성자
			sql += search_3;
			break;
		case 4: // 제목 + 내용
			sql += search_4;
			break;
		} // switch

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numberPerPage);

			pstmt.setString(2, searchWord);
			if (searchCondition == 4)
				pstmt.setString(3, searchWord);

			rs = pstmt.executeQuery();

			if (rs.next())
				totalPages = rs.getInt("totalPages");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try 총페이지 수 처리 블럭.

		int numberOfPageBlock = 10;
		int startOfPageBlock = (currentPage - 1) / numberOfPageBlock * numberOfPageBlock + 1;
		int endOfPageBlock = startOfPageBlock + numberOfPageBlock - 1;
		if (endOfPageBlock > totalPages)
			endOfPageBlock = totalPages;

		// 2.
		PageBlock pageBlock = new PageBlock();
		pageBlock.setCurrentPage(currentPage);
		pageBlock.setStart(startOfPageBlock);
		pageBlock.setEnd(endOfPageBlock);
		pageBlock.setPrev(startOfPageBlock != 1 ? true : false);
		pageBlock.setNext(endOfPageBlock != totalPages ? true : false);

		return pageBlock;
	}
	

	@Override
	public int insert(String writer, String pw, String email, String title, int tag, String content, String fileName, String fileRealName) {
		try {
			this.pstmt = this.con.prepareStatement(insert_bb);
			this.pstmt.setString(1, writer);
			this.pstmt.setString(2, pw);
			this.pstmt.setString(3, email);
			this.pstmt.setString(4, title);
			this.pstmt.setInt(5, tag);
			this.pstmt.setString(6, content);
			this.pstmt.setString(7, fileName);
			this.pstmt.setString(8, fileRealName);
			// execute하기 전에 쿼리 해결
			int rowCount = this.pstmt.executeUpdate();
			this.pstmt.close();
			commit(con);
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
			rollBack(this.con);
		}
		return 0;
	}

	@Override
	public void increaseReaded(int seq) {
		try {
			this.pstmt = this.con.prepareStatement(increase_readed);
			this.pstmt.setInt(1, seq);
			this.pstmt.executeUpdate();
			this.pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BoardVO view(int seq) {
		try {
			this.pstmt = this.con.prepareStatement(board_view);
			this.pstmt.setInt(1, seq);
			this.rs = this.pstmt.executeQuery();

			BoardVO vo = null;
			if (rs.next()) {
				vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReaded(rs.getInt("readed"));
				vo.setWritedate(rs.getDate("writedate"));
				vo.setEmail(rs.getString("email"));
				vo.setFileName(rs.getString("filename"));
				vo.setFileRealName(rs.getString("filerealname"));
				if (vo.getFileName() == null) {
					vo.setFileName("첨부파일이 없습니다");
				}
			}
			this.rs.close();
			this.pstmt.close();
			return vo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	// -1 비밀번호 틀림
	@Override
	public int delete(int seq, String pwd) {

		try {
			this.pstmt = this.con.prepareStatement(select_pw);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if (!check_pw(seq).equals(pwd)) {
				return -1;
			} else {
				pstmt.close();
				pstmt = con.prepareStatement(delete_writing);
				pstmt.setInt(1, seq);
				int res = pstmt.executeUpdate();
				commit(con);
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return 0;
	}

	public String check_pw(int seq) {
		String oPwd = null;
		try {
			this.pstmt = this.con.prepareStatement(select_pw);

			pstmt.setInt(1, seq);
			this.rs = this.pstmt.executeQuery();
			if (rs.next()) {
				oPwd = rs.getString("pwd");
			}
			rs.close();
			return oPwd;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oPwd;
	}

	@Override
	public BoardVO edit(int seq) {
		BoardVO vo = null;
		try {
			this.pstmt = this.con.prepareStatement(select_all);
			this.pstmt.setInt(1, seq);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setWriter(rs.getString("writer"));
				vo.setEmail(rs.getString("email"));
				vo.setTitle(rs.getString("title"));
				vo.setWritedate(rs.getDate("writedate"));
				vo.setReaded(rs.getInt("readed"));
				vo.setTag(rs.getInt("tag"));
				vo.setContent(rs.getString("content"));
			}
			pstmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	public int update(String writer, String pwd, String email, String title, int tag, String content, int seq) {
		try {
			this.pstmt = this.con.prepareStatement(update_bb);
			this.pstmt.setString(1, writer);
			this.pstmt.setString(2, pwd);
			this.pstmt.setString(3, email);
			this.pstmt.setString(4, title);
			this.pstmt.setInt(5, tag);
			this.pstmt.setString(6, content);
			this.pstmt.setInt(7, seq);
			int rowCount = this.pstmt.executeUpdate();
			this.pstmt.close();
			commit(con);
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
			rollBack(this.con);
		}
		return 0;
	}
}