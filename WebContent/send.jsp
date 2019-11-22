<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/send" enctype="multipart/form-data">
		파일 : <input type="file" name="send"><br> 
		아이디 : <input type="text" name="id"><br>
		<button>파일전송</button>
	</form>
</body>
</html>