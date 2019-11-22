<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
하이
<%
String a = request.getParameter("a");
request.getParameterValues("a");
//String name = (String)pageContext.getAttribute("name");//오브젝트니까 형변환 해줘야함 
//out.println(name);
//String a = (String)request.getAttribute("a");
//RequestDispatcher rd = request.getRequestDispatcher("/target");
//rd.forward(request,response);//전달
%>
</body>
</html>