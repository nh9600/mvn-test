<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//pageContext.setAttribute("name","page");//한 페이지에서만 사용 가능 
//request.setAttribute("name","req");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
안녕
<%
request.setAttribute("a","");
RequestDispatcher rd = request.getRequestDispatcher("/target");
rd.forward(request,response);//전달
%>
</body>
</html>