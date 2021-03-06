<!DOCTYPE html>
<html lang="jp" dir="ltr">
<!--
  180508
  シフト画面(ログインしたユーザー用)
  Html/css：ユンへリン
  機能構築：jhkim
-->
<head>
  <script src="https://use.fontawesome.com/926fe18a63.js"></script>
  <!-- javascript jquery 宣言 -->
  <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>

  <script>
    $(document).ready(function(){
      $(".headB").on('click', function(){
        $(".headA").animate({width:"toggle"}, 200);
      });
    });

    /* Enter Submit Event Remove */
    document.addEventListener('keydown', function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        }
    }, true);
  </script>

  <meta charset="utf-8">
  <link rel="stylesheet" href="timeSheet_style.css">
  <link rel="shortcut icon" href=""> <!-- remove favico.ico error -->

  <title>シフト履歴</title>

  <?php
    //初期出力の値
    $AuthUser = $_GET["AuthUser"];
    //selected YearMonth
    date_default_timezone_set('Asia/Tokyo'); //  default地域設定
    $monthlyDay = date("t"); //一か月の最終日
    $month = date("m"); // 現在の月
  	$year = date("Y"); // 現在の年
    $strtotime = $year."-".$month."-1"; //毎月1日の曜日を求めるための変数
    $dailyInt = date('w', strtotime($strtotime)); // date関数は0~6の数字をreturnする
    $daily = array('日','月','火','水','木','金','土');
    $workarray = array();
  ?>
</head>

<body>
<!-- Loading-image -->
<div id="loading"><img id="loading-image" src="/image/Loading.gif" alt="Loading..." /></div>

<div class="overlay">
  <header>
    <div class="container">
      <div class="container-small">
        <button type="button" class="headB">
          <span class="fa fa-bars"></span>
        </button>
      </div>
      <!-- ページ移動メニュー -->
      <nav class="headA">
        <ul>
            <li><a href="#">出・退勤</a></li>
            <li><a href="#">個人情報</a></li>
            <li><a href="MyTimeSheet.php?AuthUser=<?=$AuthUser?>">履歴</a></li>
            <li><a href="#">管理者</a></li>
            <li><a href="#">ログアウト</a></li>
        </ul>
      </nav>
    </div>
  </header>
</div>
  <b>シフト履歴</b>
  <hr>
  <div class="timeSheet-form">
    <!-- Excel Export Form -->
  <form action="ExcelExport.php" method="post">
  <div class=buttons-container>
    <div class="select-form" id="SF">
        <select name="YearMonth" id="YearMonth">
        </select>
        <button type="button" name="search" id="search" class="search-btn">検索</button>
    </div>
    <div class="button-form" id="BF">
    </div>
  </div>
    <!-- Ajax table -->
    <!-- 以下のテーブルの内容(Attendances_dailyだけ)は現在の運営には特にいらないが、開発した時のテスト用テーブルなので残します -->
    <table id="refresh">
      <tr>
        <th class="small-cell">日</th>
        <th class="small-cell">曜</th>
        <th>勤務地</th>
        <th>出勤</th>
        <th>退勤</th>
        <th>休憩</th>
        <th>残業</th>
      </tr>
      <tr>
        <td>ロ</td>
        <td>ー</td>
        <td>ディ</td>
        <td>ン</td>
        <td>グ</td>
        <td>中</td>
        <td>…</td>
      </tr>
      <input type=hidden name="workdata" value=<?=json_encode($workarray) ?>></input>
      <input type=hidden name="YM" value=<?=$year.$month ?>></input>
      <tr class="memo-cell">
        <th colspan="2">備考</th>
        <td colspan="6"><textarea name="note" rows="8" cols="80"></textarea>
        </td>
      </tr>
    </table>
    </form>
  </div>

  <!-- javascript(node.js) Firebase 宣言-->
  <script src="https://www.gstatic.com/firebasejs/4.13.0/firebase.js"></script>
  <script>
  var config = {
    apiKey: "AIzaSyCQ_M_PmSh6IqKXrZ7mBGFXlpkJy_QJeGs",
    authDomain: "testproejct-d15c9.firebaseapp.com",
    databaseURL: "https://testproejct-d15c9.firebaseio.com",
    projectId: "testproejct-d15c9",
    storageBucket: "testproejct-d15c9.appspot.com",
    messagingSenderId: "557330742678"
  };
  // Initialize Firebase for modify
  firebase.initializeApp(config);
  var database, userInfo;
  var Attendances_daily, Attendances_monthly, Users;
  database = firebase.database();
  userInfo='<?=$AuthUser?>';

  //update and refresh
  var Attendances_dailyRef = database.ref('Attendances_daily/' + userInfo );
  var Attendances_monthlyRef = database.ref('Attendances_monthly/' + userInfo );
  var UsersRef = database.ref('Users/' + userInfo );
  var SelectYM = <?=$year.$month?>; //SelectYM Save

  // Firebase Lodaing....
  $(window).load(function() {
    // Pageloading start
    Attendances_dailyRef.on('value', function(data){
    Attendances_daily = data.val();
      $.ajax({
        type: "POST", //データ送信形式
        url: "TimeSheetSearchTable.php", //請求される場所 -> つまり、データを取る場所です(テーブルの中身が必要)
        data : {"YearMonth": <?=$year.$month?>, //洗濯した年月 -> JSON形式
                "Attendances_daily": [Attendances_daily]
               }, //urlに送る Parameter
        success: function(datas){
          $("#refresh").html(datas); //戻り値 -> テーブルに出力
        },
        error: function(xhr, status, error) {
          alert(error);
        }
      })
    $('#loading').hide(); //Loading End
    });

    Attendances_monthlyRef.on('value', function(data){
      Attendances_monthly = data.val();
      $.ajax({
          type: "POST", //データ送信形式
          url: "MyTimeSheetSelect.php", //請求される場所 -> つまり、データを取る場所です
          data : {"YearMonth": <?=$year.$month?>, //洗濯した年月 -> JSON形式
                  "Attendances_monthly": [Attendances_monthly]
                 }, //urlに送る Parameter
          success: function(datas){
            $("#SF").html(datas); //戻り値 -> テーブルに出力
          },
          error: function(xhr, status, error) {
            alert(error);
          }
      })
    })

    UsersRef.on('value', function(data) {
    Users = data.val();
    $.ajax({
        type: "POST", //データ送信形式
        url: "MyTimeSheetButton.php", //請求される場所 -> つまり、データを取る場所です
        data : {
                "Users": [Users]
               }, //urlに送る Parameter
        success: function(datas){
          $("#BF").html(datas); //戻り値 -> テーブルに出力
        },
        error: function(xhr, status, error) {
          alert(error);
        }
      })
    })
  });

  /* Ajax for Search - MyTimeSheetType */
  $(document).on('click', '.select-form', function(){
      document.getElementById("modify").value = "修正";
      SelectYM = document.getElementById("YearMonth").value; //SelectYM Save
      $.ajax({
          type: "POST", //データ送信形式
          url: "TimeSheetSearchTable.php", //請求される場所 -> つまり、データを取る場所です
          data : {"YearMonth": $("#YearMonth").val(), //洗濯した年月 -> JSON形式
                  "Attendances_daily": [Attendances_daily]
                 }, //urlに送る Parameter
          success: function(datas){
            //alert("Success");
            //alert(datas);
            $("#refresh").html(datas); //戻り値 -> テーブルに出力
          },
          error: function(xhr, status, error) {
            alert(error);
          }
      })
  })

  /* Ajax For Modify - MyTimeSheetType */
  $(document).on('click', '.modify-btn', function(){
    var buttonText = document.getElementById("modify").value; //Onclickより早く作動しますのでLocal変数に作りました。
    if(buttonText == "修正") { //修正の時
      document.getElementById("modify").value = "修正終了";
      $.ajax({
        type: "POST", //データ送信形式
        url: "TimeSheetModifyTable.php", //請求される場所 -> つまり、データを取る場所です
        data : {"YearMonth": SelectYM, //選択した年月
                "Attendances_daily": [Attendances_daily]
               }, //urlに送る Parameter
        success: function(datas){
          $("#refresh").html(datas); //戻り値 -> テーブルに出力
        },
        error: function(xhr, status, error) {
          alert(error);
        }
      })
    } else { //修正完了の時
    document.getElementById("modify").value = "修正";
    $.ajax({
        type: "POST", //データ送信形式
        url: "TimeSheetSearchTable.php", //請求される場所 -> つまり、データを取る場所です
        data : {"YearMonth": SelectYM, //選択した年月  -> JSON形式
                "Attendances_daily": [Attendances_daily]
               }, //urlに送る Parameter
        success: function(datas){
          $("#refresh").html(datas); //戻り値 -> テーブルに出力
        },
        error: function(xhr, status, error) {
          alert(error);
        }
      })
    }
  })
  </script>

  <script>
    /* Firebase Database update */
    function DBmodify() {
      var buttonText = document.getElementById("modify").value;
      if(buttonText == "修正終了") {
        <?php //print ALL monthlyDays
        for($i=1;$i<$monthlyDay+1;$i++) {
        if($i < 10) $i = "0".$i;
        ?>

        //null check
        if(document.getElementById("STid<?=$i?>") != null) {

          //修正文字形Check(正規表現式)
          var Pattern = /^([1-9]|[01][0-9]|2[0-3])[:]([0-5][0-9])$/; //時間形式
          var RTPattern = /^[0-9][:]([0-5][0-9])$/; //時間形式
          var ST = document.getElementById("STid<?=$i?>").value;
          var ET = document.getElementById("ETid<?=$i?>").value;
          var RT = document.getElementById("RTid<?=$i?>").value;
          if(Pattern.test(ST) && Pattern.test(ET) && RTPattern.test(RT)) {
            //修正
            var SelectYMD = SelectYM + '<?=$i?>';
            Attendances_dailyRef.update({
              [SelectYMD]: {
                start_time: document.getElementById("STid<?=$i?>").value,
                end_time: document.getElementById("ETid<?=$i?>").value,
                rest_time: document.getElementById("RTid<?=$i?>").value,
                workplace: document.getElementById("WPid<?=$i?>").innerHTML
              },
            });
          } else {
            alert("修正に失敗しました 時間の形式で入力してください。");
          }
        }
        <?php
        } ?>
      }
    }
  </script>
</body>
</html>
