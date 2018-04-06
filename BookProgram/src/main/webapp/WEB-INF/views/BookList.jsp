<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="org.springframework.context.*" %>
<%@ page import="org.springframework.context.support.*" %>
<%@ page import="org.springframework.jdbc.datasource.DriverManagerDataSource" %>
<%@ page import="com.higasi.booksystem.Token" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="resources/css/style.css?ver=1" >
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>東新宿　本システム</title>
</head>
<!-- onbeforeunload, onpageshow로 safari뒤로갔을때 이벤트(Chrome, IE는 없어도 가능)(일부러 GET으로 화면다시 보내서 페이지 안띄워지게함) -->
<script>
window.onpageshow = function(event) {
    if (event.persisted) {
    		alert("否定接続ですのでログイン画面に戻ります。")
        window.location.replace("index.jsp");
    }
};
</script>
<body onbeforeunload="">
<div class="container">
	<header>
	<div class="ManagementButtons">
	<form action="Logout.do">
		<input type="submit" value="ログアウト"/>
	</form>
	<form action="AddBook.do">
		<input type="submit" value="本新規登録"/>
	</form>
	<form action="ViewLog.do">
		<input type="submit" value="レンタル履歴"/>
	</form>
	<form action="UserCreate.do">
		<input type="submit" value = "ユーザー新規登録"/>
	</form>
	</div>
	<div class="Search">
		<form action="BookListSearch.do" method="post">
		<select name="SCheck">
			<option value="id" ${SCheck eq "id" ? "selected":""}>Code</option>
			<option value="name" ${SCheck eq "name" ? "selected":""}>名前</option>
			<option value="subject" ${SCheck eq "subject" ? "selected":""}>種類</option>
			<option value="insert_time" ${SCheck eq "insert_time" ? "selected":""}>登録時間</option>
		</select>
		<input type="text" name="SearchText" value="${SearchText}"/>
		<input type="submit" style="margin-left: -4px" value="検索" />
		</form>
	</div>
	</header>
	<table class="table">
		<tr class="warning">
				<td>No.<a href="#" onclick="sortTable(0)">↕</a></td>
				<td>名前<a href="#" onclick="sortTable(1)">↕</a></td>
				<td>種類<a href="#" onclick="sortTable(2)">↕</a></td>
				<td>登録日時<a href="#" onclick="sortTable(3)">↕</a></td>
				<td>貸出者</td>
				<td>作業<a href="#" onclick="sortTable(5)">↕</a></td>
		</tr>
		
		<!-- 関数の中のインスタンス変数を呼んでできる -->
		<c:forEach var="listValue" items="${TestList}">
			<tr class="active">
			<form action="rentalandreturn.do" method="post">
				<%
        			if(request.getAttribute("TOKEN_KEY")==null) Token.set(request);
       			%>
      			<input type="hidden" name="TOKEN_KEY" value="<%=request.getAttribute("TOKEN_KEY")%>"/>
				<input type="hidden" value="${listValue.id}" name="id"/>
				<input type="hidden" value="${listValue.rentalCheck}" name="RC"/>
				<td><c:out value="${listValue.id}" /></td>
				<td><c:out value="${listValue.name}" /></td>
				<td><c:out value="${listValue.subject}" /></td>
				<td><c:out value="${listValue.insertTime}" /></td>
				<c:if test="${listValue.rentalCheck eq 0}">
				<td>
					<select name="UserCode">
					<%	
					Connection conn = null;
					PreparedStatement pstmt = null;
					ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
					DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
					conn = dataSource.getConnection();
					pstmt = conn.prepareStatement("Select id_count, name, division from User");
					ResultSet rs = pstmt.executeQuery();
				
					while(rs.next()) { %>
					<option value=<%=rs.getString(1)%>><%=rs.getString(3)%>-<%=rs.getString(2)%></option>
					<% } %>
					</select>
				</td>
				</c:if>
				<c:if test="${listValue.rentalCheck eq 1}">
					<input type="hidden" value="${listValue.rentalUserID}" name="UserCode"/>
					<td><c:out value="${listValue.rentalUserInfo}" /></td>
				</c:if>
				<c:if test="${listValue.rentalCheck eq 0}">
					<!-- textの値を送るため hidden typeを作ります -->
					<td>
						<input type="submit" onclick="" value="貸出" />
					</td>
				</c:if>
				<c:if test="${listValue.rentalCheck eq 1}">
					<td><input type="submit" onclick="" value="返却" /></td>
				</c:if>
			</tr>
			</form>
		</c:forEach>
	</table>
</div>
</body>
<script>
	/**
	 * JavaScriptで複数サーブミット防止
	 * 隙間がありますのでTokenの方法に帰ります。
	 * @returns {Boolean}
	 */
	function insert() {
		if(doubleSubmitCheck()) {
			alert("処理中です。\n少々お待ちしてください。");
		}
	}
	
	var doubleSubmitFlag = false;
	function doubleSubmitCheck(){
    		if(doubleSubmitFlag){
        		return doubleSubmitFlag;
    		}else{
        		doubleSubmitFlag = true;
        		return false;
    		}
	}
	
	//Sort方法；insert Sort
	var sortType = 'asc';
	function sortTable(num) {
		var table = document.getElementsByTagName('table');
		var BookListRows = table[0].rows;
			
		if (sortType === 'asc') {
	        sortType = 'desc';
	        sortTableDesc(BookListRows, num);
	    } else {
	        sortType = 'asc';
	        sortTableAsc(BookListRows, num);
	    }
	}
	
	function sortTableAsc(BookListRows, num) {
		for(var i = 2; i < BookListRows.length; i++) {
			var key = BookListRows[i]; //一番後ろの値がkey
			for(j = i-1; j>=1 ; j--) {
				hCell = BookListRows[j];
				//toLowerCase() method converts a string to lowercase letters.
				if(hCell.cells[num].innerHTML.toLowerCase() > key.cells[num].innerHTML.toLowerCase()) {
					hCell.parentNode.insertBefore(key, hCell);
				} else {
					break;
				}
			}
		}
	}
	
	function sortTableDesc(BookListRows, num) {
		for(var i = 2; i < BookListRows.length; i++) {
			var key = BookListRows[i]; //一番後ろの値がkey
			for(j = i-1; j>=1 ; j--) {
				hCell = BookListRows[j];
				//toLowerCase() method converts a string to lowercase letters.
				if(hCell.cells[num].innerHTML.toLowerCase() < key.cells[num].innerHTML.toLowerCase()) {
					hCell.parentNode.insertBefore(key, hCell);
				} else {
					break;
				}
			}
		}
	}
</script>
</html>