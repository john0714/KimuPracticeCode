<!DOCTYPE html>
<html lang="jp" dir="ltr">
<!--
  180427
  シフト画面
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
  </script>

  <meta charset="utf-8">
  <link rel="stylesheet" href="timeSheet_style.css">
  <link rel="shortcut icon" href=""> <!-- remove favico.ico error -->

  <title>シフト履歴</title>

  <?php
    //初期出力の値
    $YMs = json_decode($_POST["Attendances_monthly"], true); //配列送信(trueは stdClassから Arrayに変更する為)
    $Days = json_decode($_POST["Attendances_daily"], true);
    $UserData = json_decode($_POST["Users"], true);
    $AuthUser = $_POST["AuthUser"]; //Ref
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
<div class="overlay">
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
  <b>シフト履歴</b>
  <hr>
  <div class="timeSheet-form">
    <!-- Excel Export Form -->
    <form action="ExcelOutput.php" method="post">
    <div class="select-form">
        <select name="YearMonth" id="YearMonth">
          <?php //print Year and Month
          foreach($YMs as $key=>$value) {
            if($key == $year.$month) { ?>
              <option selected><?=$key?></option>
            <?php } else { ?>
              <option><?=$key?></option>
            <?php }
          } ?>
        </select>
        <button type="button" name="search" id="search" class="search-btn">検索</button>
        <input type="button" name="modify" id="modify" onclick='DBmodify()' class="modify-btn" value="修正"/>
        <input type=submit name="ExcelExport" class="download-btn" value="Excelダウンロード"></input>
  			<input type=hidden name="Users" value=<?=json_encode($UserData) ?>></input>
    </div>
    <!-- Ajax table -->
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
      <?php //print Days
      for($i=1;$i<$monthlyDay+1;$i++) {
      if($i < 10) $i = "0".$i; //日が 1~9の場合
      $day = $daily[$dailyInt]; //曜日を表すための変数
      foreach($Days as $key=>$value) { //日出力して比較
        $Daycheck = true;
        $WP = "";
        $ET = "";
        $ST = "";
        $RT = "";
        $OT = 0;

        if($year.$month.$i == $key) {
          $WP = $value["workplace"];
          $ST = $value["start_time"];
          $ET = $value["end_time"];
          $RT = $value["rest_time"];
          $OT = substr($ET, 0, 2)-substr($ST, 0, 2)-substr($RT, 0, 2)-8; //残業時間
          break;
        } else {
          $Daycheck = false;
        }
      }
      ?>
      <tr>
        <td class="small-cell"><?=$i?></td>
        <td class="small-cell"><?=$day?></td>
        <?php
        $dayarray = array();
        array_push($dayarray, $i, $day, $WP, $ST, $ET, $RT); //dayarray
        array_push($workarray, $dayarray); //workarray
        if($Daycheck == true) {
        ?>
          <td><?=$WP?></td>
          <td><?=$ST?></td>
          <td><?=$ET?></td>
          <td><?=$RT?></td>
          <td><?=$OT?></td>
        <?php } else { ?>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        <?php } ?>
      </tr>
      <?php if($dailyInt<6) {$dailyInt++;} else {$dailyInt=0;}
      } ?>

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

  <!-- javascript(node) Firebase 宣言-->
  <script src="https://www.gstatic.com/firebasejs/4.13.0/firebase.js"></script>
  <script>
    // Initialize Firebase for modify
    var config = {
      apiKey: "AIzaSyCQ_M_PmSh6IqKXrZ7mBGFXlpkJy_QJeGs",
      authDomain: "testproejct-d15c9.firebaseapp.com",
      databaseURL: "https://testproejct-d15c9.firebaseio.com",
      projectId: "testproejct-d15c9",
      storageBucket: "testproejct-d15c9.appspot.com",
      messagingSenderId: "557330742678"
    };
    firebase.initializeApp(config);
    var database, userInfo;
    var Attendances_daily;
    database = firebase.database();
    userInfo='<?=$AuthUser?>';

    //update and refresh
    var Attendances_dailyRef = database.ref('Attendances_daily/' + userInfo );
    Attendances_dailyRef.on('value', function(data){
      Attendances_daily = data.val();
    });
  </script>

  <script>
  var SelectYM = document.getElementById("YearMonth").value;

  /* Ajax for Search */
  $('#search').click(function(){
      document.getElementById("modify").value = "修正";
      SelectYM = document.getElementById("YearMonth").value; //SelectYM Save
      $.ajax({
          type: "POST", //データ送信形式
          url: "TimeSheetSearchTable.php", //請求される場所-> 즉, 값을 가져올곳, 테이블구성용 php가 필요
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

  /*
    Ajax For Modify
  */
  $('#modify').click(function(){
    var buttonText = document.getElementById("modify").value; //Onclickより早く作動しますのでLocal変数に作りました。
    if(buttonText == "修正") {//修正の時
      document.getElementById("modify").value = "修正終了";
      $.ajax({
        type: "POST", //データ送信形式
        url: "TimeSheetModifyTable.php", //請求される場所 -> 즉, 값을 가져올곳, 테이블구성용 php가 필요
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
        url: "TimeSheetSearchTable.php", //請求される場所 -> 즉, 값을 가져올곳, 테이블구성용 php가 필요
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
          var Pattern = /^[0-9]{1,2}[:][0-9]{2}$/; //(~)~:~~形式
          var ST = document.getElementById("STid<?=$i?>").value;
          var ET = document.getElementById("ETid<?=$i?>").value;
          var RT = document.getElementById("RTid<?=$i?>").value;
          if(Pattern.test(ST) && Pattern.test(ET) && Pattern.test(RT)) {
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
            alert("修正に失敗しました ~~:~~に形式に入力してください。");
          }
        }
        <?php
        } ?>
      }
    }
  </script>

</body>
</html>
