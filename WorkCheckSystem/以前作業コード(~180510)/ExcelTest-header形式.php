<!DOCTYPE html>
  <html>
    <head>
    </head>
    <body>
        <?php
        header( "Content-type: application/vnd.ms-excel;charset=UTF-8");
        header( "Expires: 0" );
        header( "Cache-Control: must-revalidate, post-check=0,pre-check=0" );
        header( "Pragma: public" );
        header( "Content-Disposition: attachment; filename=勤務表_".date('Ymd').".xlsx" );
        echo "
        <META HTTP-EQUIV='Content-Type' CONTENT='text/html; charset=UTF-8'>
        <table border=1>
        <tr>
        <td>1번컬럼</td><td>2번컬럼</td><td>3번컬럼</td><td>4번컬럼</td>
        </tr>
        ";
        echo "
        <tr>
        <td>여기는 1번자리</td>
        <td>여기는 2번자리</td>
        <td>'02-0000-0000'</td>
        <td>'010-0000-0000'</td>
        </tr>";
        echo "
        </table>";
        die;
        ?>
    </body>
  </html>
