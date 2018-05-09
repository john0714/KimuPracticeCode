<?php
  $YMs = $_POST["Attendances_monthly"][0]; //配列送信(trueは stdClassから Arrayに変更する為)
  $selectYM = $_POST["YearMonth"];
?>

<div class="select-form" id="SF">
      <select name="YearMonth" id="YearMonth">
        <?php //print Year and Month
        foreach($YMs as $key=>$value) {
          if($key == $selectYM) { ?>
            <option selected><?=$key?></option>
          <?php } else { ?>
            <option><?=$key?></option>
          <?php }
        } ?>
      </select>
      <button type="button" name="search" id="search" class="search-btn">検索</button>
</div>
