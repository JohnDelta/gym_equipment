<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%

	String text = "old user!!";
	if(session.getAttribute("userId") == null) {
		session.setAttribute("userId", "old");
		text = "new user initialized!!";
	}

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		index <%=text%>
		<img src="images/default.png" />
	</body>
</html>