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
<div class = "container">
	<H1>Photo Board List</H1>
	<table class="table table-bordered">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">내용</th>
			<th scope="col">이미지1</th>
			<th scope="col">이미지2</th>
			<th scope="col">작성일</th>
			<th scope="col">작성시간</th>
			<th scope="col">작성자</th>
		</tr>
		<tbody id="tBody">
		</tbody>
	</table>
		<div class="button">
	<button onclick="goPage('/photo/insert')">글쓰기</button>
	</div>
	</div>
	<script>
		window.onload = function() {
			var xhr = new XMLHttpRequest();
			xhr.open('GET', '/photo/list');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						var list = JSON.parse(xhr.responseText);
						var tBody = document.getElementById('tBody');
						var html = '';
						console.log(list);
						for (var i = 0; i < list.length; i++) {
							html += '<tr onclick="goPage(\'/photo/view?pbNum='+ list[i].pbNum +'\')">'
							html += '<td>' + list[i].pbNum + '</td>';
							html += '<td>' + list[i].pbTitle + '</td>';
							html += '<td>' + list[i].pbContent + '</td>';
							html += '<td><img src="/img/' + list[i].pbImg1 + '" width=150></td>';
							html += '<td><img src="/img/' + list[i].pbImg2 + '" width=150></td>';
							html += '<td>' + list[i].credat + '</td>';
							html += '<td>' + list[i].cretim + '</td>';
							html += '<td>' + list[i].creusr + '</td>';
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