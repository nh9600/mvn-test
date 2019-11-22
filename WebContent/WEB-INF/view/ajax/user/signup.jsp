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
	<h1>Sign-up</h1>
	<table class="table table-bordered">
		<tr>
			<th>이름</th>
			<td><input type="text" id="uiName"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type="text" id="uiId"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" id="uiPwd"></td>
		</tr>
		<tr>
			<th colspan="2"><button onclick='save()'>저장</button></th>
		</tr>
	</table>
	<script>
		function save() {
			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/ajax/user/signup');
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						var res = JSON.parse(xhr.responseText);
						alert(res.msg);
						if(res.result=='true'){
							goPage('/ajax/user/list');
						}
					}
				}
			}
			var param = {
					uiName:document.querySelector('#uiName').value,
					uiId:document.querySelector('#uiId').value,
					uiPwd:document.querySelector('#uiPwd').value
			}
			xhr.send(JSON.stringify(param));
		}
	</script>


</body>
</html>