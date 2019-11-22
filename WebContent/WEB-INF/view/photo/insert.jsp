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
		<h1>Insert</h1>
		<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td><input type="text" id="pbTitle" placeholder="제목을 입력해주세요."></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea type="text" id="pbContent"
						placeholder="내용을 입력해주세요."></textarea></td>
			</tr>
			<tr>
				<th>유저</th>
				<td><input type="number" id="creusr"></td>
			</tr>
			<tr>
				<th>이미지1</th>
				<td><input type="file" id="pbImg1"></td>
			</tr>
			<tr>
				<th>이미지2</th>
				<td><input type="file" id="pbImg2"></td>
			</tr>
			<tr>
				<th colspan="2"><button onclick="upload()">업로드</button> <progress
						id="pro" value="0" max="100"></progress></th>
				<div id="rDiv"></div>
			</tr>
		</table>
	</div>
	<script>
		function upload() {
			var formData = makeFormData();//위에 것을 불러옴 
			var conf={
					method:'POST',
					url:'/photo/insert',
					func:function(res){
						console.log(res);
					},
					data:formData
			}
			send(conf);
			/* 	for(var obj of formData.entries()){
				console.log(obj);} */


		}
	</script>

</body>
</html>