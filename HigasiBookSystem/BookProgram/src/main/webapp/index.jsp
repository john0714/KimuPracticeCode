<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />

<link rel="stylesheet" type="text/css" href="resources/css/style.css" >

<title>マーキュリーBookシステム with Git</title>
</head>
<% if(session.getAttribute("id")!=null) { %>
<body onLoad="document.UserLogin.submit();">
	<form action="Login.do" method="post" name="UserLogin">
		<input type="hidden" name="idtext" id="idtext" value="<%=session.getAttribute("id") %>"/>
		<input type="hidden" name="pwtext" value="<%=session.getAttribute("pw") %>" />
	</form>
</body>
<% } else{ %>
<body>
		<div class="login">
			<form action="Login.do" method="post">
			<p>ID</p>
				<input type="text" name="idtext">
			<p>パスワード</p>
				<input type="password" name="pwtext"/>
			<p></p>
			<div class="somebutton">
				<input type=submit style='font-size:16pt' value="ログイン"></input>
			</form>
			<form action="UserCreate.do">
				<input type="submit" style='font-size:16pt' value = "使用者加入"/>
			</form>
			</div>
		</div>
</body>
<% } %>
</html>