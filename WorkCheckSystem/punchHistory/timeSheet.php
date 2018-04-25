<!DOCTYPE html>
<html lang="jp" dir="ltr">

<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="timeSheet_style.css">
  <title>シフト画面</title>
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
  <b>シフト履歴</b>
  <hr>
  <div class="timeSheet-form">
    <div class="select-form">
      <select><option><?= $year?></option></select>
      <button type="button" name="search" class="search-btn">検索</button>
      <button type="button" name="memberInsert" class="member-btn">Excel<br>ダウンロード</button>
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
        <td><button type="button" name="modify-btn">編集</button></td>
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
