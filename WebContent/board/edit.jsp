<%@page import="com.vo.BoardVO"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    BoardVO vo = (BoardVO)request.getAttribute("vo");
	int seq = 0;
	if (request.getParameter("seq") != null) {
	seq = Integer.parseInt(request.getParameter("seq"));
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 링크 -->
<link rel="stylesheet" href="../css/style.css">
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
	/*  padding:3px;  */
	
}

table {
	width: 600px;
}
</style>
<script>
   $(document).ready(function (){	  
   });
</script>
</head>
<body>

	<div align="center">
		<h2>수정하기</h2>
		<!-- [중요사항] action 속성 X -->
		<form method="post" action="Update">
		<input type="hidden" value="<%=seq%>" name="seq"/>
			<table>
				<tr>
					<td colspan="2" align="center"><b>글을 수정합니다</b></td>
				</tr>
				<tr>
					<td align="center">이름</td>
					<td><input type="text" readonly name="writer" size="15"
						value="<%= vo.getWriter() %>"></td>
				</tr>
				<tr>
					<td align="center">Email</td>
					<td><input type="email" name="email" size="50"
						value="<%= vo.getEmail() %>"></td>
				</tr>
				<tr>
					<td align="center">제목</td>
					<td><input type="text" name="title" size="50"
						value="<%= vo.getTitle() %>"></td>
				</tr>
				<tr>
					<td align="center">내용</td>
					<td><textarea name="content" cols="50" rows="10"><%= vo.getContent() %></textarea>
					</td>
				</tr>
				<tr>
					<td align="center">HTML</td>
					<td>
					<input type="radio" name="tag" value="1">적용 
					<input type="radio" name="tag" value="0">비적용 
					<script>
		        // JS  에서 EL 사용하는 방법    "EL 사용가능"
		        // :radio      ==  input type=radio
		        $(":radio[value=<%= vo.getTag() %>]").attr("checked", "checked");
		      </script>
		      </td>
				</tr>
				<tr>
					<td align="center">비밀번호</td>
					<td><input type="password" name="pwd" size="15"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="작성 완료">&nbsp;&nbsp; 
					<input type="button" onClick="javascript:history.back();" value="이전으로">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>