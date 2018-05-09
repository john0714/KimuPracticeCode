<!DOCTYPE html>
<!--
  180425
  シフト画面
  Html/css：ユンへリン
  機能構築：jhkim
-->
<html lang="jp" dir="ltr">
<head>
  <script src="https://use.fontawesome.com/926fe18a63.js"></script>
  <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
  <script>
    $(document).ready(function(){
      $(".headB").on('click', function(){
        $(".headA").animate({width:"toggle"}, 200);
      });
    });
  </script>

  <meta charset="utf-8">
  <link rel="stylesheet" href="timeSheet_style.css">
  <link rel="shortcut icon" href=""> <!-- remove favico.ico error -->

  <title>シフト履歴</title>
     <?php
	    date_default_timezone_set('Asia/Tokyo'); //  default地域設定
        $monthlyDay = date("t"); //一か月の最終日
        $month = date("m"); // 現在の月
	      $year = date("Y"); // 現在の年
        $strtotime = $year."-".$month."-1"; //毎月1日の曜日を求めるための変数
        $dailyInt = date('w', strtotime($strtotime)); // date関数は0~6の数字をreturnする
        $daily = array('日','月','火','水','木','金','土');
     ?>
</head>

<body>
  <header>
    <div class="container">
      <div class="container-small">
        <button type="button" class="headB">
          <span class="fa fa-bars"></span>
        </button>
      </div>
      <nav class="headA">
        <ul>
            <li><a href="#">出・退勤</a></li>
            <li><a href="#">個人情報</a></li>
            <li><a href="#">履歴</a></li>
            <li><a href="#">管理者</a></li>
            <li><a href="#">ログアウト</a></li>
        </ul>
      </nav>
    </div>
  </header>
</div>
  <?php
  $YMs = json_decode($_POST["Attendances_monthly"], true); //配列送信(trueは stdClassから Arrayに変更する為)
  $Days = json_decode($_POST["Attendances_monthly"], true);
  $UserData = json_decode($_POST["Users"], true);
  ?>
  <b>シフト履歴</b>
  <hr>
  <div class="timeSheet-form">
    <form action="TimeSheet.php" name="phptest" method="post">
      <input type="submit" onclick="exportdays()" name="test" class="download-btn"/>
    </form>
    <div id="dataaa"></div>
    <div class="select-form">
      <select>
        <?php
        for($i=0; $i<count($YMs); $i++) { ?>
        <option><?=$YMs[$i]?></option>
        <?php }?>
      </select>
        <button type="button" name="search" class="search-btn">検索</button>
      <form action="ExcelTest.php" method="post">
      <input type="submit" name="memberInsert" class="download-btn" value="Excelダウンロード"></input>
    </div>
    <table>
      <tr>
        <th class="small-cell">日</th>
        <th class="small-cell">曜</th>
        <th>勤務地</th>
        <th>出勤</th>
        <th>退勤</th>
        <th>休憩</th>
        <th>残業</th>
        <th></th>
      </tr>
      <?php for($i=1;$i<$monthlyDay+1;$i++) {
      $day = $daily[$dailyInt]; //曜日を表すための変数
      ?>
      <tr>
        <td class="small-cell">
          <?=$i?>
        </td>
        <td class="small-cell">
          <?=$day?>
        </td>
        <td id="workplaces<?=$i?>"></td>
        <td id="starttime<?=$i?>"></td>
        <td id="endtime<?=$i?>"></td>
        <td id="resttime<?=$i?>"></td>
        <td id="overworktime<?=$i?>"></td>
        <td><button type="button" class="modify-btn">編集</button></td>
      </tr>
      <?php if($dailyInt<6) {$dailyInt++;} else {$dailyInt=0;}} //일주일 요일 초기화 ?>
      <tr class="memo-cell">
        <th colspan="2">備考</th>
        <td colspan="6"><textarea name="text" rows="8" cols="80"></textarea>
        </td>
      </tr>
    </table>
    </form>

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
      var Attendances_dailyRef, Attendances_monthlyRef, UsersRef;
      var list_Month = new Array();
      database = firebase.database();

      auth = firebase.auth();
      var authProvider = new firebase.auth.GoogleAuthProvider(); //구글 인증창 선언
      auth.onAuthStateChanged(function(user){
        if (user) { //인증 성공
          userInfo = user;

          <?php for($day=1 ; $day < $monthlyDay+1 ; $day++){ ?>
          Attendances_dailyRef = database.ref('Attendances_daily/'
                                              + userInfo.uid
                                              + "/<?=$year?><?=$month?><?=$day?>" ); //yyyymmdd형식
          UsersRef = database.ref('Users/' + userInfo.uid);
          exportdays(<?=$day?>);
          <?php } ?>

          Attendances_monthlyRef = database.ref('Attendances_monthly/'
                                                + userInfo.uid ); //년,월 부분 출력(검색)
          exportmonth();

        } else { //인증 실패 → 인증 팝업이 뜸
          auth.signInWithPopup(authProvider);
        }
      })

      //ajax기본, JQuery
      $('#data').click(function(){
        $.ajax({
            type:"POST", //데이터 보내는 형식
            url:"TimeSheet.php", //요청이 보내지는곳 -> 즉, 값을 가져올곳
            data : "button", //요청하는 데이터
            success: function(datas){
              alert("Success");
              alert(datas);
              $("#dataaa").html(datas);
            },
            error: function(xhr, status, error) {
              alert(error);
            }
        })
      })

      //出力
      function exportdays(day) {
        UsersRef.on('value', function(data) {
          document.getElementById("workplaces" + day).innerHTML = data.val().workplace;
        });

        Attendances_dailyRef.on('value', function(data) {
            if(data.val()) { //Property null error check
              document.getElementById("starttime" + day).innerHTML = data.val().start_time;
              document.getElementById("endtime" + day).innerHTML = data.val().end_time;
              document.getElementById("resttime" + day).innerHTML = data.val().rest_time;

              if(data.val().end_time) {
                var et = data.val().end_time.substring(0,2);
                var st = data.val().start_time.substring(0,2);
                var rt = data.val().rest_time.substring(0,1);
                var overtime = et - st - rt - 8;
                document.getElementById("overworktime" + day).innerHTML = overtime + ":00"; //残業時間
              }
            }
        });
      }

      function exportmonth() {
        Attendances_monthlyRef.on('value', function(data){
          data.forEach(function(item) { //データ部分は Arrayに再び作らなきゃならないです。
            var itemVal = item.key;
            list_Month.push(itemVal);
          })
          document.phptest.test.value = list_Month;
          //document.getElementById("YMs").innerHTML = data.key;
        });
      }

      /*
      function on_child_added(data) {
        console.log( "key : " + data.key ); //key값( = 값이 들어있는 이름 )가져옴
        console.log( "Example 1 : " +  data.val() );
        console.log( "Example 1 : " +  data.val().subname ); //됨
        var TempKey = data.key;
        var pushdata = data.val().userid;
        document.phptest.getjsvalue.value = pushdata;
        alert(document.phptest.getjsvalue.value); //phpに値もらえます。
      }
      */
    </script>
</body>
</html>
