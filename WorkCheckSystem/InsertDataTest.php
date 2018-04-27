<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />

<title>勤怠システム</title>
</head>
<body>
	<div class="Test1">
    <?php
    //echo "=======================";
    //echo $_POST["getjsvalue"]; //変数で値もらえる
    //echo $_GET["getjsvalue"]; //変数で値もらえる
    //echo "=======================";
    ?>
		<form action="TimeSheet.php" method="post">
			<p>ID</p>
				<input type="text" name="idtext"/>
			<p>パスワード</p>
				<input type="password" name="pwtext"/>
		<div class="somebutton">
			<input type=submit style='font-size:16pt' value="ログイン"/>
		</div>
		</form>
	</div>

	<!-- For Firebase Script -->
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
  firebase.initializeApp(config);//firebase초기화

	var auth, database, userInfo;
	var userRef, trafficRef, timesheetRef, attendances_dailyRef, attendances_monthlyRef, authorityRef, divisionRef;

	database = firebase.database() //Get a database Refrence
	auth = firebase.auth();
	var authProvider = new firebase.auth.GoogleAuthProvider(); //구글 인증창 선언

	//auth.createUserWithEmailAndPassword("john0714@naver.com", "123456"); //ID/PW를 통한 로그인계정 생성

	//즉, 인증은 아래 인증 상태가 바꼈을때 자동으로 체크 하는것이며 구글로 하던가 ID/PW로 생성해서 로그인 하던가 하면됨
	//아래 인증 상태가 바꼈을때 자동으로 체크하는건 페이지가 로딩됐을때 자동으로 이루어지며, 자동 로그인 체크로 사용 가능하다.

	auth.onAuthStateChanged(function(user){ //인증 상태가 바꼈을 경우 체크
		if (user) { //인증 성공
			userInfo = user;
			userRef = database.ref('Users/' + userInfo.uid ); //Make key for uid
			trafficRef = database.ref('Traffics/' + userInfo.uid );
			timesheetRef = database.ref('Timesheet/' + userInfo.uid );
			attendances_dailyRef = database.ref('Attendances_daily/' + userInfo.uid);
			attendances_monthlyRef = database.ref('Attendances_monthly/' + userInfo.uid);
			authorityRef = database.ref("Authority/"); //권한
			divisionRef = database.ref("Division/"); //직무계급
			insertlist();
			//exportlist();
		} else { //인증 실패 → 인증 팝업이 뜸
			auth.signInWithPopup(authProvider); //구글
		}
	})

	//入力
	function insertlist() {
		//비동기식(callback)형식으로 값을 받아옴(onAuth에서 값을 출력하므로)
		userRef.set({
		  user_name: "金",
		  name_kana: "キム",
			birthday: "921215",
			workplace: "内勤",
			occupation: "開発",
			division: "0",
			authority_id: "1"
			//id, passwordはグーグル認証にします。
		});
		attendances_dailyRef.set({
				20180424: {
			  	start_time: "10:00",
					end_time: "19:00",
					rest_time: "1:00"
				},
				20180425: {
					start_time: "10:00",
					end_time: "19:00",
					rest_time: "1:00"
				},
				20180426: {
					start_time: "10:00",
					end_time: "19:00",
					rest_time: "1:00"
				},
				20180427: {
					start_time: "10:00",
					end_time: "19:00",
					rest_time: "1:00"
				},
				20180501: {
					start_time: "10:00",
					end_time: "19:00",
					rest_time: "1:00"
				},
				20180502: {
					start_time: "10:00",
					end_time: "19:00",
					rest_time: "1:00"
				}
		});
		attendances_monthlyRef.set({
				201804: {
					attendances_memo: "備考",
					late_count: "0",
					overtime: "20"
				},
				201805: {
					attendances_memo: "備考",
					late_count: "0",
					overtime: "30"
				}
		});
		timesheetRef.set({
				start_time: "",
				end_time: "",
				rest_time: ""
		});
		trafficRef.set({
			departure_station: "大山",
			arrival_station: "東新宿",
			commutation_ticket: ""
		});
		authorityRef.set({
			0: {authority_name: "使用者"},
			1: {authority_name: "管理者"}
		});
		divisionRef.set({
			0: {division_name: "社員"},
			1: {division_name: "部長"}
		});
		//해당 경로에 새로운 key를 생성해서 거기에 넣음
		//DBRef.push({
		//	userid: "john0714",
		//	password: "123456",
		//});
	}

	//出力
	function exportlist() {
		//[Reference.on(읽고싶은 데이터, 데이터 조작할 함수(데이터 변수){})]
		//Example 1(데이터 하나씩 출력)
		DBRef.on('child_added', on_child_added);

		//Example 2(데이터 전체 출력)
		DBRef.on('value', function(data) {
			console.log( data.val() );
		});

		//Example 3(하위에 배열 구조가 있을경우 출력형태)
		DBRef.on('child_added', function(data) {
			console.log ( "Example 3 : " + data.val()["userid"] ); //됨
		});

		//그 외에 데이터가 삭제될 때 발생하는 child_removed나 바뀔때 발생하는child_changed등이 있음
	}

	function on_child_added(data) {
		console.log( "key : " + data.key ); //key값( = 값이 들어있는 이름 )가져옴
		console.log( "Example 1 : " +  data.val() );
		console.log( "Example 1 : " +  data.val().subname ); //됨
		var TempKey = data.key;
		var pushdata = data.val().userid;
		document.phptest.getjsvalue.value = pushdata;
		alert(document.phptest.getjsvalue.value); //phpに値もらえます。
	}

	</script>
</body>

</html>
