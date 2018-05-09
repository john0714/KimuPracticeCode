<?php
  $UserData = $_POST["Users"][0];
?>
<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
<div class="button-form">
    <!-- 修正ボタン  -->
    <?php if($UserData["authority_id"] == 1) { ?>
      <input type="button" name="modify" id="modify" onclick='DBmodify()' class="modify-btn" value="修正"/>
    <?php } ?>
    <input type=submit name="ExcelExport" class="download-btn" value="Excelダウン"></input>
    <input type=hidden name="Users" value=<?=json_encode($UserData) ?>></input>
</div>
