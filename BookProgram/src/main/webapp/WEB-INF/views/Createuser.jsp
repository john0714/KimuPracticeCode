<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/style.css" >
<title>使用者登録画面</title>
</head>
<% if(session.getAttribute("id")==null) { response.sendRedirect("index.jsp");%>
<% } else { %>
<body>
	<div class="CreateUser">
		<form action="CreateUserInDatabase.do" method="post">
			<p>ID</p>
			<c:if test="${not empty iderror}">
			<input type="text" name="idtext" value="${idtext}" style="border: solid 2px red"/></c:if>
			<c:if test="${empty iderror}">
			<input type="text" name="idtext" value="${idtext}"/></c:if>
			<p>パスワード</p>
			<c:if test="${not empty pwerror}">
			<input type="password" name="pwtext" value="${pwtext}" style="border: solid 2px red"/></c:if>
			<c:if test="${empty pwerror}">
			<input type="password" name="pwtext" value="${pwtext}"/></c:if>
			<p>名前</p>
			<input type="text" name="nametext" value="${nametext}"/>
			<p>部署</p>
			<select name="divisiontext">
				<option value="総務部" ${divisiontext eq "総務部" ? "selected":""}>総務部</option>
				<option value="新規事業開発部" ${divisiontext eq "新規事業開発部" ? "selected":""}>新規事業開発部</option>
				<option value="採用科" ${divisiontext eq "採用科" ? "selected":""}>採用科</option>
				<option value="管理部" ${divisiontext eq "管理部" ? "selected":""}>管理部</option>
			</select>
			<p></p>
			<input type="submit" style='font-size:16pt' value = "使用者登録"/>
		</form>
		<div class="ManagementButtons">
			<form action="BookList.do">
				<input type="submit" value = "戻る"/>
			</form>
		</div>
	</div>
</body>
<% } %>
</html>