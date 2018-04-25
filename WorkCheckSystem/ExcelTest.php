<!DOCTYPE html>
  <html>
    <head>
    </head>
    <body>
        <?php
        /*
          180424
          Excel出力
        */
        //$_POST로 리스트 값 받아와서 근무표 출력
        require_once "Classes/PHPExcel.php";// PHPExcel.php을 불러와야 하며, 경로는 사용자의 설정에 맞게 수정해야 한다.
        $objPHPExcel = new PHPExcel();
        require_once "Classes/PHPExcel/IOFactory.php";// IOFactory.php을 불러와야 하며, 경로는 사용자의 설정에 맞게 수정해야 한다.
        $filename = "WorkSystemForm.xlsx";// 읽어들일 엑셀 파일의 경로와 파일명을 지정한다.

        try {
        $objReader=PHPExcel_IOFactory::createReaderForFile($filename); //엑셀 Type에 맞는 Read객체를 만듬
        //$objReader->setReadDataOnly(true); //읽기 전용으로 설정
        $objPHPExcel = $objReader->load($filename); //엑셀 파일을 읽는다.
        $objPHPExcel->setActiveSheetIndex(0); //첫번째 시트를 선택
        $objPHPExcel->getActiveSheet()->setCellValue('A1', 'Hello');//선택한 시트에 대한 작업용 변수선언
        //$rowIterator = $sheet->getRowIterator();
        //foreach ($rowIterator as $row) {
        //  $cellIterator = $row->getCellIterator();
        //  $cellIterator->setIterateOnlyExistingCells(false);
        //}
        //$maxRow = $sheet->getHighestRow();
        //for ($i = 0 ; $i <= $maxRow ; $i++) {
        //  $name = $sheet->getCell('A' . $i)->getValue();
        //  $addr1 = $sheet->getCell('B' . $i)->getValue();
        //  $addr2 = $sheet->getCell('C' . $i)->getValue();
        //  $addr3 = $sheet->getCell('D' . $i)->getValue();
        //  $addr4 = $sheet->getCell('E' . $i)->getValue();
        //  $reg_date = $sheet->getCell('F' . $i)->getValue();
        //  $reg_date = PHPExcel_Style_NumberFormat::toFormattedString($reg_date, 'YYYY-MM-DD');
        //}

        //$sheet -> setTitle("asdasdasd");

        $objWriter = PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel2007');
        ob_end_clean(); //Clean (erase) the output buffer and turn off output buffering!!!!
        header('Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
        header('Content-Disposition: attachment; filename="WorkSystem.xlsx"');
        header('Cache-Control: max-age=0');

        $objWriter->save('php://output');

        //$objWriter->save('WorkSystem.xlsx');//서버쪽 다운로드 -> 서버에 파일 보내기위해 반드시 필요
        //header("Location: WorkSystem.xlsx");//클라이언트쪽 다운로드 -> 서버쪽 파일을 가져오는것 뿐
      }
      catch (exception $e) {
        echo '엑셀파일을 읽는도중 오류가 발생하였습니다.';
      }
      ?>
    </body>
  </html>
