<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>東新宿　本システム</title>
</head>
<% if(session.getAttribute("id")==null) { response.sendRedirect("index.jsp");%>
<% } else { %>
<body>
	<form action="Logout.do">
		<input type="submit" value="ログアウト"/>
	</form>
	<table class="table">
		<tr class="warning">
				<td>Code</td>
				<td>名前</td>
				<td>種類</td>
				<td>登録時間</td>
				<td>貸出し</td>
		</tr>
		
		<!-- 関数の中のインスタンス変数を呼んでできる -->
		<c:forEach var="listValue" items="${TestList}">
			<tr class="active">
			<form action="rentalandreturn.do" method="post">
				<input type="hidden" value="${listValue.id}" name="id"/>
				<input type="hidden" value="${listValue.rentalCheck}" name="RC"/>
				<td><c:out value="${listValue.id}" /></td>
				<td><c:out value="${listValue.name}" /></td>
				<td><c:out value="${listValue.subject}" /></td>
				<td><c:out value="${listValue.insertTime}" /></td>
				<c:if test="${listValue.rentalCheck eq 0}">
					<td>貸出し可能</td>
				</c:if>
				<c:if test="${listValue.rentalCheck eq 1}">
					<td>貸出し中</td>
				</c:if>
			</tr>
			</form>
		</c:forEach>
	</table>
</body>
<% } %>
</html>