<!DOCTYPE html>
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
  <title></title>
  <?php
	date_default_timezone_set('Asia/Tokyo'); //  default地域設定
        $monthlyDay = date("t"); //一か月の最終日
        $month = date("m"); // 現在の時間を月をとってくる
	$year = date("Y-m");
        $strtotime = $year. "-1"; //毎月1日の曜日を求めるための変数
        $dailyInt = date('w', strtotime($strtotime)); // date関数は0~6の数字をreturnする
        $daily = array('日','月','火','水','木','金','土');
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
    <div class="select-form">
      <select><option><?= $year?></option></select>
      <button type="button" name="search" class="search-btn">検索</button>
      <button type="button" name="memberInsert" class="download-btn">Excel<br>ダウンロード</button>
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
      <?php for($i=1;$i<$monthlyDay+1;$i++){
      $day = $daily[$dailyInt]; //曜日を表すための変数 ?>
      <tr>
        <td class="small-cell">
          <?= $i?>
        </td>
        <td class="small-cell">
          <? echo $day; if($dailyInt<6) $dailyInt++; else $dailyInt=0;?>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td><button type="button" class="modify-btn">編集</button></td>
      </tr>
      <?php } ?>
      <tr class="memo-cell">
        <th colspan="2">備考</th>
        <td colspan="6"><textarea name="text" rows="8" cols="80"></textarea>
        </td>
      </tr>
    </table>
  </div>
</body>
</html>
