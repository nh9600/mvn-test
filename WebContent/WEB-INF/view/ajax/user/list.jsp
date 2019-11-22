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
	<H1>USER 리스트</H1>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">이름</th>
					<th scope="col">아이디</th>
					<th scope="col">비밀번호</th>
					<th scope="col">생성일</th>
					<th scope="col">생성시간</th>
				</tr>
			</thead>
			<tbody id="tBody">
			</tbody>
		</table>
		<div class="button">
		<button onclick="goPage('/ajax/user/signup')">회원가입</button>
		</div>
		<script>
			window.onload = function() {
				var xhr = new XMLHttpRequest();
				xhr.open('GET', '/ajax/user/list?cmd=list');
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {
							var list = JSON.parse(xhr.responseText);
							var tBody = document.getElementById('tBody');
							var html = '';
							console.log(list);
							for (var i = 0; i < list.length; i++) {
								html += '<tr onclick="goPage(\'/ajax/user/view?uiNum='+list[i].uiNum+'\')">';
								html += '<td>' + list[i].uiNum + '</td>';
								html += '<td>' + list[i].uiName + '</td>';
								html += '<td>' + list[i].uiId + '</td>';
								html += '<td>' + list[i].uiPwd + '</td>';
								html += '<td>' + list[i].credat + '</td>';
								html += '<td>' + list[i].cretim + '</td>';
								html += '</tr>';
							}
							tBody.innerHTML = html;

						}
					}
				}
				xhr.send();

			}
		</script>
</body>
</html>