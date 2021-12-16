<%@page import="com.vo.BoardVO"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    BoardVO vo = (BoardVO) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>content.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 

<!-- 링크 -->
<link rel="stylesheet" href="../css/style.css">


 <!-- jquery.ui  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script> 
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

  
<style>
  table{
     border-spacing: 3px;
     border-collapse: separate; 
   }
   table,  tr, td {
      border:solid 1px gray;
      /* border-radius: 3px;  
      padding:3px;   */ 
   }
   
 #tblContent{
   width:600px;
 }
 
</style>

<script>
   $(document).ready(function (){	  
	   
	   $("#homeLink")
	     .attr("href"
	   , "/bb/List");
	   
	   $("#btnModalDelete").on("click", function(event) {
		   // 객체.메서드("open")
	   	  dialog.dialog("open");
	   });
	   
	   $("#cancel").on("click", function(event) {
		   // 객체.메서드("open")
		   	  dialog.dialog("close");
		   });
	   
	   dialog = $( "#dialog-form" ).dialog({
		      autoOpen: false,
		      height: 400,
		      width: 350,
		      modal: true,
		      buttons: {
		        /* "Create an account": addUser,
		        Cancel: function() {
		          dialog.dialog( "close" );
		        } */
		      },
		      close: function() {
		         form[ 0 ].reset();
		         //allFields.removeClass( "ui-state-error" );
		      }
		    });
		 
		 form = dialog.find( "form" );
   });
</script>
</head>
<body>
 
 <div align="center">
  <h2>내용보기</h2>
  <table id="tblContent">
   <tr>
       <td>이름</td>
       <td><%= vo.getWriter() %></td>
       <td>등록일</td>
       <td><%= vo.getWritedate() %></td>
   </tr>
   <tr>
       <td>Email</td>
       <td><a href="mailto:<%= vo.getEmail() %>"><%= vo.getEmail() %></a></td>
       <td>조회</td>
       <td><%= vo.getReaded() %></td>
   </tr>
   <tr>
        <td>제목</td>
        <td colspan="3"><%= vo.getTitle() %></td>
   </tr>
   <tr>
        <td>첨부파일</td>
        <%-- <td colspan="2"><%= vo.getFileName() %></a></td>
        <td> <form action="Download" method="GET"><input type="submit" value="다운로드"/><input type="hidden" name="seq" value="<%=vo.getSeq() %>"/></form></td> --%>
        
        
   </tr>
   <tr>
       <td colspan="4" style="padding:15px;height: 200px;text-align: left;vertical-align: top">
       <%= vo.getContent() %>
       </td>
   </tr>
   <tr>
       <td colspan="4" align="center" >
           <a class="btn btn-secondary"  href="/bb/Edit?seq=<%= vo.getSeq() %>" id="editLink">수정하기</a>
           <a class="btn btn-secondary"  href="List" id="homeLink">게시판으로</a>
           <input class="btn btn-secondary"  type="button" id="btnModalDelete" value="글 삭제"> 
           
       </td>
   </tr>
</table>
</div>

<!--  삭제 모달창 -->
<div id="dialog-form" align="center" title="삭제">
  <h2>삭제하기</h2>
  <form action="Delete" method="post">
       
    <table>
	  <tr>
	    <td colspan="2" align="center"><b>글을 삭제합니다</b></td>
	  </tr>
	  <tr>
	    <td align="center">비밀번호</td>
	    <td>
	      <input type="password" name="pwd" size="15" autofocus="autofocus">
	    </td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center">
	      <input type="submit" value="삭제">&nbsp;&nbsp;
	      <!-- <input type="button" onClick="javascript:history.back();" value="취소"> -->
	      <input type="button" id="cancel" value="취소">
	    </td>
	  </tr>
	</table> 
	<input type="hidden" name="seq" value="${ param.seq }" />
	        
</form>
</div>
 
</body>
</html>