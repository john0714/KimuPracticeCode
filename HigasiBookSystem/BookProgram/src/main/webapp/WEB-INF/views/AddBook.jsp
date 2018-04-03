<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/style.css" >
<title>本の追加画面</title>
</head>
<body>
	<div class="AddBook">
		<form action="AddBookData.do" method="post">
			<p>名前</p>
			<input type="text" name="bookname"/>
			<p>種類</p>
			<input type="text" name="booksubject"/>
			<p></p>
			<input type="submit" style='font-size:16pt' value = "本を登録"/>
		</form>
	</div>
</body>
</html>