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
		<table class="table table-bordered">
			<h1>확인</h1>
			<tr>
				<th>번호</th>
				<td data-id="uiNum"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td data-id="uiName"></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td data-id="uiId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td data-id="uiPwd"></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td data-id="credat"></td>
			</tr>
			<tr>
				<th>가입시간</th>
				<td data-id="cretim"></td>
			</tr>
			<tr>
				<th>활성여부</th>
				<td data-id="active"></td>
			</tr>
			<tr>
				<th colspan="2">
					<button onclick="update(this)">수정</button>
					<button onclick="deleteUser()">삭제</button>
					<button onclick="goPage('/ajax/user/list')">리스트가기</button>

				</th>
		</table>
	</div>
	<script>
		var user;
		window.onload = function() {
			var xhr = new XMLHttpRequest();
			xhr.open('GET', '/ajax/user/view?cmd=view&uiNum=${param.uiNum}');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						user = JSON.parse(xhr.responseText);
						var tds = document.querySelectorAll('td[data-id]');
						for (var idx = 0; idx < tds.length; idx++) {
							var td = tds[idx];
							var key = td.getAttribute('data-id');
							td.innerHTML = user[key];
						}
					}
				}
			}
			xhr.send();
		}
		function deleteUser() {
			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/ajax/user/delete?cmd=delete');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						var res = JSON.parse(xhr.responseText);
						alert(res.msg);
						if (res.result == 'true') {
							goPage('/ajax/user/list');
						}
					}
				}
			}
			var param = {
				uiNum : user.uiNum
			}
			xhr.send(JSON.stringify(param));
		}

		function update(btn) {
			btn.onclick = updateUser;
			var list = document
					.querySelectorAll('td[data-id=active],td[data-id=uiName]');
			for (var i = 0; i < list.length; i++) {
				var td = list[i];
				var id = td.getAttribute('data-id');
				td.innerHTML = '<input type="text" id="' + id + '" value="'+ user[id]+'">';
			}
		}
		function updateUser() {
			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/ajax/user/update?cmd=update');
			xhr.onreadystatechange = function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						var res = JSON.parse(xhr.responseText);
						alert(res.msg);
						if(res.result=='true'){
							goPage('/ajax/user/list');
						}
					}
				}
			}
			var param = {
				uiNum : user.uiNum,
				uiName : document.querySelector('#uiName').value,
				active : document.querySelector('#active').value
			}
			xhr.send(JSON.stringify(param));
		}
	</script>

</body>
</html>