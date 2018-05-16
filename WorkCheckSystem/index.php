<!DOCTYPE html>
<!--
	180425
	情報送信用ページ
	→ 管理者ページから移動する場合データ必要
	jhkim
-->

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<!-- javascript(node.js) Firebase 宣言-->
<script src="https://www.gstatic.com/firebasejs/4.13.0/firebase.js"></script>
<script type="text/javascript" src="db.js"></script>

<title>情報送信用ページ</title>
</head>
<body>
	<div class="社員のメニュー">
		<form action="TimeSheet.php" name="GetValue" method="get">
			<input type=submit style='font-size:16pt' value="シフト履歴にデータ送信"/>
			<!-- <input type=textarea name="Attendances_monthly" style='font-size:16pt'/>
			<input type=textarea name="Attendances_daily" style='font-size:16pt'/>
			<input type=textarea name="Users" style='font-size:16pt'/> -->
			<input type=textarea name="AuthUser" style='font-size:16pt'/>
		</form>
	</div>
			<script>
			//auth.createUserWithEmailAndPassword("john0712@naver.com", "123456") //ただのID, PW生成(メールとPW)
			//auth.signInWithEmailAndPassword("john0712@naver.com", "123456")// ID, PW ログイン(メールとPW)
			//auth.signInWithEmailAndPassword("john0712@naver.com", "123456").then(function(user) {
			//	var userssss = auth.currentUser;
			//  alert(userssss.uid);
			//}) // ID, PW ログイン(メールとPW)
			//auth.signOut() //ログアウト, しないとsignされた認証が残されます。
			var userInfo
			var authProvider = new firebase.auth.GoogleAuthProvider(); //Google Authentication Various
	      auth.onAuthStateChanged(function(user){ //Authentication ChangeCheck(Sessioncheck)
	        if (user) { //Authentication User is signed in. → don't need Other Authentication
	          userInfo = user;
						document.GetValue.AuthUser.value = userInfo.uid; //uid送信

	          // Attendances_monthlyRef = database.ref('Attendances_monthly/'
	          //                                       + userInfo.uid );
						// Attendances_dailyRef = database.ref('Attendances_daily/'
						// 														          + userInfo.uid );
						// UsersRef = database.ref('Users/'
						// 																		  + userInfo.uid );
						// exportmonth();
	        } else { //Authentication No user is signed in. → need Other Authentication
	          auth.signInWithPopup(authProvider); //上で設定した Google Authentication認証 and save
						//alert("문제발생");
	        }
	      })

	      function exportmonth() {
	        Attendances_monthlyRef.on('value', function(data){
						document.GetValue.Attendances_monthly.value = JSON.stringify(data); //月データ送信
	        });
					Attendances_dailyRef.on('value', function(data){
						document.GetValue.Attendances_daily.value = JSON.stringify(data); //日データ送信
	        });
					UsersRef.on('value', function(data){
						document.GetValue.Users.value = JSON.stringify(data); //ユーザーデータ送信
	        });

					alert("データ送信準備完了!");
	      }
	    </script>
</body>

</html>
