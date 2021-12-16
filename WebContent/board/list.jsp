<%@page import="com.vo.PageBlock"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<BoardVO> list =
                           (ArrayList<BoardVO>)request.getAttribute("list");

    PageBlock pageBlock =  (PageBlock)request.getAttribute("pageBlock");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 링크 -->
<!-- <link rel="stylesheet" href="../css/style_list.css"> -->
<style>
table, td, th {
	border: solid 1px gray;
}

table {
	border-spacing: 3px;
	border-collapse: separate;
}

table, tr, td {
	/* border-radius: 3px; */
	/* padding:3px;  */
	
}

tbody tr  td:nth-of-type(2) {
	text-align: left;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	color: red;
}
</style>
<!-- 페이징 처리 style -->
<style>
.pagination {
	margin: 0 auto;
	display: flex;
	justify-content: center;
}

.pagination a {
	color: black;
	float: left;
	padding: 4px 8px;
	text-decoration: none;
	transition: background-color .3s;
}

.pagination a.active {
	background-color: dodgerblue;
	color: white;
}

.pagination a:hover:not (.active ) {
	background-color: #ddd;
}
</style>
<style>
.searchWord {
	background-color: yellow;
	color: red;
}
</style>
<style>
.title {
	display: inline-block;
	white-space: nowrap;
	width: 90%;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<script>
   $(document).ready(function (){	  
	   // Write?write=success
	   if( '<%= request.getParameter("write") %>' == 'success' ){
		   alert("글 쓰기 완료!!!");
	   }		   
	   
	   // 상태 유지
	   $("#searchCondition").val( '${  requestScope.searchCondition }'); 
	   $("#searchWord").val('${ param.searchWord}');

   });
</script>
</head>
<body>
 <!-- <h3>list.jsp</h3> -->
 <div align="center">
  <h2>목록 보기</h2>
  <a href="/bb/index.jsp">메인페이지</a>
  <a href="/bb/Write">글쓰기</a>
  <table style="width:600px;">
    <thead>
      <tr>
        <th width="10%">번호</th>
        <th width="45%">제목</th>
        <th width="17%">작성자</th>
        <th width="20%">등록일</th>
        <th width="10%">조회</th>
      </tr>
    </thead>
    <tbody>
    <%
        if( list == null ){
   %>
      <tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
   <%     	
        }else{
        	Iterator<BoardVO> ir = list.iterator();
        	while( ir.hasNext() ){
        		BoardVO vo = ir.next();
   %>
      <tr align="center">
        <td><%= vo.getSeq() %></td>
        <%-- <td><%= vo.getTitle() %></td> --%>
        <td>
           <a class="title" href="/bb/Content?seq=<%= vo.getSeq() %>"><%= vo.getTitle() %></a>
           <%
              if( vo.isNewmark() ){
           %><img src="D:\JspWork\bb5\WebContent\img\new-icon16.png" alt="새 글" /><%  	  
              }
           %>
        </td>
        
        <td>
           <%
              if( vo.getWriter().equals("관리자") ){
           %><img src="D:\JspWork\bb5\WebContent\img\manager.png" alt="관리자" /><%  	  
              }
           %>
            <a href="mailto:<%= vo.getEmail() %>"><%= vo.getWriter() %></a>
        </td>
        <td><%= vo.getWritedate() %></td>
        <td><%= vo.getReaded() %></td>
      </tr>
   <%        		
        	} // while
        } // if
    %>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="5" align="center">
           <div class="pagination">   
             <%
               if( pageBlock.isPrev() ){
             %>
             <a href="/bb/List?currentPage=<%= pageBlock.getStart() -1 %>"> &laquo; </a>
             <%	   
               } 
                for(int i = pageBlock.getStart() ; i <= pageBlock.getEnd() ; i++ ){
             %>
              <a class="<%= i==pageBlock.getCurrentPage() ? "active":""  %>" href="List?currentPage=<%= i %>"><%= i %></a>
             <%                	
                }
               if( pageBlock.isNext() ){
             %>
             <a href="/bb/List?currentPage=<%= pageBlock.getEnd() + 1 %>"> &raquo; </a>
             <%	   
               }  
             %>
             
           </div>
        </td>
      </tr>
      <tr>
        <td colspan="5" align="center">
          <form>
            <select name="searchCondition" id="searchCondition">
              <option value="1">제목</option>
              <option value="2">내용</option>
              <option value="3">글쓴이</option>
              <option value="4">제목+내용</option>
            </select>
            <input type="text" name="searchWord" id="searchWord" />
            <input type="submit" value="search" />
          </form>
        </td>
      </tr>
    </tfoot>
  </table>
 </div>
</body>
</html>



<%--
      ${}     js주석, html주석 X
      /* 
	   $("#searchCondition").val(
			   '${  empty param.searchCondition ? 1 :  param.searchCondition }');
	    */
	    // ?searchCondition     param.searchCondition
	    // request Scope     	 searchCondition
	    // ${}   requestScope EL 에서만 사용할 수 있는 객체
 --%>
