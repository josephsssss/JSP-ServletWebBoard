<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- meta 선언 -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- link 선언 -->
<link rel="stylesheet" href="./css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
<title>게시판</title>
</head>
<body>
	<div class="row">
		<form  action="List" method="GET">
			<input id="left" class="buttons" type="submit" name="List" value="게시판" />
		</form>
		<form  action="Write" method="POST">
			<input id="right" class="buttons" type="submit" name="next" value="글쓰기" />
		</form>
	</div>
</body>
</html>
