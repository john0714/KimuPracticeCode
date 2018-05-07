<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />

<title>テストページ</title>
</head>
<body>
	<div class="Test1">
    <?php
    //echo "=======================";
    //echo $_POST["getjsvalue"]; //変数で値もらえる
    //echo $_GET["getjsvalue"]; //変数で値もらえる
    //echo "=======================";
    ?>
		<form action="TimeSheet.php" name="GetValue" method="post">
			<input type=submit style='font-size:16pt' value="シフト画面に行こう"/>
			<input type=textarea name="Attendances_monthly" style='font-size:16pt'/>
			<input type=textarea name="Attendances_daily" style='font-size:16pt'/>
			<input type=textarea name="Users" style='font-size:16pt'/>
			<input type=textarea name="AuthUser" style='font-size:16pt'/>
		</form>
	</div>
	    <script src="https://www.gstatic.com/firebasejs/4.13.0/firebase.js"></script>
	    <script>
	      // Initialize Firebase
	      var config = {
	        apiKey: "AIzaSyCQ_M_PmSh6IqKXrZ7mBGFXlpkJy_QJeGs",
	        authDomain: "testproejct-d15c9.firebaseapp.com",
	        databaseURL: "https://testproejct-d15c9.firebaseio.com",
	        projectId: "testproejct-d15c9",
	        storageBucket: "testproejct-d15c9.appspot.com",
	        messagingSenderId: "557330742678"
	      };
	      firebase.initializeApp(config);

	      var auth, database, userInfo;
	      var Attendances_monthlyRef, Attendances_dailyRef, UsersRef;
	      database = firebase.database();

	      auth = firebase.auth();
	      var authProvider = new firebase.auth.GoogleAuthProvider(); //구글 인증창 선언
	      auth.onAuthStateChanged(function(user){
	        if (user) { //인증 성공 → need for login?
	          userInfo = user;
						document.GetValue.AuthUser.value = userInfo.uid; //uid

	          Attendances_monthlyRef = database.ref('Attendances_monthly/'
	                                                + userInfo.uid );
						Attendances_dailyRef = database.ref('Attendances_daily/'
																				          + userInfo.uid );
						UsersRef = database.ref('Users/'
																								  + userInfo.uid );
						exportmonth();
	        } else { //인증 실패 → 인증 팝업이 뜸
	          auth.signInWithPopup(authProvider);
	        }
	      })

	      function exportmonth() {
	        Attendances_monthlyRef.on('value', function(data){
						document.GetValue.Attendances_monthly.value = JSON.stringify(data);
	        });
					Attendances_dailyRef.on('value', function(data){
						document.GetValue.Attendances_daily.value = JSON.stringify(data);
	        });
					UsersRef.on('value', function(data){
						document.GetValue.Users.value = JSON.stringify(data);
	        });

					alert("Success");
	      }
	    </script>
</body>

</html>
