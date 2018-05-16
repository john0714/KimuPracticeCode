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
  <!-- javascript(node.js) Firebase, Auth 宣言-->
  <script src="https://www.gstatic.com/firebasejs/5.0.2/firebase.js"></script>
  <script type="text/javascript" src="js/db.js"></script>

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
  var auth, database;
  var userInfo;
  </script>

  <meta charset="utf-8">
  <link rel="stylesheet" href="css/timeSheet_style.css">
  <link rel="shortcut icon" href=""> <!-- remove favico.ico error -->

  <title>シフト履歴</title>

  <?php
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
      <!-- ページ移動メニュー -->
      <nav class="headA">
        <ul>
            <li><a href="punch.php">出・退勤</a></li>
            <li><a href="memberInfo/mMemInfo.html" target="_blank">個人情報</a></li>
            <!-- ログインした人の履歴に移動 -->
            <!-- <li><a href="javascript:void(0)" onclick=" location.href = 'TimeSheet.php?AuthUser=' + userInfo; return false; ">履歴</a></li> -->
            <li><a href="timeSheet.php">履歴</a></li>
            <li><a href="admin.html" id="admin">管理者</a></li>
            <li><a href="log/mLogin.html" id="login">ログイン</a></li> <!-- log/ == ./log/-->
        </ul>
      </nav>
    </div>
  </header>
</div>
</body>
</html>
