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
	<h1>FILE LIST</h1>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>파일</th>
		</tr>
		<tbody id="tBody">
		</tbody>
	</table>
	<script>
		window.onload = function() {
			var xhr = new XMLHttpRequest();
			xhr.open('GET', '/file?cmd=list');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						var list = JSON.parse(xhr.responseText);
						var tBody = document.getElementById('tBody');
						var html = '';
						console.log(list);
						for (var i = 0; i < list.length; i++) {
							html += '<tr>';
							html += '<td>' + list[i].ft_num + '</td>';
							html += '<td>' + list[i].ft_id + '</td>';
							html += '<td>' + list[i].ft_name + '</td>';
							html += '<td><img src="' + list[i].ft_file + ' "width=150></td>';
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