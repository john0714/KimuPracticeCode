<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="org.springframework.context.*" %>
<%@ page import="org.springframework.context.support.*" %>
<%@ page import="org.springframework.jdbc.datasource.DriverManagerDataSource" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="resources/css/style.css?ver=1" >
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<title>本システム履歴</title>
</head>
<body>
	<table class="table">
		<tr class="warning">
				<td>カウント</td>
				<td>本の名前</td>
				<td>人</td>
				<td>時間</td>
				<td>種類</td>
		</tr>
		
		<!-- 関数の中のインスタンス変数を呼んでできる -->
		<c:forEach var="listValue" items="${LogList}">
			<tr class="active">
			<form action="rentalandreturn.do" method="post">
				<td><c:out value="${listValue.ID}" /></td>
				<td><c:out value="${listValue.BOOKNAME}" /></td>
				<td><c:out value="${listValue.PEOPLENAME}" /></td>
				<td><c:out value="${listValue.TIME}" /></td>
				<td><c:out value="${listValue.RENTAL_CHECK}" /></td>
			</tr>
			</form>
		</c:forEach>
	</table>
</body>
</html>