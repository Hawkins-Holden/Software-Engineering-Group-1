<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Spring MVC Tutorial Series by Crunchify.com</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>
	<br>
	<div style="text-align: center">
		<h3>
			<!-- 			<a href="GuestBook.jsp">Go To Form</a> -->
			<!-- 			<a href="welcome.jsp">Go To Welcome</a> -->
		</h3>
		<form method="POST" action="GuestBook.jsp">
			<input type="text" name="fname" placeholder="City" /> 
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>