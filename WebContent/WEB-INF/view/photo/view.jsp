<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/view/common/head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<h1>View</h1>
		<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td data-id="pbTitle"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td data-id="pbContent"></td>
			</tr>
			<tr>
				<th>유저</th>
				<td data-id="creusr"></td>
			</tr>
			<tr>
				<th>이미지1</th>
				<td data-id="pb_Img1"></td>
			</tr>
			<tr>
				<th>이미지2</th>
				<td data-id="pb_Img2"></td>
			</tr>
			<tr>
				<th colspan="2">
					<button onclick="update()">수정</button>
					<button onclick="deleteUser()">삭제</button>
					<button onclick="goPage('/photo/list')">리스트가기</button>
				</th>
				<div id="rDiv"></div>
			</tr>
		</table>
	</div>
	<script>
		function upload() {
			var formData = makeFormData();//위에 것을 불러옴 
			var conf = {
				method : 'POST',
				url : '/photo/insert',
				func : function(res) {
					console.log(res);
				},
				data : formData
			}
			send(conf);
			/* 	for(var obj of formData.entries()){
				console.log(obj);} */

		}
	</script>

</body>
</html>