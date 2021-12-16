<%@page import="java.util.Iterator"%>
<%@page import="com.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write.jsp</title>
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
	/* padding:3px;  */
	
}
</style>
<script>
	$(document).ready(function() {
	});
</script>
</head>
<body>
	<!--  <h3>write.jsp</h3> -->
	<div align="center">
		<h2>글 쓰기</h2>
		<form method="post" action="../Insert" enctype="multipart/form-data">
			<table style="padding: 2px; width: 600px">
				<tr>
					<td colspan="2" align="center"><b>글을 적어주세요</b></td>
				</tr>
				<tr>
					<td align="center">이름</td>
					<td><input type="text" name="writer" size="15"
						autofocus="autofocus" required="required"></td>
				</tr>
				<tr>
					<td align="center">비밀번호</td>
					<td><input type="password" name="pwd" size="15"
						required="required"></td>
				</tr>
				<tr>
					<td align="center">Email</td>
					<td><input type="email" name="email" size="50"></td>
				</tr>
				<tr>
					<td align="center">제목</td>
					<td><input type="text" name="title" size="50"
						required="required"></td>
				</tr>
				<tr>
					<td align="center">파일 업로드</td>
					<td><input type="file" name="file"></td>
				</tr>
				<tr>
					<td align="center">내용</td>
					<td><textarea name="content" cols="50" rows="10"></textarea></td>
				</tr>
				<tr>
					<td align="center">HTML</td>
					<td><input type="radio" name="tag" value="1" checked>적용
						<input type="radio" name="tag" value="0">비적용</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="작성 완료"> &nbsp;&nbsp;&nbsp; <input type="reset"
						value="다시 작성"> &nbsp;&nbsp;&nbsp; <a
						href="http://localhost:8787/bb/index.jsp">Home</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>