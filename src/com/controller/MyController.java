package com.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.BoardBIZ;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vo.BoardVO;
import com.vo.PageBlock;

@WebServlet(urlPatterns = { "/List", "/Edit", "/Delete", "/Write", "/Content", "/Insert", "/Update", "/Download" })
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getRequestURI();
		if (path.endsWith("List")) {
			doList(request, response);
		}
		if (path.endsWith("Content")) {
			doContent(request, response);
		}
		if (path.endsWith("Delete")) {
			doDelete(request, response);
		}
		if (path.endsWith("Edit")) {
			doEdit(request, response);
		}
		if (path.endsWith("Write")) {
			doWrite(request, response);
		}
		if (path.endsWith("Download")) {
			doDownload(request, response);
		}
	}

	protected void doList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 제 1 장. 뷰(jsp)로부터 값을 가져온다.
		String pCurrentPage = null;
		String pNumberPerPage = null;
		String pSearchCondition = null;
		String searchWord = null;
		// 1-1. 현재 페이지
		pCurrentPage = request.getParameter("currentPage");
		int currentPage = pCurrentPage == null ? 1 : Integer.parseInt(pCurrentPage);

		// 1-2. 몇 개 띄울지
		pNumberPerPage = request.getParameter("numberPerPage");
		int numberPerPage = pNumberPerPage == null ? 10 : Integer.parseInt(pNumberPerPage);

		// 1-3. 라디오박스 1,2,3,4로 제목, 내용, 글쓴이, 제목+내용 을 정한다.
		pSearchCondition = request.getParameter("searchCondition");
		int searchCondition = pSearchCondition == null ? 1 : Integer.parseInt(pSearchCondition);
		// 1-4. 검색어. 가져온 게 없을 경우 모두 *
		searchWord = request.getParameter("searchWord");
		if (searchWord == null || searchWord == "")
			searchWord = "*";

		// 제 2장 가져온 값을 프론트에 뿌려주기 위해 가공한다 
		// 2-1. 게시글 목록
		ArrayList<BoardVO> list = new BoardBIZ().getList(currentPage, numberPerPage, searchCondition, searchWord);
		// 2-2. 게시글 블록
		PageBlock pageBlock = new BoardBIZ().getPageBlock(currentPage, numberPerPage, searchCondition, searchWord);
		request.setAttribute("list", list);
		request.setAttribute("pageBlock", pageBlock);

		// 2-3. 포워딩으로 가버렷!
		String path = "/board/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);

	}

	protected void doContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		// 1. 조회수 증가
		new BoardBIZ().getIncreaseReaded(seq);
		// 2. 게시글 보기
		BoardVO vo = new BoardBIZ().getView(seq);
		
		request.setAttribute("vo", vo);
		String path = "/board/content.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		String pPwd = request.getParameter("pwd");
		int res = new BoardBIZ().getDelete_Writing(seq, pPwd);
		String location = "/bb/List";
		if (res == 1)
			location += "?delete=success";
		response.sendRedirect(location);
	}

	protected void doInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String directory = "c:/test/";

		MultipartRequest mr = new MultipartRequest(request, directory, 1000 * 1024, "utf-8",
				new DefaultFileRenamePolicy());

		String writer = mr.getParameter("writer");
		String pw = mr.getParameter("pwd");
		String email = mr.getParameter("email");
		String title = mr.getParameter("title");
		int tag = Integer.parseInt(mr.getParameter("tag"));
		String content = mr.getParameter("content");
		String fileName = mr.getOriginalFileName("file");
		String fileRealName = mr.getFilesystemName("file");

		if (fileName != null) {
			File targetDir = new File(directory);
			if (!targetDir.exists()) {
				System.out.println("c:/test/ 폴더 만들어서 자료 넣는다~");
				targetDir.mkdirs();
			}
		}

		int res = new BoardBIZ().getWriting(writer, pw, email, title, tag, content, fileName, fileRealName);

		String location = "/bb/List";
		if (res == 1)
			location += "?write=success";
		response.sendRedirect(location);
	}

	protected void doWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/bb/board/write.jsp";
		response.sendRedirect(path);
	}

	protected void doEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		BoardVO vo = new BoardBIZ().getEditing(seq);
		request.setAttribute("vo", vo);
		String path = "/board/edit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);

	}

	protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		System.out.println(Integer.parseInt(request.getParameter("seq")));
		String writer = request.getParameter("writer");
		String pw = request.getParameter("pwd");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		int tag = Integer.parseInt(request.getParameter("tag"));
		String content = request.getParameter("content");
		int res = new BoardBIZ().getUpdating(writer, pw, email, title, tag, content, seq);

		String location = "/bb/List";
		if (res == 1)
			location += "?edit=success";
		response.sendRedirect(location);

	}
	protected void doDownload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		BoardVO vo = new BoardBIZ().getView(seq);
		// 1. 경로설정
		String downloadPath = "c:/test";
		String fileName = vo.getFileName();
		if (fileName != "첨부파일이 없습니다") {
			File targetDir = new File(downloadPath);
			if (!targetDir.exists()) {
				System.out.println("c:/test/download 폴더 만들어서 다운로드 받는다~");
				targetDir.mkdirs();
			}
		String filePath = downloadPath + "\\" + fileName;
		File file = new File(filePath);
		// 2. MIMETYPE 설정
		String mimeType = getServletContext().getMimeType(filePath);
		if (mimeType == null)
			mimeType = "application/octet-stream";
		response.setContentType(mimeType);
		
		// 3. 다운로드 설정 및 인코딩 설정
		String encoding = new String(fileName.getBytes("utf-8"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; filename= " + encoding);
		
		// 4. 요청 파일을 읽어서 클라이언트에 전송
		FileInputStream in = new FileInputStream(file);
		ServletOutputStream outStream = response.getOutputStream();
		byte b[] = new byte[4096];
		int data = 0;
		while ((data = in.read(b, 0, b.length)) != -1) {
			outStream.write(b, 0, data);
		}
		outStream.flush();
		outStream.close();
		in.close();
		}else {
			// 5. 첨부파일이 없다면 아무것도 하지 않겠다는 의지
			response.sendRedirect("Content?seq="+vo.getSeq());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String path = request.getRequestURI();

		if (path.endsWith("Insert")) {
			doInsert(request, response);
		}
		if (path.endsWith("Edit")) {
			doEdit(request, response);
		}
		if (path.endsWith("Update")) {
			doUpdate(request, response);
		}

	}
}
